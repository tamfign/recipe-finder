package recipefinder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FindRecipeController {

    @PostMapping("/findRecipe")
    public String findRecipe(@RequestParam(fridgeList="fridgeList", required=true, defaultValue="") String fridgeList, 
		             @RequestParam(recipes="recipes", required=true, defaultValue="") recipes, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    //TODO map error
}
