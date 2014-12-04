package cz.uhk.restaurace.dao;

import java.util.List;

import cz.uhk.restaurace.model.DishLoc;

public interface DishLocDao {
	public void addDishLoc(DishLoc dishLoc);
	public void updateDishLoc(DishLoc dishLoc);
	public List<DishLoc> listDishLoc();
	public DishLoc getDishLocById(int id, String language);
	public void removeDishLoc(int id, String language);

}
