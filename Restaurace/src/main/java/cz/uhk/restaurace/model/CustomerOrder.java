package cz.uhk.restaurace.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;
import javax.persistence.Entity;

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
	@ManyToMany(mappedBy = "customerOrders")
	@JsonIgnore
	private Map<Integer, DishGeneral> orderedDishes = new HashMap<Integer, DishGeneral>();
	@ManyToMany(mappedBy = "customerOrders")
	@JsonIgnore
	private Map<String, DishGeneral> orderedTeppanyakiDishes = new HashMap<String, DishGeneral>();
	private Boolean processed = false;
	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	@Cascade(CascadeType.ALL)
	private TempCustomerInfo tempCustomerInfo;

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
	public Boolean getProcessed() {
		return processed;
	}
	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}
	public TempCustomerInfo getTempCustomerInfo() {
		return tempCustomerInfo;
	}
	public void setTempCustomerInfo(TempCustomerInfo tempCustomerInfo) {
		this.tempCustomerInfo = tempCustomerInfo;
	}

	public BigDecimal getRegularTax() {
		return getTotalPrice().multiply(this.regularTax).setScale(2, RoundingMode.HALF_DOWN);
	}
	public BigDecimal getTotalPriceFieldValue(){
		return this.totalPrice;
	}

	/**
	 * Get the fucking total price of the cart
	 * @return
	 */
	public BigDecimal getTotalPrice() {
		BigDecimal total = new BigDecimal("0.00");
		for (Map.Entry<Integer, DishGeneral> entry : this.getOrderedDishes().entrySet()){
			total = total.add(new BigDecimal(entry.getValue().getAmount()).multiply(entry.getValue().getPrice()));
		}
		for (Map.Entry<String, DishGeneral> entry : this.getOrderedTeppanyakiDishes().entrySet()){
			for(Map.Entry<Integer, IngredientGeneral> ent : entry.getValue().getIngredients().entrySet()){
				BigDecimal p = new BigDecimal(ent.getValue().getGrams()).multiply(ent.getValue().getPricePerHundredGrams())
						.divide(new BigDecimal(100));
				total = total.add(p);
			}
		}
		return total.setScale(2, RoundingMode.CEILING);
	}

	public BigDecimal getValueOfTotalPriceField(){
		return this.totalPrice;
	}

	public BigDecimal getTotalPriceWithoutTax(){
		return getTotalPrice().multiply(new BigDecimal("1.00").subtract(this.regularTax)).setScale(2, RoundingMode.HALF_DOWN);
	}

	public int getNumberOfDishes() {
		int amount = 0;
		for (Map.Entry<Integer, DishGeneral> entry : this.getOrderedDishes().entrySet()){
			amount += entry.getValue().getAmount();
		}
		for(Map.Entry<String, DishGeneral> entry : this.getOrderedTeppanyakiDishes().entrySet()){
			amount += entry.getValue().getAmount();
		}
		return amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CustomerOrder order = (CustomerOrder) o;

		if (id != order.id) return false;
		if (delivery != null ? !delivery.equals(order.delivery) : order.delivery != null) return false;
		if (processed != null ? !processed.equals(order.processed) : order.processed != null) return false;
		if (totalPrice != null ? !totalPrice.equals(order.totalPrice) : order.totalPrice != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
		result = 31 * result + (delivery != null ? delivery.hashCode() : 0);
		result = 31 * result + (processed != null ? processed.hashCode() : 0);
		return result;
	}
}
