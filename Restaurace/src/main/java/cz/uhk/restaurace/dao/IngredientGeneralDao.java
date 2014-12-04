package cz.uhk.restaurace.dao;

import java.util.List;

import cz.uhk.restaurace.model.IngredientGeneral;

public interface IngredientGeneralDao {
	public void addIngredientGeneral(IngredientGeneral ingredient);
	public void updateIngredientGeneral(IngredientGeneral ingredient);
	public List<IngredientGeneral> listIngredientsGeneral();
	public List<IngredientGeneral> getIngredientsGeneralByCategory(IngredientGeneral.IngredientType type);
	public IngredientGeneral getIngredientGeneralById(int id);
	public void removeIngredientGeneral(int id);

}
