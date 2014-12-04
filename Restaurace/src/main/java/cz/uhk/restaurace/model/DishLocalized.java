package cz.uhk.restaurace.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public class DishLocalized{

	private int id;

	private int amount = 1;
	private BigDecimal price;
	private int fatGrams;
	private int kcal;
	private int saccharideGrams;
	private int proteinGrams;
	private DishGeneral.DishCategory dishCategory;
	private Map<Integer, IngredientGeneral> ingredients;
	private Map<Integer, IngredientLocalized> ingredientsLocalized;
	private Collection<CustomerOrder> customerOrders;
	private String language;
	private String name;
	private String description;
	
	public DishLocalized() {
		// TODO Auto-generated constructor stub
	}
	public DishLocalized(DishGeneral dish, DishLoc dishLoc){
		amount = dish.getAmount();
		price = dish.getPrice();
		fatGrams = dish.getFatGrams();
		kcal = dish.getKcal();
		saccharideGrams = dish.getSaccharideGrams();
		proteinGrams = dish.getProteinGrams();
		dishCategory = dish.getDishCategory();
		customerOrders = dish.getCustomerOrders();
		language = dishLoc.getLanguage();
		name = dishLoc.getName();
		description = dishLoc.getDescription();
		ingredients = dish.getIngredients();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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
	public DishGeneral.DishCategory getDishCategory() {
		return dishCategory;
	}
	public void setDishCategory(DishGeneral.DishCategory dishCategory) {
		this.dishCategory = dishCategory;
	}
	public Map<Integer, IngredientGeneral> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Map<Integer, IngredientGeneral> ingredients) {
		this.ingredients = ingredients;
	}
	public Collection<CustomerOrder> getCustomerOrders() {
		return customerOrders;
	}
	public void setCustomerOrders(Collection<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setIngredientsLocalized(
			Map<Integer, IngredientLocalized> ingredientsLocalized) {
		this.ingredientsLocalized = ingredientsLocalized;
	}
	public Map<Integer, IngredientLocalized> getIngredientsLocalized() {
		return ingredientsLocalized;
	}


}
