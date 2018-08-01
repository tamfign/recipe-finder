package recipefinder;

import java.util.ArrayList;
import java.util.Date;

import model.Item;
import model.Recipe;

public class RecipeFinder {

	private final static String DEFAULT_RET = "Order Takeout";
	private Date closestUseBy = null;
	private Recipe prefer = null;

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
						if (isClosestToExpired(itemInFridge)) {
							recipeClosestUseBy = itemInFridge.getUseBy();
						}
						count++;
					}
				}
			}

			if (count == recipe.getIngredients().size()) {
				if (recipeClosestUseBy != null) {
					this.closestUseBy = recipeClosestUseBy;
					this.prefer = recipe;
				}
			}
		}

		if (this.prefer != null) {
			ret = this.prefer.getName();
		}
		return ret;
	}

	private boolean isClosestToExpired(Item item) {
		if (this.closestUseBy == null)
			return true;

		return this.closestUseBy.compareTo(item.getUseBy()) > 0;
	}

	private boolean isItemMatch(Item itemNeeded, Item itemInFridge) {
		if (itemNeeded == null || itemInFridge == null)
			return false;
		if (itemInFridge.isExpired())
			return false;

		return itemNeeded.getItem().equals(itemInFridge.getItem())
				&& itemNeeded.getAmount() < itemInFridge.getAmount();
	}
}
