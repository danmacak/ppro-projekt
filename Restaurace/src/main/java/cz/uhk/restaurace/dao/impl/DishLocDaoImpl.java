package cz.uhk.restaurace.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.restaurace.dao.DishLocDao;
import cz.uhk.restaurace.model.DishLoc;
@Repository
public class DishLocDaoImpl implements DishLocDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addDishLoc(DishLoc dishLoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(dishLoc);

	}

	@Override
	public void updateDishLoc(DishLoc dishLoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(dishLoc);
	}


	@Override
	public DishLoc getDishLocById(int id, String language) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(DishLoc.class);
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("id", id)).
				add(Restrictions.eq("language", language));
		return (DishLoc) criteria.add(criterion).uniqueResult();


	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeDishLoc(int id, String language) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(DishLoc.class);
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("id", id)).
				add(Restrictions.eq("language", language));
		List<DishLoc> dll = criteria.add(criterion).list();
		if (dll.size() == 1) {	session.delete( dll.get(0));
			
		}
	}

}
