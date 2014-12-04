package cz.uhk.restaurace.dao.impl;

import java.util.List;

import cz.uhk.restaurace.dao.IngredientDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cz.uhk.restaurace.model.Ingredient;

@Repository
public class IngredientDaoImpl implements IngredientDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addIngredient(Ingredient ingredient) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(ingredient);

	}

	@Override
	public void updateIngredient(Ingredient ingredient) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(ingredient);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingredient> listIngredient() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Ingredient> ingredientList = session.createQuery("from Ingredient").list();
		return ingredientList;
	}

	@Override
	public Ingredient getIngredientById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Ingredient a = (Ingredient) session.get(Ingredient.class, new Integer(id));
		return a;
	}

	@Override
	public void removeIngredient(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Ingredient a = (Ingredient) session.load(Ingredient.class, new Integer(id));
		if (a != null) {
			session.delete(a);
		}

	}

	@Override
	public List<Ingredient> getIngredientsByCategory(Ingredient.IngredientType type) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Ingredient.class);
		return criteria.add(Restrictions.eq("type", type)).list();
	}
}