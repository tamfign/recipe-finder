package model;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * Recipe parser
 * 
 * @author Andrew Y
 *
 */
public class RecipeParser {

	/**
	 * 
	 * Convert string to recipes in array list.
	 * 
	 * @param str String to be converted.
	 * @return List of recipe objects.
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * 
	 */
	public static ArrayList<Recipe> parse(String str)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(str, objectMapper.getTypeFactory()
				.constructCollectionType(ArrayList.class, Recipe.class));
	}

}
