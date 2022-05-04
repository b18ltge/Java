package Misc.L8_1;

import java.time.LocalDate;

public class Car implements Comparable<Car>, IVehicle {
	private Brand brand;
	private Color color;
	private int number;
	private short year;
	private String ownerInfo;
	
	public Car(Brand newBrand, Color newColor, int newNumber, short newYear, String newOwnerInfo) throws Exception {
		if (setBrand(newBrand) == false)
			throw new Exception("Некоректно задано марку авто;");
		else if (setColor(newColor) == false)
			throw new Exception("Некоректно задано колір авто;");
		else if (setNumber(newNumber) == false)
			throw new Exception("Некоректно задано номер авто;");
		else if (setYear(newYear) == false)
			throw new Exception("Некоректно задано рік авто;");
		else if (setOwnerInfo(newOwnerInfo) == false)
			throw new Exception("Некоректно задано дані про власника авто;");
	}
	
 	public Brand getBrand() {
		return brand;
	}
	public Color getColor() {
		return color;
	}
	public int getNumber() {
		return number;
	}
	public short getYear() {
		return year;
	}
	public String getOwnerInfo() {
		return ownerInfo;
	}

	public boolean setBrand(Brand newBrand) {
		if (newBrand == null)
			return false;
		brand = newBrand;
		return true;
	}
	public boolean setColor(Color newColor) {
		if (newColor == null)
			return false;
		color = newColor;
		return true;
	}
	public boolean setNumber(int newNumber) {
		if (newNumber < 0)
			return false;
		number = newNumber;
		return true;
	}
	public boolean setYear(short newYear) {
		if (newYear < 1950 || newYear > LocalDate.now().getYear())
			return false;
		year = newYear;
		return true;
	}
	public boolean setOwnerInfo(String newOwnerInfo) {
		if (newOwnerInfo == null || newOwnerInfo.length() < 3)
			return false;
		ownerInfo = newOwnerInfo;
		return true;
	}

	
	@Override
	public int compareTo(Car o) {
		return brand.toString().compareTo(o.brand.toString());
	}
	
	@Override
	public String toString() {
		String str = "\t";
		if (brand.toString().length() < 8)
			str += "\t";
		return brand.toString() + str + color.toString() + "\t" + number + "\t\t" + year + "\t\t" + ownerInfo;
	}
}
