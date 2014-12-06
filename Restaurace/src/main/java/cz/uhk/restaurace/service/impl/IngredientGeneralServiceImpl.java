package cz.uhk.restaurace.service.impl;

import java.util.List;

import cz.uhk.restaurace.dao.IngredientGeneralDao;
import cz.uhk.restaurace.model.IngredientGeneral;
import cz.uhk.restaurace.service.IngredientGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredientGeneralServiceImpl implements IngredientGeneralService {

	@Autowired
	private IngredientGeneralDao ingredientGeneralDao;

	@Override
	@Transactional
	public void addIngredient(IngredientGeneral ingredient) {
		ingredientGeneralDao.addIngredientGeneral(ingredient);
	}

	@Override
	@Transactional
	public void updateIngredient(IngredientGeneral ingredient) {
		ingredientGeneralDao.updateIngredientGeneral(ingredient);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IngredientGeneral> listIngredient() {
		return ingredientGeneralDao.listIngredientsGeneral();
	}

	@Override
	@Transactional(readOnly = true)
	public IngredientGeneral getIngredientById(int id) {
		return ingredientGeneralDao.getIngredientGeneralById(id);
	}

	@Override
	@Transactional
	public void removeIngredient(int id) {
		ingredientGeneralDao.removeIngredientGeneral(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<IngredientGeneral> getIngredientsByCategory(IngredientGeneral.IngredientType type) {
		return ingredientGeneralDao.getIngredientsGeneralByCategory(type);
	}
}
