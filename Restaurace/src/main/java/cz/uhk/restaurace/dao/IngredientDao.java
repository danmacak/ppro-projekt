package cz.uhk.restaurace.dao;

import java.util.List;

import cz.uhk.restaurace.model.Ingredient;

public interface IngredientDao {
	public void addIngredient(Ingredient ingredient);
	public void updateIngredient(Ingredient ingredient);
	public List<Ingredient> listIngredient();
	public Ingredient getIngredientById(int id);
	public void removeIngredient(int id);
	public List<Ingredient> getIngredientsByCategory(Ingredient.IngredientType type);
}
