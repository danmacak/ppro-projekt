package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.*;
import cz.uhk.restaurace.service.DishLocalizedService;
import cz.uhk.restaurace.service.IngredientLocalizedService;

import cz.uhk.restaurace.service.IngredientGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

/**
 * Created by dann on 15.11.2014.
 */

@Controller
public class DishController {

	@Autowired
	private DishLocalizedService dishLocalizedService;

	@Autowired
	private IngredientLocalizedService ingredientLocalizedService;

	@Autowired
	private IngredientGeneralService ingredientGeneralService;

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

	//TODO doimplementovat, checknout potencialni duplicity v mape
	@RequestMapping(value = "/addIngredient", method = RequestMethod.GET,  produces = "application/json")
	@ResponseBody
	public IngredientGeneral addIngredient(HttpSession session, @RequestParam Integer id,
							 @RequestParam(required = false) String grams){
		DishGeneral dish = (DishGeneral) session.getAttribute("teppanyakiDish");
		IngredientGeneral ingredient = null;
		if (dish != null) {
			ingredient = ingredientGeneralService.getIngredientById(id);
			ingredient.setGrams(Integer.parseInt(grams));
			dish.getIngredients().put(id, ingredient);
		}
		return ingredient;
	}

	/*@RequestMapping(value = "/removeIngredient", method = RequestMethod.GET)
	@ResponseBody
	public Integer removeIngredient(HttpSession session, @RequestParam("id") Integer id) {
		DishLocalized dish = (DishLocalized) session.getAttribute("teppanyakiDish");
		dish.getIngredientsLocalized().remove(id);
		return id;
	}*/

	@RequestMapping(value = "/removeIngredient", method = RequestMethod.GET)
	public String removeIngredient(HttpSession session, @RequestParam("category") String category,
								   @RequestParam("id") Integer id) {
		DishLocalized dish = (DishLocalized) session
				.getAttribute("teppanyakiDish");
		dish.getIngredientsLocalized().remove(id);
		return "redirect:/teppanyaki/" + category;
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
