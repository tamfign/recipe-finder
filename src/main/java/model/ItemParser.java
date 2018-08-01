package model;

import java.text.ParseException;
import java.util.ArrayList;

public class ItemParser {

	public static ArrayList<Item> parse(String str) throws ParseException {
		ArrayList<Item> list = new ArrayList<Item>();
		String[] itemStrs = str.split("\\r?\\n");

		for (String itemStr : itemStrs) {
			Item item = Item.getItem(itemStr);
			list.add(item);
		}
		return list;
	}
}
