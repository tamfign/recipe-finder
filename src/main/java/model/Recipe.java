package model;

import java.util.ArrayList;

/**
 * 
 * Object of Recipe
 * 
 * @author Andrew Y
 *
 */
public class Recipe {
	private String name;
	private ArrayList<Item> ingredients;

	public void setName(String name) {
		this.name = name;
	}

	public void setIngredients(ArrayList<Item> ingredients) {
		this.ingredients = ingredients;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Item> getIngredients() {
		return ingredients;
	}
}
