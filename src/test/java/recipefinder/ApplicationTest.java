/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package recipefinder;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import model.Item;
import model.ItemParser;
import model.Recipe;
import model.RecipeParser;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FindRecipeController.class)
public class ApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void homePage() throws Exception {
		// N.B. jsoup can be useful for asserting HTML content
		mockMvc.perform(get("/index.html"))
				.andExpect(content().string(containsString("Item of Fridge")));
	}

	@Test
	public void recipeFinder() {
		RecipeFinder finder = new RecipeFinder();

		try {
			ArrayList<Item> items = ItemParser
					.parse(TestData.FRIDGE_ITEM_TEST_STREAM);
			ArrayList<Recipe> recipes = RecipeParser
					.parse(TestData.RECIPE_TEST_STREAM);

			String result = finder.findRecipe(recipes, items);
			assertEquals(result, "salad sandwich");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Find recipe failed");
		}
	}

	@Test
	public void findRecipe() throws Exception {
		mockMvc.perform(post("/findRecipe")).andExpect(content()
				.string(containsString("Item CSV cannnot be recognised")));
	}

	@Test
	public void findRecipeWithItem() throws Exception {
		mockMvc.perform(post("/findRecipe").param("fridgeList",
				TestData.FRIDGE_ITEM_TEST_STREAM))
				.andExpect(content().string(
						containsString("Recipes JSON cannot be recognised")));
	}

	@Test
	public void findRecipeWithBoth() throws Exception {
		mockMvc.perform(post("/findRecipe")
				.param("fridgeList", TestData.FRIDGE_ITEM_TEST_STREAM)
				.param("recipes", TestData.RECIPE_TEST_STREAM))
				.andExpect(content().string(
						containsString("salad sandwich")));
	}

	@Test
	public void errorMsg() throws Exception {
		mockMvc.perform(post("/error")).andExpect(content().string(
				containsString("Fail to find recipe")));
	}
}
