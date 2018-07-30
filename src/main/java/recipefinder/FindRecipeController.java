package recipefinder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FindRecipeController {

	@PostMapping("/findRecipe")
	public String findRecipe(@RequestParam(name = "fridgeList", required = true, defaultValue = "") String fridgeList,
			@RequestParam(name = "recipes", required = true, defaultValue = "") String recipes, Model model) {
		model.addAttribute("name", fridgeList);
		return "greeting";
	}

	// TODO map error
}