package cz.uhk.restaurace.dao;

import java.util.List;

import cz.uhk.restaurace.model.Dish;

public interface DishDao {
	public void addDish(Dish dish);
	public void updateDish(Dish dish);
	public List<Dish> listDishes();
	public List<Dish> listDrinks();
	public Dish getDishById(String id);
	public void removeDish(String id);
	

}
