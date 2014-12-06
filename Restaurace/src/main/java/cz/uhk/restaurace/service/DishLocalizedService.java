package cz.uhk.restaurace.service;

import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.model.DishGeneral;
import cz.uhk.restaurace.model.DishLocalized;

public interface DishLocalizedService {
	public void addDishLocalized(DishLocalized dish);
	public void updateDishLocalized(DishLocalized dish);
	public List<DishLocalized> listDishesLocalized();
	public List<DishLocalized> listDrinksLocalized();
	public DishLocalized getDishLocalizedById(int id);
	public void removeDishLocalized(int id);
	public void setLanguage(String language);
	public Map<String, DishLocalized> getDishesLocalizedInCart(List<Integer> ids);
}
