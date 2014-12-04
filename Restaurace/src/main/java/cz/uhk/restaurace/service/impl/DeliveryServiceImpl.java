package cz.uhk.restaurace.service.impl;

import cz.uhk.restaurace.dao.DeliveryDao;
import cz.uhk.restaurace.dao.impl.DeliveryDaoImpl;
import cz.uhk.restaurace.model.Delivery;
import cz.uhk.restaurace.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dann on 22.11.2014.
 */

@Service
public class DeliveryServiceImpl implements DeliveryService{

    @Autowired
    private DeliveryDao deliveryDao;

    @Transactional
    @Override
    public void addBooking(Delivery delivery) {
        deliveryDao.addBooking(delivery);
    }

    @Transactional
    @Override
    public void updateBooking(Delivery delivery) {
        deliveryDao.updateBooking(delivery);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Delivery> listBooking() {
        return deliveryDao.listBooking();
    }

    @Transactional(readOnly = true)
    @Override
    public Delivery getBookingById(int id) {
        return deliveryDao.getBookingById(id);
    }

    @Transactional
    @Override
    public void removeBooking(int id) {
        deliveryDao.removeBooking(id);
    }
}
