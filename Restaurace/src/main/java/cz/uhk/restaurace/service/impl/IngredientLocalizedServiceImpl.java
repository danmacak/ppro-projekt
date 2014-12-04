package cz.uhk.restaurace.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.uhk.restaurace.dao.IngredientGeneralDao;
import cz.uhk.restaurace.dao.IngredientLocDao;
import cz.uhk.restaurace.model.IngredientGeneral;
import cz.uhk.restaurace.model.IngredientGeneral.IngredientType;
import cz.uhk.restaurace.model.IngredientLocalized;
import cz.uhk.restaurace.service.IngredientLocalizedService;
@Service
public class IngredientLocalizedServiceImpl implements IngredientLocalizedService{
	@Autowired
	private IngredientGeneralDao ingredientGeneralDao;
	@Autowired
	private IngredientLocDao ingredientLocDao;
	
	private String language = "cs";
	
	public void setIngredientGeneralDao(
			IngredientGeneralDao ingredientGeneralDao) {
		this.ingredientGeneralDao = ingredientGeneralDao;
	}
	public void setIngredientLocDao(IngredientLocDao ingredientLocDao) {
		this.ingredientLocDao = ingredientLocDao;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Transactional
	@Override
	public void addIngredientLocalized(IngredientLocalized ingredientLocalized) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	@Override
	public void updateIngredientLocalized(
			IngredientLocalized ingredientLocalized) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	@Override
	public List<IngredientLocalized> listIngredientsLocalized() {
		List<IngredientLocalized> res = new ArrayList<IngredientLocalized>();
		List<IngredientGeneral> ig = ingredientGeneralDao.listIngredientsGeneral();
		for (int i = 0; i < ig.size(); i++) {
			res.add(new IngredientLocalized(ig.get(i), ingredientLocDao.getIngredientLocById(ig.get(i).getId(), language)));			
		}
		return res;
	}
	@Transactional
	@Override
	public IngredientLocalized getIngredientLocalizedById(int id) {
		return new IngredientLocalized(ingredientGeneralDao.getIngredientGeneralById(id),
				ingredientLocDao.getIngredientLocById(id, language));
	}
	@Transactional
	@Override
	public void removeIngredientLocalized(int id) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	@Override
	public List<IngredientLocalized> getIngredientsLocalizedByCategory(
			IngredientType type) {
		List<IngredientLocalized> res = new ArrayList<IngredientLocalized>();
		List<IngredientGeneral> ig = ingredientGeneralDao.getIngredientsGeneralByCategory(type);
		for (int i = 0; i < ig.size(); i++) {
			res.add(new IngredientLocalized(ig.get(i), ingredientLocDao.getIngredientLocById(ig.get(i).getId(), language)));			
		}
		return res;
	}
	@Override
	public void SetLanguage(String lang) {
	language = lang;
		
	}
	@Override
	public Map<Integer, IngredientLocalized> relocalizeIngredients(
			Map<Integer, IngredientLocalized> il) {
		for (Map.Entry<Integer, IngredientLocalized> entry: il.entrySet()) {
			entry.setValue(relocalize(entry.getValue()));
		}
		return il;
	}
	@Transactional
	private IngredientLocalized relocalize(IngredientLocalized il){
		if (il.getLanguage() != language) {
			il.setName(ingredientLocDao.getIngredientLocById(il.getId(), language).getName());
			return il;
		} else {
			return il;
		}
	}

}
