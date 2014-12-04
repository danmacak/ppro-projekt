package cz.uhk.restaurace.service;

import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.model.IngredientGeneral;
import cz.uhk.restaurace.model.IngredientLocalized;

public interface IngredientLocalizedService {
	public void addIngredientLocalized(IngredientLocalized ingredientLocalized);
	public void updateIngredientLocalized(IngredientLocalized ingredientLocalized);
	public List<IngredientLocalized> listIngredientsLocalized();
	public IngredientLocalized getIngredientLocalizedById(int id);
	public void removeIngredientLocalized(int id);
	public List<IngredientLocalized> getIngredientsLocalizedByCategory(IngredientGeneral.IngredientType type);
	public void SetLanguage(String lang);
	public Map<Integer, IngredientLocalized> relocalizeIngredients(Map<Integer, IngredientLocalized> il);

}
