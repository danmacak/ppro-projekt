package cz.uhk.restaurace.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity(name = "ingredient_general")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class IngredientGeneral {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int kcal;
	private int fatGrams;
	private int saccharideGrams;
	private int proteinGrams;
	private transient int grams = 0;
	@Enumerated(EnumType.STRING)
	private IngredientType type;
	@ManyToMany(mappedBy="ingredients")
	@JsonIgnore
	private Collection<DishGeneral> dishes;
	@OneToMany
	@JoinColumn(name="id")
	private Collection<IngredientLoc> localizations;
	@Column(name="price")
	private BigDecimal pricePerHundredGrams;
	
	public IngredientGeneral() {}

	public IngredientGeneral(int kcal, int fatGrams, int saccharideGrams,
					  int proteinGrams, IngredientType type) {
		this.kcal = kcal;
		this.fatGrams = fatGrams;
		this.saccharideGrams = saccharideGrams;
		this.proteinGrams = proteinGrams;
		this.type = type;
	}

	public enum IngredientType{

		VEGETABLE("Zelenina", "vegetable"),
		MEAT("Maso", "meat"),
		FRUIT("Ovoce", "fruit"),
		SPICE("Koreni", "spice"),
		TEPPANYAKI("Teppanyaki", "teppanyaki");

		private String description;
		private String url;

		IngredientType(String description, String url) {
			this.description = description;
			this.url = url;
		}

		public String getUrl() {
			return url;
		}

		public String getDescription() {
			return description;
		}
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

	public int getGrams() {
		return grams;
	}

	public void setGrams(int grams) {
		this.grams = grams;
	}

	public BigDecimal getPricePerHundredGrams() {
		return pricePerHundredGrams;
	}

	public void setPricePerHundredGrams(BigDecimal pricePerHundredGrams) {
		this.pricePerHundredGrams = pricePerHundredGrams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
