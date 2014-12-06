package cz.uhk.restaurace.model;

import java.math.BigDecimal;
import java.util.Collection;


import cz.uhk.restaurace.model.IngredientGeneral.IngredientType;
import org.codehaus.jackson.annotate.JsonIgnore;

public class IngredientLocalized {

	private int id;
	private int kcal;
	private int fatGrams;
	private int saccharideGrams;
	private int proteinGrams;
	private transient int grams = 0;
	private IngredientType type;
	private Collection<DishGeneral> dishes;
	private String language;
	private String name;
	private BigDecimal pricePerHundredGrams;
	
	public IngredientLocalized() {
		// TODO Auto-generated constructor stub
	}
	
	public IngredientLocalized(IngredientGeneral ingredientGeneral, IngredientLoc ingredientLoc) {
		id = ingredientGeneral.getId();
		kcal = ingredientGeneral.getKcal();
		fatGrams = ingredientGeneral.getFatGrams();
		saccharideGrams = ingredientGeneral.getSaccharideGrams();
		proteinGrams = ingredientGeneral.getProteinGrams();
		grams = ingredientGeneral.getGrams();
		type = ingredientGeneral.getType();
		dishes = ingredientGeneral.getDishes();
		language = ingredientLoc.getLanguage();
		name = ingredientLoc.getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public int getFatGrams() {
		return fatGrams;
	}

	public void setFatGrams(int fatGrams) {
		this.fatGrams = fatGrams;
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

	public int getGrams() {
		return grams;
	}

	public void setGrams(int grams) {
		this.grams = grams;
	}

	public IngredientType getType() {
		return type;
	}

	public void setType(IngredientType type) {
		this.type = type;
	}

	public Collection<DishGeneral> getDishes() {
		return dishes;
	}

	public void setDishes(Collection<DishGeneral> dishes) {
		this.dishes = dishes;
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

	public BigDecimal getPricePerHundredGrams() {
		return pricePerHundredGrams;
	}

	public void setPricePerHundredGrams(BigDecimal pricePerHundredGrams) {
		this.pricePerHundredGrams = pricePerHundredGrams;
	}
}
