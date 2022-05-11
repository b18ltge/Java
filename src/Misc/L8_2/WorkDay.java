package Misc.L8_2;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkDay extends Doctor{
	public static final LocalDate MIN_DATE = LocalDate.of(2000,1,1);
	public static final LocalDate MAX_DATE = LocalDate.now();
	public static final LocalTime MIN_TIME = LocalTime.of(6, 30);
	public static final LocalTime MAX_TIME = LocalTime.of(20, 30);
	
	protected LocalDate date;
	protected byte count;
	protected LocalTime time;
	
	public WorkDay(String newName, Type newType, LocalDate newDate, byte newCount, LocalTime newTime) throws Exception {
		if (setName(newName) == false)
			throw new Exception("Некоректно задано ПІБ лікаря;");
		else if (setType(newType) == false)
			throw new Exception("Некоректно задано спеціальнсть лікаря;");
		else if (setDate(newDate) == false)
			throw new Exception("Некоректно задано дату робочого дня лікаря;");
		else if (setCount(newCount) == false)
			throw new Exception("Некоректно задано кількість пацієнтів;");
		else if (setTime(newTime) == false)
			throw new Exception("Некоректно задано час початку роботи;");
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public Type getType() {
		return type;
	}
	public LocalDate getDate() {
		return date;
	}
	public byte getCount() {
		return count;
	}
	public LocalTime getTime() {
		return time;
	}

	@Override
	public boolean setName(String newName) {
		if (!newName.matches(NAME_REGEX))
			return false;
		name = newName;
		return true;
	}
	@Override
	public boolean setType(Type newType) {
		if (newType == null)
			return false;
		type = newType;
		return true;
	}
	public boolean setDate(LocalDate newDate) {
		if (newDate == null || newDate.isBefore(MIN_DATE) || newDate.isAfter(MAX_DATE))
				return false;
		date = newDate;
		return true;
	}
	public boolean setCount(byte newCount) {
		if (newCount < 0)
			return false;
		count = newCount;
		return true;
	}
	public boolean setTime(LocalTime newTime) {
		if (newTime == null || newTime.isBefore(MIN_TIME) || newTime.isAfter(MAX_TIME))
				return false;
		time = newTime;
		return true;
	}
}
