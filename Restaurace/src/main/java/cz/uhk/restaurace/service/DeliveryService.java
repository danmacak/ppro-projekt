package cz.uhk.restaurace.service;

import cz.uhk.restaurace.model.Delivery;

import java.util.List;

/**
 * Created by dann on 22.11.2014.
 */
public interface DeliveryService {
    public void addBooking(Delivery delivery);
    public void updateBooking(Delivery delivery);
    public List<Delivery> listBooking();
    public Delivery getBookingById(int id);
    public void removeBooking(int id);
}
