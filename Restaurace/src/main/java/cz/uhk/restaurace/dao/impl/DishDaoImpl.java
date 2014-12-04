package cz.uhk.restaurace.dao.impl;

import java.util.List;

import cz.uhk.restaurace.dao.DishDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cz.uhk.restaurace.model.Dish;

@Repository
public class DishDaoImpl implements DishDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void addDish(Dish dish) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(dish);
		
	}

	@Override
	public void updateDish(Dish dish) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(dish);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dish> listDishes() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Dish.class);
		return criteria.add(Restrictions.not(Restrictions.eq("dishCategory", Dish.DishCategory.DRINK))).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dish> listDrinks() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Dish.class);
		return criteria.add(Restrictions.eq("dishCategory", Dish.DishCategory.DRINK)).list();
	}

	@Override
	public Dish getDishById(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Dish a = (Dish) session.get(Dish.class, id);
		return a;
	}

	@Override
	public void removeDish(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Dish a = (Dish) session.load(Dish.class, new Integer(id));
		if (a != null) {
			session.delete(a);
		}
		
	}

	
	
}
