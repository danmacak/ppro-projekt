package cz.uhk.restaurace.service.impl;

import java.util.List;

import cz.uhk.restaurace.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.IngredientDao;
import cz.uhk.restaurace.model.Ingredient;
@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientDao ingredientDao;

	@Override
	@Transactional
	public void addIngredient(Ingredient ingredient) {
		ingredientDao.addIngredient(ingredient);

	}

	@Override
	@Transactional
	public void updateIngredient(Ingredient ingredient) {
		ingredientDao.updateIngredient(ingredient);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Ingredient> listIngredient() {
		return ingredientDao.listIngredient();
	}

	@Override
	@Transactional(readOnly = true)
	public Ingredient getIngredientById(int id) {
		return ingredientDao.getIngredientById(id);
	}

	@Override
	@Transactional
	public void removeIngredient(int id) {
		ingredientDao.removeIngredient(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Ingredient> getIngredientsByCategory(Ingredient.IngredientType type) {
		return ingredientDao.getIngredientsByCategory(type);
	}
}
