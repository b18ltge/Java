package Misc.L7_1;

import java.time.LocalDate;

public class Performance extends Poet {
	private LocalDate date;
	private String location;
	private short listeners;
	
	public Performance(String poetName, Language lang, byte newCount, 
			LocalDate newDate, String newLocation, short newListeners) throws Exception {
		super(poetName,lang,newCount);
		if (setDate(newDate) == false)
			throw new Exception("Некоректна дата виступу;");
		if (setLocation(newLocation) == false)
			throw new Exception("Некоректне місце виступу;");
		if (setListeners(newListeners) == false)
			throw new Exception("Некоректна к-сть слухачів. К-сть слухачів повинна бути > 0;");
	}

	public boolean setDate(LocalDate newDate) {
		if (newDate == null || newDate.isAfter(LocalDate.now().plusYears(7)) || newDate.isBefore(LocalDate.of(1900,1,1)))
			return false;
		date = newDate;
		return true;
	}
	public boolean setLocation(String newLocation) {
		if (newLocation == null || newLocation.length() < 3 || newLocation.contains(" "))
			return false;
		location = newLocation;
		return true;
	}
	public boolean setListeners(short newListeners) {
		if (newListeners < 1)
			return false;
		listeners = newListeners;
		return true;
	}

	public boolean setSurname(String newSurname) {
		if (newSurname == null || !newSurname.matches("[a-zA-Z]+"))
			return false;
		surname = newSurname;
		return true;
	}
	public boolean setLanguage(Language newLanguage) {
		if (newLanguage == null)
			return false;
		language = newLanguage;
		return true;
	}
	public boolean setCount(byte newCount) {
		if (newCount <= 0)
			return false;
		count = newCount;
		return true;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public String getLocation() {
		return location;
	}
	public short getListeners() {
		return listeners;
	}
	
	public String getSurname() {
		return surname;
	}
	public Language getLanguage() {
		return language;
	}
	public byte getCount() {
		return count;
	}

	public int getSurnameLength() {
		return surname.length();
	}

	public Performance clone() {
		try {
			return new Performance(surname, language, count, date, location, listeners);
		} catch (Exception e) {
			return null;
		}
	}

	public String toString() {
		String space2 = "\t";
		String space1 = "\t";
		if (surname.length() < 8) space1 += "\t";
		if (location.length() < 8) space2 += "\t";
		return "\t" + surname + space1 + getLanguageString() + "\t" + count + 
				"\t\t" + date.toString() + "\t" + location + space2 + listeners + "\t\t" + surname.length();
	}
}
