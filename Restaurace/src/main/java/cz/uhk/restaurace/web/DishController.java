package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.*;
import cz.uhk.restaurace.service.DishGeneralService;
import cz.uhk.restaurace.service.DishLocService;
import cz.uhk.restaurace.service.IngredientLocService;
import cz.uhk.restaurace.service.IngredientGeneralService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dann on 15.11.2014.
 */

@Controller
public class DishController {
	
	@Autowired
	private DishLocService dishLocService;
	
	@Autowired
	private DishGeneralService dishGeneralService;

	@Autowired
	private IngredientLocService ingredientLocService;

	@Autowired
	private IngredientGeneralService ingredientGeneralService;

	private String language = "cs";

	public void setLanguage(String language, HttpSession session) {
		this.language = language;
	}

	@RequestMapping(value = "/teppanyaki", method = RequestMethod.GET)
	public String showTeppanyakiHighLevel(HttpSession session, Model model) {
		DishGeneral dish = (DishGeneral)session.getAttribute("teppanyakiDish");

		if (dish == null) {
			dish = new DishGeneral();
			Map<Integer, IngredientGeneral> ingredients = new HashMap<Integer, IngredientGeneral>();
			dish.setIngredients(ingredients);
			dish.setDishCategory(DishGeneral.DishCategory.TEPPANYAKI);
			session.setAttribute("teppanyakiDish", dish);
		}
		ingredientGeneralService.actualizeLocFieldOnIngredients(dish.getIngredients(), this.language);
		model.addAttribute("ingredientTypes", ingredientGeneralService.getIngredientTypes());
		return "teppanyaki";
	}

	@RequestMapping(value = "/teppanyaki/{ingredient}")
	public String showIngredientsByCategory(HttpSession session, Model model, @PathVariable("ingredient") String ingredient) {
		DishGeneral dish = (DishGeneral)session.getAttribute("teppanyakiDish");
		IngredientGeneral.IngredientType category = null;
		for (IngredientGeneral.IngredientType type : IngredientGeneral.IngredientType
				.values()) {
			if (ingredient.equals(type.getUrl())) {
				category = type;
			}
		}
		ingredientGeneralService.actualizeLocFieldOnIngredients(dish.getIngredients(), this.language);
		model.addAttribute("ingredients", ingredientGeneralService.getIngredientsByCategory(category, this.language));
		model.addAttribute("ingredientTypes", ingredientGeneralService.getIngredientTypes());
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
			ingredient.setIngredientLocalized(ingredientGeneralService.getIngredientLocalized(ingredient.getId(), this.language));
			ingredient.setGrams(Integer.parseInt(grams));
			dish.getIngredients().put(id, ingredient);
		}
		return ingredient;
	}

	@RequestMapping(value = "/removeIngredient", method = RequestMethod.GET)
	public String removeIngredient(HttpSession session, @RequestParam("category") String category,
								   @RequestParam("id") Integer id) {
		DishGeneral dish = (DishGeneral) session
				.getAttribute("teppanyakiDish");
		dish.getIngredients().remove(id);
		return "redirect:/teppanyaki/" + category;
	}

	@RequestMapping(value = "/drinks", method = RequestMethod.GET)
	public String showDrinks(HttpSession session, Model model){
		model.addAttribute("drinksToShow", true);
		model.addAttribute("drinks", dishGeneralService.listDrinks(this.language));
		return "menu";
	}

	@RequestMapping(value = "/dishes", method = RequestMethod.GET)
	public String showDishes(Model model){
		model.addAttribute("dishesToShow", true);
		model.addAttribute("dishes", dishGeneralService.listDishes(this.language));
		return "menu";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String showAllProducts(HttpSession session, Model model){
		model.addAttribute("dishesToShow", true);
		model.addAttribute("drinksToShow", true);
		model.addAttribute("dishes", dishGeneralService.listDishes(this.language));
		model.addAttribute("drinks", dishGeneralService.listDrinks(this.language));
		return "menu";
	}

	//nemazat, pripravena metoda pro ajax
	/*@RequestMapping(value = "/removeIngredient", method = RequestMethod.GET)
	@ResponseBody
	public Integer removeIngredient(HttpSession session, @RequestParam("id") Integer id) {
		DishLocalized dish = (DishLocalized) session.getAttribute("teppanyakiDish");
		dish.getIngredientsLocalized().remove(id);
		return id;
	}*/

}
