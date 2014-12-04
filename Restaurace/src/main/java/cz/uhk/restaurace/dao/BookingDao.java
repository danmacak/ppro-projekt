package cz.uhk.restaurace.dao;

import java.util.List;

import cz.uhk.restaurace.model.Booking;

public interface BookingDao {
	public void addBooking(Booking booking);
	public void updateBooking(Booking booking);
	public List<Booking> listBooking();
	public Booking getBookingById(int id);
	public void removeBooking(int id);

}
