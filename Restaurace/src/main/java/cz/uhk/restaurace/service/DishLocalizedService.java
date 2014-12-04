package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.DishLocalized;

public interface DishLocalizedService {
	public void addDishLocalized(DishLocalized dish);
	public void updateDishLocalized(DishLocalized dish);
	public List<DishLocalized> listDishesLocalized();
	public List<DishLocalized> listDrinksLocalized();
	public DishLocalized getDishLocalizedById(int id);
	public void removeDishLocalized(int id);
	public void setLanguage(String language);

}
