package model;

import java.text.ParseException;
import java.util.ArrayList;

public class FridgeItemParser {

	private static FridgeItemParser _instance;

	private FridgeItemParser() {
	}

	public static FridgeItemParser getInstance() {
		if (_instance == null)
			_instance = new FridgeItemParser();
		return _instance;
	}

	public ArrayList<FridgeItem> parse(String str) {
		ArrayList<FridgeItem> list = new ArrayList<FridgeItem>();
		String[] itemStrs = str.split("\\r?\\n");

		for (int i = 0; i < itemStrs.length; i++) {
			try {
				FridgeItem item = FridgeItem.getItem(itemStrs[i]);
				list.add(item);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
