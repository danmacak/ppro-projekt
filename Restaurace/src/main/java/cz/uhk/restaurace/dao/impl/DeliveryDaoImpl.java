package cz.uhk.restaurace.dao.impl;

import cz.uhk.restaurace.dao.DeliveryDao;
import cz.uhk.restaurace.model.Delivery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dann on 22.11.2014.
 */

@Repository
public class DeliveryDaoImpl implements DeliveryDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBooking(Delivery delivery) {
        sessionFactory.getCurrentSession().persist(delivery);
    }

    @Override
    public void updateBooking(Delivery delivery) {
        sessionFactory.getCurrentSession().update(delivery);
    }

    @Override
    public List<Delivery> listBooking() {
        return sessionFactory.getCurrentSession().createQuery("FROM Delivery").list();
    }

    @Override
    public Delivery getBookingById(int id) {
        return (Delivery)sessionFactory.getCurrentSession().get(Delivery.class, id);
    }

    @Override
    public void removeBooking(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Delivery.class, id));
    }
}
