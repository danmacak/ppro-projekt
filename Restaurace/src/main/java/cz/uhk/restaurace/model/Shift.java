package cz.uhk.restaurace.model;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Shift {
	
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	private int sinceHour;
	private int toHour;
	@ManyToMany(mappedBy="shifts")
	private Collection<User> employees;
	
	public Shift() {}

	public Shift(int id, Date date, int sinceHour, int toHour) {
		super();
		this.id = id;
		this.date = date;
		this.sinceHour = sinceHour;
		this.toHour = toHour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSinceHour() {
		return sinceHour;
	}

	public void setSinceHour(int sinceHour) {
		this.sinceHour = sinceHour;
	}

	public int getToHour() {
		return toHour;
	}

	public void setToHour(int toHour) {
		this.toHour = toHour;
	}
	
	
}
