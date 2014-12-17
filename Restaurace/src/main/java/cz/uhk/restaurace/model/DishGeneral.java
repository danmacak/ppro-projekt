package cz.uhk.restaurace.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class DishGeneral {
	
	@Id
	@GeneratedValue
	private int id;
	private transient String name;
	private transient int amount = 1;
	@Column(precision = 15, scale = 2)
	private BigDecimal price;
	private int fatGrams;
	private int kcal;
	private int saccharideGrams;
	private int proteinGrams;
	@Enumerated(EnumType.STRING)
	private DishCategory dishCategory;
	@ManyToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	@JsonIgnore
	private Map<Integer, IngredientGeneral> ingredients;
	@ManyToMany(cascade={CascadeType.ALL})
	@JsonIgnore
	private Collection<CustomerOrder> customerOrders;
	private transient DishLoc dishLoc;

	public DishGeneral() {}

	public DishGeneral(String name, String description, BigDecimal price, int fatGrams, int kcal,
				int saccharideGrams, int proteinGrams, DishCategory dishCategory) {
		this.price = price;
		this.fatGrams = fatGrams;
		this.kcal = kcal;
		this.saccharideGrams = saccharideGrams;
		this.proteinGrams = proteinGrams;
		this.dishCategory = dishCategory;
	}

	public enum DishCategory{
		MAIN("dish"), DRINK("drink"), APPETIZER("appetizer"), DESSERT("dessert"), TEPPANYAKI("teppanyaki");

		DishCategory(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getFatGrams() {
		return fatGrams;
	}
	public void setFatGrams(int fatGrams) {
		this.fatGrams = fatGrams;
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	public int getSaccharideGrams() {
		return saccharideGrams;
	}
	public void setSaccharideGrams(int saccharideGrams) {
		this.saccharideGrams = saccharideGrams;
	}
	public int getProteinGrams() {
		return proteinGrams;
	}
	public void setProteinGrams(int proteinGrams) {
		this.proteinGrams = proteinGrams;
	}
	public DishCategory getDishCategory() {
		return dishCategory;
	}
	public void setDishCategory(DishCategory dishCategory) {
		this.dishCategory = dishCategory;
	}
	public Collection<CustomerOrder> getCustomerOrders() {
		return customerOrders;
	}
	public void setCustomerOrders(Collection<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Map<Integer, IngredientGeneral> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Map<Integer, IngredientGeneral> ingredients) {
		this.ingredients = ingredients;
	}
	public int getId() {
		return id;
	}
	public DishLoc getDishLoc() {
		return dishLoc;
	}
	public void setDishLoc(DishLoc dishLoc) {
		this.dishLoc = dishLoc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCustomDishPrice(){
		BigDecimal total = new BigDecimal("0.00");
		for(Map.Entry<Integer, IngredientGeneral> entry : this.getIngredients().entrySet()){
			total = total.add(new BigDecimal(entry.getValue().getGrams()).multiply(entry.getValue().getPricePerHundredGrams())
						.divide(new BigDecimal(100)));
		}
		return total;
	}
}
