package cz.uhk.restaurace.dao.impl;

import java.util.List;

import cz.uhk.restaurace.dao.BookingDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cz.uhk.restaurace.model.Booking;

@Repository
public class BookingDaoImpl implements BookingDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addBooking(Booking booking) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(booking);

	}

	@Override
	public void updateBooking(Booking booking) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(booking);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> listBooking() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Booking> rl = session.createQuery("from Booking").list();
		return rl;
	}

	@Override
	public Booking getBookingById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Booking a = (Booking) session.get(Booking.class, new Integer(id));
		return a;
	}

	@Override
	public void removeBooking(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Booking a = (Booking) session.get(Booking.class, new Integer(id));
		if (a != null) {
			session.delete(a);
		}

	}

	@Override
	public List<Booking> getBookingsByUsername(String username) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Booking.class);
		return criteria.add(Restrictions.eq("customer.username", username)).list();
	}
}
