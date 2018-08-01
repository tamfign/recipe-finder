package model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import recipefinder.TestData;

@RunWith(SpringRunner.class)
public class ModelTest {
	private final SimpleDateFormat formatter = new SimpleDateFormat(
			"dd/MM/yyyy");

	@Test
	public void parseItem() {
		try {
			Item item = Item.getItem("bread,10,slices,25/12/2014");

			assertEquals(item.getItem(), "bread");
			assertEquals(item.getAmount(), 10);
			assertEquals(item.getUnit(), Item.Unit.slices);

			assertTrue(item.getUseBy()
					.compareTo(formatter.parse("25/12/2014")) == 0);
			assertTrue(item.isExpired());
		} catch (Exception e) {
			fail("Fridge Item parse fail");
		}
	}

	@Test
	public void parseItemList() {
		ArrayList<Item> list = null;
		try {
			list = ItemParser.parse(TestData.FRIDGE_ITEM_TEST_STREAM);
		} catch (ParseException e1) {
			e1.printStackTrace();
			fail("Fridge Item parse fail");
		}

		try {
			assertEquals(list.size(), 5);
			assertEquals(list.get(4).getItem(), "mixed salad");
			assertTrue(list.get(4).getUseBy()
					.compareTo(formatter.parse("26/12/2018")) == 0);
		} catch (Exception e) {
			fail("Fridge Item parse fail");
		}
	}

	@Test
	public void parseRecipe() {
		try {
			ArrayList<Recipe> recipes = RecipeParser
					.parse(TestData.RECIPE_TEST_STREAM);

			assertEquals(recipes.size(), 2);
			assertEquals(recipes.get(1).getName(), "salad sandwich");
			assertEquals(recipes.get(0).getIngredients().get(1).getItem(),
					"cheese");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Recipe parse fail");
		}
	}
}
