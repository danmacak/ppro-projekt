package cz.uhk.restaurace.service.authentication.user;

import cz.uhk.restaurace.model.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by dann on 20.11.2014.
 * Class whose objects store user data used in various views
 */
public class UserDetails extends User{

    private static final long serialVersionUID = 32L;

    private String surname;
    private String firstname;
    private String email;
    private String telephone;
    private Address address;
    private Collection<CustomerOrder> orders;
    private Collection<Shift> shifts;
    private Collection<Role> roles;
    private Collection<Booking> bookings;

    public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                       String surname, String firstname, String email, String telephone, Address address) {
        this(username, password, authorities);
        this.address = address;
        this.firstname = firstname;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;

    }

    public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                       String surname, String firstname, String email, String telephone, Address address,
                       Collection<CustomerOrder> orders, Collection<Shift> shifts,
                       Collection<Role> roles, Collection<Booking> bookings) {

        this(username, password, authorities, surname, firstname, email, telephone, address);
        this.orders = orders;
        this.shifts = shifts;
        this.roles = roles;
        this.bookings = bookings;

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(Collection<CustomerOrder> orders) {
        this.orders = orders;
    }

    public Collection<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Collection<Shift> shifts) {
        this.shifts = shifts;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Collection<Booking> bookings) {
        this.bookings = bookings;
    }
}
