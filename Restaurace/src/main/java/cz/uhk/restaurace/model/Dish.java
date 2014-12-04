package cz.uhk.restaurace.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class Dish implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String name;
	private String description;
	private transient int amount = 1;
	@Column(precision = 15, scale = 2)
	private BigDecimal price = new BigDecimal("0.00");
	private int fatGrams;
	private int kcal;
	private int saccharideGrams;
	private int proteinGrams;
	@Enumerated(EnumType.STRING)
	private DishCategory dishCategory;
	@ManyToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	private Map<Integer, Ingredient> ingredients;
	@ManyToMany(cascade={CascadeType.ALL})
	@JsonIgnore
	private Collection<CustomerOrder> customerOrders;

	public Dish() {}

	public Dish(String name, String description, BigDecimal price, int fatGrams, int kcal,
				int saccharideGrams, int proteinGrams, DishCategory dishCategory) {
		this.name = name;
		this.price = price;
		this.fatGrams = fatGrams;
		this.kcal = kcal;
		this.saccharideGrams = saccharideGrams;
		this.proteinGrams = proteinGrams;
		this.dishCategory = dishCategory;
		this.description = description;
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		if(!DishCategory.TEPPANYAKI.equals(this.dishCategory)) {
			return this.price;
		}else{
			BigDecimal price = new BigDecimal("0.00");
			for (Map.Entry<Integer, Ingredient> entry : this.getIngredients().entrySet()){
				price = price.add((entry.getValue().getPricePerHundredGrams())
						.multiply(new BigDecimal(entry.getValue().getGrams())).divide(new BigDecimal("100.00")));
			}
			return price;
		}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Map<Integer, Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Map<Integer, Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}
