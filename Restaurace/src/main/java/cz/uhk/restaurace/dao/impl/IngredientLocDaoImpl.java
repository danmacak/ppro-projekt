package cz.uhk.restaurace.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.restaurace.dao.IngredientLocDao;
import cz.uhk.restaurace.model.IngredientLoc;
@Repository
public class IngredientLocDaoImpl implements IngredientLocDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addIngredientLoc(IngredientLoc ingredientLoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(ingredientLoc);

	}

	@Override
	public void updateIngredientLoc(IngredientLoc ingredientLoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(ingredientLoc);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IngredientLoc> listIngredientLoc() {
		Session session = this.sessionFactory.getCurrentSession();
		List<IngredientLoc> ingredientLocList = session.createQuery("from IngredientLoc").list();
		return ingredientLocList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IngredientLoc getIngredientLocById(int id, String language) {
	
		Session session = this.sessionFactory.getCurrentSession();
	
		Criteria criteria = session.createCriteria(IngredientLoc.class);
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("id", id)).
				add(Restrictions.eq("language", language));
		List<IngredientLoc> ill = criteria.add(criterion).list();
		if (ill.size() == 1) {
			return ill.get(0);
			
		} else {return null;

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeIngredientLoc(int id, String language) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(IngredientLoc.class);
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("id", id)).
				add(Restrictions.eq("language", language));
		List<IngredientLoc> ill = criteria.add(criterion).list();
		if (ill.size() == 1) {
			session.delete(ill.get(0));
			
		}

	}

}
