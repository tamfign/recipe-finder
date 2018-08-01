package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {

	public enum Unit {
		of, grams, ml, slices
	}

	private String item;
	private int amount;
	private Unit unit;
	private Date useBy;

	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"dd/MM/yyyy");

	public Item() {
	}

	private Item(String item, int amount, Unit unit, Date useBy) {
		this.item = item;
		this.amount = amount;
		this.unit = unit;
		this.useBy = useBy;
	}

	public static Item getItem(String str) throws ParseException {
		String[] params = str.trim().split(",");

		if (params.length != 4)
			throw new ParseException("Fail to parse string to item", 0);

		return new Item(params[0], Integer.parseInt(params[1]),
				parseUnit(params[2]), dateFormatter.parse(params[3]));
	}

	private static Unit parseUnit(String str) throws ParseException {
		switch (str) {
		case "of":
			return Unit.of;
		case "grams":
			return Unit.grams;
		case "ml":
			return Unit.ml;
		case "slices":
			return Unit.slices;
		default:
			throw new ParseException("Fail to prase unit", 0);
		}
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public void setUseBy(Date useBy) {
		this.useBy = useBy;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItem() {
		return item;
	}

	public int getAmount() {
		return amount;
	}

	public Unit getUnit() {
		return unit;
	}

	public Date getUseBy() {
		return useBy;
	}

	public boolean isExpired() {
		return useBy.compareTo(new Date()) < 0;
	}
}
