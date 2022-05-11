package Misc.L10;

import java.time.LocalDate;
import java.util.LinkedList;

public class CarPart implements Comparable<CarPart>{
	public static final LocalDate MIN_DATE = LocalDate.of(2000, 1, 1);
	public static final byte MAX_NAME_LENGTH = 64;
	public static final byte MIN_NAME_LENGTH = 3;
	public static final float MAX_PRICE = 99999.99f;
	
	public static LinkedList<CarPart> carParts = new LinkedList<CarPart>();
	
	public static boolean findId(int id) {
		if (id < 1)
			return false;
		for(final var i : carParts) {
			if (i.id == id)
				return true;
		}
		return false;
	}
	
	protected int id;
	protected String name;
	protected LocalDate date;
	protected float price;
	protected short count;
	
	public CarPart(int newId, String newName, LocalDate newDate, float newPrice, short newCount) throws Exception {
		if (newId < 1 && findId(newId) == true)
			throw new Exception("Некоректно задано порядковий номер");
		if (setName(newName) == false)
			throw new Exception("Некоректно задано назву запчастини");
		else if (setDate(newDate) == false)
			throw new Exception("Некоректно задано назву запчастини");
		else if (setPrice(newPrice) == false)
			throw new Exception("Некоректно задано назву запчастини");
		else if (setCount(newCount) == false)
			throw new Exception("Некоректно задано назву запчастини");
		id = newId;
	}
	
	
	public boolean setName(String newName) {
		if (newName == null || newName.length() < MIN_NAME_LENGTH || newName.length() > MAX_NAME_LENGTH)
			return false;
		name = newName;
		return true;
	}
	public boolean setDate(LocalDate newDate) {
		if (newDate == null || newDate.isBefore(MIN_DATE) || newDate.isAfter(LocalDate.now()))
			return false;
		date = newDate;
		return true;
	}
	public boolean setPrice(float newPrice) {
		if (newPrice <= 0 || newPrice > MAX_PRICE)
			return false;
		price = newPrice;
		return true;
	}
	public boolean setCount(short newCount) {
		if (newCount < 0)
			return false;
		count = newCount;
		return true;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public LocalDate getDate() {
		return date;
	}
	public float getPrice() {
		return price;
	}
	public short getCount() {
		return count;
	}

	@Override
	public int compareTo(CarPart other) {
		return id - other.id;
	}
}
