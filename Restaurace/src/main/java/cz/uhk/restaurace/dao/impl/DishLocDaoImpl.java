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
		session.persist(dishLoc);

	}

	@Override
	public void updateDishLoc(DishLoc dishLoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(dishLoc);
	}


	@SuppressWarnings("unchecked")
	@Override
	public DishLoc getDishLocById(int id, String language) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(DishLoc.class);
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("id", id)).
				add(Restrictions.eq("language", language));
		List<DishLoc> dll = criteria.add(criterion).list();
		if (dll.size() == 1) {	return dll.get(0);
			
		} else {
			return null;

		}

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

	
	@SuppressWarnings("unchecked")
	@Override
	public List<DishLoc> listDishesLoc(String lang) {
		Session session = this.sessionFactory.getCurrentSession();
		List<DishLoc> dishLocList = session.createQuery("from DishLoc where language='"+lang+"'and id in (select id from DishGeneral where not(dishCategory = 'DRINK') ) order by id").list();
		
		return dishLocList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DishLoc> listDrinksLoc(String lang) {
		Session session = this.sessionFactory.getCurrentSession();
		List<DishLoc> dishLocList = session.createQuery("from DishLoc where language='"+lang+"'and id in (select id from DishGeneral where dishCategory = 'DRINK' ) order by id").list();
		return dishLocList;
	}

}
