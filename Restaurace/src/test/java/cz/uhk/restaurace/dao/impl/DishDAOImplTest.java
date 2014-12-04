package cz.uhk.restaurace.dao.impl;

import cz.uhk.restaurace.model.Dish;

/**
 * Created by dann on 17.11.2014.
 */
public class DishDAOImplTest {

    public static void main(String [] args){
        Dish dish = new Dish();
        dish.setDishCategory(Dish.DishCategory.DRINK);
        System.out.println(Dish.DishCategory.DRINK.getClass());
        System.out.println(dish.getDishCategory().getName());
    }
}
