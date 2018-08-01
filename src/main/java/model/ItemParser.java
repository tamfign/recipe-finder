package model;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * 
 * Item Parser
 * 
 * @author Andrew Y
 *
 */

public class ItemParser {

	/**
	 * Convert string to array list of items.
	 * 
	 * @param str String to be converted.
	 * @return List of items converted.
	 * @throws ParseException
	 * 
	 */
	public static ArrayList<Item> parse(String str) throws ParseException {
		ArrayList<Item> list = new ArrayList<Item>();
		String[] itemStrs = str.split("\\r?\\n");

		for (String itemStr : itemStrs) {
			list.add(Item.genItem(itemStr));
		}
		return list;
	}
}
