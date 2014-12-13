package cz.uhk.restaurace.service.impl;

import cz.uhk.restaurace.dao.DeliveryDao;
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
    public void addDelivery(Delivery delivery) {
        deliveryDao.addDelivery(delivery);
    }

    @Transactional
    @Override
    public void updateDelivery(Delivery delivery) {
        deliveryDao.updateDelivery(delivery);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Delivery> listDelivery() {
        return deliveryDao.listDelivery();
    }

    @Transactional(readOnly = true)
    @Override
    public Delivery getDeliveryById(int id) {
        return deliveryDao.getDeliveryById(id);
    }

    @Transactional
    @Override
    public void removeDelivery(int id) {
        deliveryDao.removeDelivery(id);
    }
}
