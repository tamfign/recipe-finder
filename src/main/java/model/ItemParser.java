package model;

import java.text.ParseException;
import java.util.ArrayList;

public class ItemParser {

	private static ItemParser _instance;

	private ItemParser() {
	}

	public static ItemParser getInstance() {
		if (_instance == null)
			_instance = new ItemParser();
		return _instance;
	}

	public ArrayList<Item> parse(String str) {
		ArrayList<Item> list = new ArrayList<Item>();
		String[] itemStrs = str.split("\\r?\\n");

		for (String itemStr : itemStrs) {
			try {
				Item item = Item.getItem(itemStr);
				list.add(item);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
