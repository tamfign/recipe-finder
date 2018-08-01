package recipefinder;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Item;
import model.ItemParser;
import model.Recipe;
import model.RecipeParser;

@Controller
public class FindRecipeController {

	private static final String LABEL_RECOMMEDATION = "Recommedation of Tonight:   ";
	private static final String RECIPE_ERROR = "Recipes cannot be recognised. Please check the format";
	private static final String ITEM_ERROR = "Item CSV cannnot be recognised. Please check the format";

	@PostMapping("/findRecipe")
	public String findRecipe(
			@RequestParam(name = "fridgeList", required = true, defaultValue = "") String fridgeListStr,
			@RequestParam(name = "recipes", required = true, defaultValue = "") String recipesStr,
			Model model) {

		final String nextPage = "result";
		final String RESULT_ATTR = "result";
		ArrayList<Recipe> recipeList = null;
		ArrayList<Item> itemInFridge = null;

		try {
			itemInFridge = ItemParser.parse(fridgeListStr);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute(RESULT_ATTR, ITEM_ERROR);
			return nextPage;
		}

		try {
			recipeList = RecipeParser.parse(recipesStr);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute(RESULT_ATTR, RECIPE_ERROR);
			return nextPage;
		}

		model.addAttribute("result", LABEL_RECOMMEDATION
				+ new RecipeFinder().findRecipe(recipeList, itemInFridge));
		return nextPage;
	}

}