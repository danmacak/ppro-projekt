package cz.uhk.restaurace.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.uhk.restaurace.dao.DishGeneralDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.restaurace.model.DishGeneral;
import cz.uhk.restaurace.model.DishLoc;
import cz.uhk.restaurace.model.Role;

@Repository
public class DishGeneralDaoImpl implements DishGeneralDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void addDishGeneral(DishGeneral dishGeneral) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(dishGeneral);
		
	}

	@Override
	public void updateDishGeneral(DishGeneral dishGeneral) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(dishGeneral);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DishGeneral> listDishesGeneral() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(DishGeneral.class);
		return criteria.add(Restrictions.not(Restrictions.eq("dishCategory", DishGeneral.DishCategory.DRINK))).list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DishGeneral> listDrinksGeneral() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(DishGeneral.class);
		return criteria.add(Restrictions.eq("dishCategory", DishGeneral.DishCategory.DRINK)).list();
	}

	@Override
	public DishGeneral getDishGeneralById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		DishGeneral a = (DishGeneral) session.get(DishGeneral.class, id);
		return a;
	}

	@Override
	public void removeDishGeneral(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		DishGeneral a = (DishGeneral) session.load(DishGeneral.class, id);
		if (a != null) {
			session.delete(a);
		}
	}
}
