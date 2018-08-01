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

	@PostMapping("/findRecipe")
	public String findRecipe(
			@RequestParam(name = "fridgeList", required = true, defaultValue = "") String fridgeListStr,
			@RequestParam(name = "recipes", required = true, defaultValue = "") String recipesStr,
			Model model) {

		String result = "";
		ArrayList<Recipe> recipeList = null;
		ArrayList<Item> itemInFridge = null;

		try {
			recipeList = RecipeParser.parse(recipesStr);
		} catch (Exception e) {
			// TODO Error page.
			e.printStackTrace();
		}

		try {
			itemInFridge = ItemParser.parse(fridgeListStr);
		} catch (Exception e) {
			// TODO Error page.
			e.printStackTrace();
		}

		result = new RecipeFinder().findRecipe(recipeList, itemInFridge);

		model.addAttribute("result", result);
		return "index";
	}

}