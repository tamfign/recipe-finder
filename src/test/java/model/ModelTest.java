package model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ModelTest {
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void parseItem() {
		try {
			Item item = Item.getItem("bread,10,slices,25/12/2014");

			assertEquals(item.getItem(), "bread");
			assertEquals(item.getAmount(), 10);
			assertEquals(item.getUnit(), Item.Unit.slices);

			assertTrue(item.getUseBy().compareTo(formatter.parse("25/12/2014")) == 0);
		} catch (Exception e) {
			fail("Fridge Item parse fail");
		}
	}

	@Test
	public void parseItemList() {
		final String stream = "bread,10,slices,25/12/2014\r\n" + "cheese,10,slices,25/12/2014\r\n"
				+ "butter,250,grams,25/12/2014\r\n peanut butter,250,grams,2/12/2014\r\n mixed salad,150,grams,26/12/2013";
		ArrayList<Item> list = ItemParser.getInstance().parse(stream);

		assertEquals(list.size(), 5);
		assertEquals(list.get(4).getItem(), "mixed salad");
		try {
			assertTrue(list.get(4).getUseBy().compareTo(formatter.parse("26/12/2013")) == 0);
		} catch (ParseException e) {
			fail("Fridge Item parse fail");
		}
	}

	@Test
	public void parseRecipe() {
		final String steam = "[\r\n" + "{" + "\"name\": \"grilled cheese on toast\", \"ingredients\": ["
				+ "{ \"item\":\"bread\", \"amount\":\"2\", \"unit\":\"slices\"}, { \"item\":\"cheese\", \"amount\":\"2\", \"unit\":\"slices\"}"
				+ "]" + "}" + "," + "{" + "\"name\": \"salad sandwich\", \"ingredients\": ["
				+ "{ \"item\":\"bread\", \"amount\":\"2\", \"unit\":\"slices\"},"
				+ "{ \"item\":\"mixed salad\", \"amount\":\"100\", \"unit\":\"grams\"}" + "]" + "}" + "]";

		try {
			ArrayList<Recipe> recipes = RecipeParser.parse(steam);

			assertEquals(recipes.size(), 2);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Recipe parse fail");
		}
	}
}
