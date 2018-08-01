package recipefinder;

import java.util.ArrayList;
import java.util.Date;

import model.Item;
import model.Recipe;

/**
 * 
 * Logic of find the recipe matched.
 * 
 * @author Andrew Y
 *
 */
public class RecipeFinder {

	private final static String DEFAULT_RET = "Order Takeout";
	private Date closestUseBy = null;
	private Recipe prefer = null;

	/**
	 * Find recipe basing on the rules.
	 * 
	 * @param recipeList List of recipes.
	 * @param itemList   List of items in the fridge.
	 * @return Recipe's name if match. Otherwise default message.
	 * 
	 */
	public String findRecipe(ArrayList<Recipe> recipeList,
			ArrayList<Item> itemList) {
		String ret = DEFAULT_RET;

		if (recipeList == null || itemList == null)
			return ret;

		for (Recipe recipe : recipeList) {
			int count = 0;
			Date recipeClosestUseBy = null;

			for (Item itemNeeded : recipe.getIngredients()) {
				for (Item itemInFridge : itemList) {
					if (isItemMatch(itemNeeded, itemInFridge)) {
						/*
						 * Item use-by is closer than previous recipe's
						 */
						if (isClosestToExpired(itemInFridge)) {
							recipeClosestUseBy = itemInFridge.getUseBy();
						}
						count++;
					}
				}
			}

			// All items in fridge match this recipe.
			if (count == recipe.getIngredients().size()) {
				if (recipeClosestUseBy != null) {
					this.closestUseBy = recipeClosestUseBy;
					this.prefer = recipe;
				}
			}
		}

		// This is a matched recipe.
		if (this.prefer != null) {
			ret = this.prefer.getName();
		}
		return ret;
	}

	/**
	 * Check whether item is closer to expired then preferred recipe.
	 * 
	 * @param item Item object to be checked.
	 * @return True if item use-by closer then preferred recipe. Otherwise
	 *         False.
	 * 
	 */
	private boolean isClosestToExpired(Item item) {
		if (this.closestUseBy == null)
			return true;

		return this.closestUseBy.compareTo(item.getUseBy()) > 0;
	}

	/**
	 * Check whether item match with recipe.
	 * 
	 * @param itemNeeded   Item needed by recipe.
	 * @param itemInFridge Item in the fridge.
	 * @return True if item name matched and amount more than needed.
	 * 
	 */
	private boolean isItemMatch(Item itemNeeded, Item itemInFridge) {
		if (itemNeeded == null || itemInFridge == null)
			return false;
		if (itemInFridge.isExpired())
			return false;

		return itemNeeded.getItem().equals(itemInFridge.getItem())
				&& itemNeeded.getAmount() <= itemInFridge.getAmount();
	}
}
