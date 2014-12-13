package cz.uhk.restaurace.service;

import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.model.IngredientGeneral;
import cz.uhk.restaurace.model.IngredientLoc;

/**
 * This service encapsulates common operations with ingredients
 */
public interface IngredientGeneralService {

	/**
	 * Add one ingredient to DB
	 * @param ingredient
	 */
	public void addIngredient(IngredientGeneral ingredient);

	/**
	 * Update ingredient in DB
	 * this update methods may not actually be needed
	 * @param ingredient
	 */
	public void updateIngredient(IngredientGeneral ingredient);

	/**
	 * Get list of all ingredients without localized describing fields
	 * @return
	 */
	public List<IngredientGeneral> listIngredient();

	/**
	 * Retrieve single ingredient by provided id
	 * @param id
	 * @return
	 */
	public IngredientGeneral getIngredientById(int id);

	/**
	 * Remove ingredient from DB
	 * @param id
	 */
	public void removeIngredient(int id);

	/**
	 * Get all ingredients by their ingredient type and language actually being used in app
	 * @param type
	 * @param language
	 * @return
	 */
	public List<IngredientGeneral> getIngredientsByCategory(IngredientGeneral.IngredientType type, String language);
	public Map<Integer, IngredientGeneral> getIngredientsLocalized(Map<Integer, IngredientGeneral> ingredients, String language);
	public IngredientLoc getIngredientLocalized(Integer id, String language);
	public List<IngredientGeneral.IngredientType> getIngredientTypes();
	public void actualizeLocFieldsOnIngredients(Map<Integer, IngredientGeneral> ingredients, String language);
}
