package recipefinder;

public class TestData {
	public static final String RECIPE_TEST_STREAM = "[" + "{"
			+ "\"name\": \"grilled cheese on toast\", \"ingredients\": ["
			+ "{ \"item\":\"bread\", \"amount\":\"2\", \"unit\":\"slices\"}, { \"item\":\"cheese\", \"amount\":\"2\", \"unit\":\"slices\"}"
			+ "]" + "}" + "," + "{"
			+ "\"name\": \"salad sandwich\", \"ingredients\": ["
			+ "{ \"item\":\"bread\", \"amount\":\"2\", \"unit\":\"slices\"},"
			+ "{ \"item\":\"mixed salad\", \"amount\":\"100\", \"unit\":\"grams\"}"
			+ "]" + "}" + "]";;

	public static final String FRIDGE_ITEM_TEST_STREAM = "bread,10,slices,25/12/2019\r\n"
			+ "cheese,10,slices,25/12/2019\r\n"
			+ "butter,250,grams,25/12/2019\r\n peanut butter,250,grams,2/12/2019\r\n mixed salad,150,grams,26/12/2018";
}
