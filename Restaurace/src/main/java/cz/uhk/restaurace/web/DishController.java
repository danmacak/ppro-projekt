package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.*;
import cz.uhk.restaurace.service.DishLocalizedService;
import cz.uhk.restaurace.service.IngredientLocalizedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dann on 15.11.2014.
 */

@Controller
public class DishController {

	@Autowired
	private DishLocalizedService dishLocalizedService;

	@Autowired
	private IngredientLocalizedService ingredientLocalizedService;

	private String language = "cs";

	public void setLanguage(String language, HttpSession session) {
		this.language = language;
		ingredientLocalizedService.SetLanguage(language);
		dishLocalizedService.setLanguage(language);
//		if (session.getAttribute("teppanyakiDish") != null) {
//			DishLocalized dl = (DishLocalized) session
//					.getAttribute("teppanyakiDish");
//			if (!dl.getIngredientsLocalized().isEmpty()) {
//				dl.setIngredientsLocalized(ingredientLocalizedService
//						.relocalizeIngredients(dl.getIngredientsLocalized()));
//			}
//		}
	}

	@RequestMapping(value = "/teppanyaki/{ingredient}")
	public String showIngredientsByCategory(Model model,
			@PathVariable("ingredient") String ingredient) {
		IngredientGeneral.IngredientType category = null;
		for (IngredientGeneral.IngredientType type : IngredientGeneral.IngredientType
				.values()) {
			if (ingredient.equals(type.getUrl())) {
				category = type;
			}
		}
		List<IngredientLocalized> ingredients = ingredientLocalizedService
				.getIngredientsLocalizedByCategory(category);
		model.addAttribute("ingredients", ingredients);
		return "teppanyaki";
	}

	/*@RequestMapping(value = "/addIngredient", method = RequestMethod.GET)
	public String addIngredientToDish(HttpSession session,
			@RequestParam("id") String id, @RequestParam("grams") String grams,
			@RequestParam("category") String category) {
		DishLocalized dish = (DishLocalized) session
				.getAttribute("teppanyakiDish");
		if (dish != null) {
			IngredientLocalized ingredient = ingredientLocalizedService
					.getIngredientLocalizedById(Integer.parseInt(id));
			ingredient.setGrams(Integer.parseInt(grams));
			dish.getIngredientsLocalized()
					.put(Integer.parseInt(id), ingredient);
		}
		return "redirect:/teppanyaki/" + category;
	}*/

	//TODO doimplementovat, checknout potencialni duplicity v mape
	@RequestMapping(value = "/addIngredient", method = RequestMethod.GET,  produces = "application/json")
	@ResponseBody
	public IngredientLocalized addIngredient(HttpSession session, @RequestParam int id,
							 @RequestParam String grams){
		DishLocalized dish = (DishLocalized) session.getAttribute("teppanyakiDish");
		IngredientLocalized ingredient = null;
		if (dish != null) {
			ingredient = ingredientLocalizedService.getIngredientLocalizedById(id);
			ingredient.setGrams(Integer.parseInt(grams));
			dish.getIngredientsLocalized().put(id, ingredient);
		}
		return ingredient;
	}

	@RequestMapping(value = "/removeIngredient", method = RequestMethod.GET)
	public String removeIngredient(HttpSession session,
			@RequestParam("id") String id,
			@RequestParam("category") String category) {
		DishLocalized dish = (DishLocalized) session
				.getAttribute("teppanyakiDish");
		dish.getIngredientsLocalized().remove(Integer.parseInt(id));
		return "redirect:/teppanyaki/" + category;
	}

	@RequestMapping(value = "/teppanyaki", method = RequestMethod.GET)
	public String showTeppanyakiHighLevel(HttpSession session) {
		if (session.getAttribute("ingredientTypes") == null) {
			IngredientGeneral.IngredientType[] ingredientTypes = IngredientGeneral.IngredientType
					.values();
			session.setAttribute("ingredientTypes", ingredientTypes);
		}
		if (session.getAttribute("teppanyakiDish") == null) {
			DishLocalized dish = new DishLocalized();
			Map<Integer, IngredientLocalized> ingredients = new HashMap<Integer, IngredientLocalized>();
			dish.setIngredientsLocalized(ingredients);
			dish.setDishCategory(DishGeneral.DishCategory.TEPPANYAKI);
			session.setAttribute("teppanyakiDish", dish);
		}
		return "teppanyaki";
	}

	@RequestMapping(value = "/drinks", method = RequestMethod.GET)
	public String showDrinks(HttpSession session, Model model){
		model.addAttribute("drinksToShow", true);
		return "menu";
	}

	@RequestMapping(value = "/dishes", method = RequestMethod.GET)
	public String showDishes(Model model){
		model.addAttribute("dishesToShow", true);
		return "menu";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String showAllProducts(HttpSession session, Model model){
		model.addAttribute("dishesToShow", true);
		model.addAttribute("drinksToShow", true);
		return "menu";
	}

	@ModelAttribute(value = "dishes")
	public List<DishLocalized> getDishes(){
		return dishLocalizedService.listDishesLocalized();
	}

	@ModelAttribute(value = "drinks")
	public List<DishLocalized> getDrinks(){
		return dishLocalizedService.listDrinksLocalized();
	}

}
