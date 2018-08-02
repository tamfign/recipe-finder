package recipefinder;

import java.util.ArrayList;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Item;
import model.ItemParser;
import model.Recipe;
import model.RecipeParser;

@Controller
public class FindRecipeController implements ErrorController {

	private static final String LABEL_RECOMMEDATION = "Recommedation of Tonight:   ";
	private static final String RECIPE_ERROR = "Recipes JSON cannot be recognised. Please check the format";
	private static final String ITEM_ERROR = "Item CSV cannnot be recognised. Please check the format";
	private static final String DEFAULT_ERROR = "Fail to find recipe. Please check your input";
	private static final String ERROR_PATH = "/error";

	@PostMapping("/findRecipe")
	public String findRecipe(
			@RequestParam(name = "fridgeList", required = true, defaultValue = "") String fridgeListStr,
			@RequestParam(name = "recipes", required = true, defaultValue = "") String recipesStr,
			Model model) {

		final String nextPage = "result";
		final String RESULT_ATTR = "result";
		ArrayList<Recipe> recipeList = null;
		ArrayList<Item> itemInFridge = null;

		/*
		 * Check and analyse the csv of items. Pop up error message if exception
		 * occurs.
		 */
		try {
			itemInFridge = ItemParser.parse(fridgeListStr);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute(RESULT_ATTR, ITEM_ERROR);
			return nextPage;
		}

		/*
		 * Check and analyse the JSON of recipes. Pop up error message if
		 * exception occurs.
		 */
		try {
			recipeList = RecipeParser.parse(recipesStr);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute(RESULT_ATTR, RECIPE_ERROR);
			return nextPage;
		}

		/*
		 * Find the recipe basing on the rules. Pop up the result message.
		 */
		model.addAttribute(RESULT_ATTR, LABEL_RECOMMEDATION
				+ new RecipeFinder().findRecipe(recipeList, itemInFridge));
		return nextPage;
	}

	@RequestMapping(value = ERROR_PATH)
	public String error(Model model) {
		final String nextPage = "result";
		final String RESULT_ATTR = "result";

		model.addAttribute(RESULT_ATTR, DEFAULT_ERROR);
		return nextPage;
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}