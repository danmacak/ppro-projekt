package cz.uhk.restaurace.service;

import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.model.IngredientGeneral;
import cz.uhk.restaurace.model.IngredientLoc;

public interface IngredientGeneralService {
	public void addIngredient(IngredientGeneral ingredient);
	public void updateIngredient(IngredientGeneral ingredient);
	public List<IngredientGeneral> listIngredient();
	public IngredientGeneral getIngredientById(int id);
	public void removeIngredient(int id);
	public List<IngredientGeneral> getIngredientsByCategory(IngredientGeneral.IngredientType type, String language);
	public Map<Integer, IngredientGeneral> getIngredientsLocalized(Map<Integer, IngredientGeneral> ingredients, String language);
	public IngredientLoc getIngredientLocalized(Integer id, String language);
	public List<IngredientGeneral.IngredientType> getIngredientTypes();
	public void actualizeLocFieldOnIngredients(Map<Integer, IngredientGeneral> ingredients, String language);
}
