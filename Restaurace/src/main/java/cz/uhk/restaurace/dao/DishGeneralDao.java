package cz.uhk.restaurace.dao;

import java.util.List;

import cz.uhk.restaurace.model.DishGeneral;

public interface DishGeneralDao {
	public void addDishGeneral(DishGeneral dish);
	public void updateDishGeneral(DishGeneral dish);
	public List<DishGeneral> listDishesGeneral();
	public List<DishGeneral> listDrinksGeneral();
	public DishGeneral getDishGeneralById(int id);
	public void removeDishGeneral(int id);

}
