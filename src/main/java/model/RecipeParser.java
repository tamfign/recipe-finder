package model;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RecipeParser {

	public static ArrayList<Recipe> parse(String str) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(str,
				objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Recipe.class));
	}

}
