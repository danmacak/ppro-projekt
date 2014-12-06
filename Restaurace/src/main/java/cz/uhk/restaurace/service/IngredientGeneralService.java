package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.IngredientGeneral;

public interface IngredientGeneralService {
	public void addIngredient(IngredientGeneral ingredient);
	public void updateIngredient(IngredientGeneral ingredient);
	public List<IngredientGeneral> listIngredient();
	public IngredientGeneral getIngredientById(int id);
	public void removeIngredient(int id);
	public List<IngredientGeneral> getIngredientsByCategory(IngredientGeneral.IngredientType type);
}
