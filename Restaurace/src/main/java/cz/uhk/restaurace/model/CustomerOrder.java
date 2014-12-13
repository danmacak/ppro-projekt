package cz.uhk.restaurace.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

@Entity(name = "customer_order")
public class CustomerOrder {

	@Id
	@GeneratedValue
	private int id;
	private Date date;
	@Column(precision = 15, scale = 2)
	private BigDecimal totalPrice = new BigDecimal("0.00");
	private transient int numberOfDishes = 0;
	private transient int numOfTeppanyakis = 0;
	@Column(precision = 7, scale = 2)
	private transient BigDecimal regularTax = new BigDecimal("0.21");
	@ManyToOne
	private Delivery delivery;
	@ManyToOne
	@JoinColumn(name="customer_username")
	private User customer;
	@ManyToMany(mappedBy = "customerOrders", fetch = FetchType.EAGER)
	@JsonIgnore
	private Map<Integer, DishGeneral> orderedDishes = new HashMap<Integer, DishGeneral>();
	@ManyToMany(mappedBy = "customerOrders")
	@JsonIgnore
	private Map<String, DishGeneral> orderedTeppanyakiDishes = new HashMap<String, DishGeneral>();

	public CustomerOrder() {}
	
	public CustomerOrder(Date date, BigDecimal totalPrice, User customer) {
		this.date = date;
		this.totalPrice = totalPrice;
		this.customer = customer;
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
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	public Map<Integer, DishGeneral> getOrderedDishes() {
		return orderedDishes;
	}
	public void setOrderedDishes(Map<Integer, DishGeneral> orderedDishes) {
		this.orderedDishes = orderedDishes;
	}
	public void setNumberOfDishes(int numberOfDishes) {
		this.numberOfDishes = numberOfDishes;
	}
	public Delivery getDelivery() {
		return delivery;
	}
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	public void setRegularTax(BigDecimal regularTax) {
		this.regularTax = regularTax;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Map<String, DishGeneral> getOrderedTeppanyakiDishes() {
		return orderedTeppanyakiDishes;
	}

	public void setOrderedTeppanyakiDishes(Map<String, DishGeneral> orderedTeppanyakiDishes) {
		this.orderedTeppanyakiDishes = orderedTeppanyakiDishes;
	}

	public int getNumOfTeppanyakis() {
		return numOfTeppanyakis;
	}

	public void setNumOfTeppanyakis(int numOfTeppanyakis) {
		this.numOfTeppanyakis = numOfTeppanyakis;
	}

	public BigDecimal getRegularTax() {
		return getTotalPrice().multiply(this.regularTax).setScale(2, RoundingMode.HALF_DOWN);
	}


	public BigDecimal getTotalPrice() {
		BigDecimal total = new BigDecimal("0.00");
		for (Map.Entry<Integer, DishGeneral> entry : this.getOrderedDishes().entrySet()){
			total = total.add(new BigDecimal(entry.getValue().getAmount()).multiply(entry.getValue().getPrice()));
		}
		return total.setScale(2, RoundingMode.CEILING);
	}

	public BigDecimal getTotalPriceWithoutTax(){
		return getTotalPrice().multiply(new BigDecimal("1.00").subtract(this.regularTax)).setScale(2, RoundingMode.HALF_DOWN);
	}

	public int getNumberOfDishes() {
		int amount = 0;
		for (Map.Entry<Integer, DishGeneral> entry : this.getOrderedDishes().entrySet()){
			amount += entry.getValue().getAmount();
		}
		return amount;
	}
}
