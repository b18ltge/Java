package MyLabs;

import MyUtils.SomeUtils;
import MyUtils.SomeUtils.Mode;

import java.time.LocalDate;
import java.time.LocalTime;

import Misc.L8_1.Car;
import Misc.L8_1.CarList;
import Misc.L8_1.CarList.Fields;
import Misc.L8_1.IVehicle.Brand;
import Misc.L8_1.IVehicle.Color;
import Misc.L8_2.Doctor;
import Misc.L8_2.IDoctor;
import Misc.L8_2.WorkDay;
import Misc.L8_2.WorkDayList;

public class SolutionL8 {
	private static void CallAddMenu1(CarList list) {
		System.out.println("Додавання нової інформації про авто: ");
		String elements = SomeUtils.ArrayToString(Brand.values(), Mode.NextLine);
		byte answer = SomeUtils.enterByte("Оберіть марку авто:\n" +
				elements,
				 1, Brand.values().length);
		Brand brand = Brand.values()[answer - 1];
		
		elements = SomeUtils.ArrayToString(Color.values(), Mode.NextLine);
		answer = SomeUtils.enterByte("Оберіть колір авто:\n" +
				elements,
				 1, Color.values().length);
		Color color = Color.values()[answer - 1];
		
		int number = SomeUtils.enterInt("Введіть номер авто: ", 0, Integer.MAX_VALUE);
		int currYear = LocalDate.now().getYear();
		short year = SomeUtils.enterShort("Введіть рік випуску авто (1950-" + currYear + "): ", 1950, currYear);
		String ownerInfo = SomeUtils.enterString("Введіть інформацію про власника: ", ".{3,}");
		
		try {
			list.add(new Car(brand,color,number,year,ownerInfo));
			System.out.println("Новий запис успішно додано;");
		} catch (Exception e) {
			System.out.println("Не вдалось додати новий запис до файлу;");
			e.printStackTrace();
		}
	}
	
	private static void CallAddMenu2(WorkDayList list) {
		System.out.println("Додавання інформації про новий робочий день лікаря: ");
		
		String name = SomeUtils.enterString("Введіть ПІБ лікаря (тільки символи латинського алфавіту та пробіли) : ", WorkDay.NAME_REGEX);
		
		String elements = SomeUtils.ArrayToString(IDoctor.Type.values(), Mode.NextLine);
		byte answer = SomeUtils.enterByte("Оберіть спеціальність лікаря:\n" +
				elements,
				 1, IDoctor.Type.values().length);
		IDoctor.Type type = IDoctor.Type.values()[answer - 1];
		
		LocalDate date = SomeUtils.enterDate("Введіть дату робочого дня (yyyy-mm-dd): ", WorkDay.MIN_DATE, WorkDay.MAX_DATE);
		byte count = SomeUtils.enterByte("Введіть кількість пацієнтів (кількість > 0): ", 1, Byte.MAX_VALUE);
		LocalTime time = SomeUtils.enterTime("Введіть час початку роботи лікаря (" +
				WorkDay.MIN_TIME.toString() + " - " + WorkDay.MAX_TIME.toString() + "): ",
				WorkDay.MIN_TIME, WorkDay.MAX_TIME);
		
		try {
			list.add(new WorkDay(name,type,date,count,time));
			System.out.println("Новий запис успішно додано;");
		} catch (Exception e) {
			System.out.println("Не вдалось додати новий запис до файлу;");
			e.printStackTrace();
		}
	}
	
	private static void CallRemoveMenu1(CarList list) {
		final String message = "Оберіть параметр для вилучення:\n" +
				"1 - Порядковий номер;\n" +
				"2 - Марка;\n" +
				"3 - Колір;\n" +
				"4 - Номер;\n" +
				"5 - Рік випуску;\n" +
				"6 - Інформація про власника;\n" +
				"0 - Вихід;\n";
		byte n = SomeUtils.enterByte(message, 6, 0);
		
		switch(n) {
		case 1:{
			byte id = SomeUtils.enterByte("Введіть порядковий номер авто (0 < Id <= " + list.size() + "): ", 1, list.size());
			list.remove(--id);
			System.out.println("Інформацію про авто з порядковим номером " + (id + 1) + " вилучено успішно");
			break;
		}
		case 2:{
			String elements = SomeUtils.ArrayToString(Brand.values(), Mode.NextLine);
			byte answer = SomeUtils.enterByte("Оберіть марку авто:\n" +
					elements,
					 1, Brand.values().length);
			Brand brand = Brand.values()[answer - 1];
			
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про авто з даною маркою чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(brand, all) + " записів успішно вилучено;");
			break;
		}
		case 3:{
			String elements = SomeUtils.ArrayToString(Color.values(), Mode.NextLine);
			byte answer = SomeUtils.enterByte("Оберіть колір авто:\n" +
					elements,
					 1, Color.values().length);
			Color color = Color.values()[answer - 1];
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про авто з даним кольором чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(color, all) + " записів успішно вилучено;");
			break;
		}
		case 4:{
			int count = SomeUtils.enterInt("Введіть номер авто: ", 0, Integer.MAX_VALUE);
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про авто з даним номером чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(count, all) + " записів успішно вилучено;");
			break;
		}
		case 5:{
			short count = SomeUtils.enterShort("Введіть рік випуску авто: ", 0, Integer.MAX_VALUE);
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про авто з даним роком випуску чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(count, all) + " записів успішно вилучено;");
			break;
		}
		case 6:{
			String ownerInfo = SomeUtils.enterString("Введіть інформацію про власника: ", ".{3,}");
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про авто з даною інформацією про власника чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(ownerInfo, all) + " записів успішно вилучено;");
			break;
		}
		default: return;
		}
	}
	
	private static void CallRemoveMenu2(WorkDayList list) {
		final String message = "Оберіть параметр для вилучення:\n" +
				"1 - Порядковий номер;\n" +
				"2 - ПІБ;\n" +
				"3 - Спеціальність;\n" +
				"4 - Дата робочого дня;\n" +
				"5 - Кількість пацієнтів;\n" +
				"6 - Час початку роботи;\n" +
				"0 - Вихід;\n";
		byte n = SomeUtils.enterByte(message, 6, 0);
		
		switch(n) {
		case 1:{
			byte id = SomeUtils.enterByte("Введіть порядковий номер робочого дня (0 < Id <= " + list.size() + "): ", 1, list.size());
			list.remove(--id);
			System.out.println("Інформацію про робочий день з порядковим номером " + (id + 1) + " вилучено успішно");
			break;
		}
		case 2:{
			String name = SomeUtils.enterString("Введіть ПІБ лікаря (тільки символи латинського алфавіту та пробіли): ", WorkDay.NAME_REGEX);
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про робочі дні лікарів з даними ПІБ чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(name, all) + " записів успішно вилучено;");
			break;
		}
		case 3:{
			String elements = SomeUtils.ArrayToString(IDoctor.Type.values(), Mode.NextLine);
			byte answer = SomeUtils.enterByte("Оберіть спеціальність лікаря:\n" +
					elements,
					 1, IDoctor.Type.values().length);
			IDoctor.Type type = IDoctor.Type.values()[answer - 1];
			
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про робочі дні лікарів з даною спеціальністю чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(type, all) + " записів успішно вилучено;");
			break;
		}
		case 4:{
			LocalDate date = SomeUtils.enterDate("Введіть дату робочого дня (yyyy-mm-dd): ", WorkDay.MIN_DATE, WorkDay.MAX_DATE);
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про робочі дні лікарів з вказаною датою чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(date, all) + " записів успішно вилучено;");
			break;
		}
		case 5:{
			byte count = SomeUtils.enterByte("Введіть кількість пацієнтів (кількість > 0): ", 1, Byte.MAX_VALUE);
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про робочі дні лікарів з даною к-стю пацієнтів чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(count, all) + " записів успішно вилучено;");
			break;
		}
		case 6:{
			LocalTime time = SomeUtils.enterTime("Введіть час початку роботи лікаря (" +
					WorkDay.MIN_TIME.toString() + " - " + WorkDay.MAX_TIME.toString() + "): ",
					WorkDay.MIN_TIME, WorkDay.MAX_TIME);
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про робочі дні лікарів з даним часом початку роботи чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(time, all) + " записів успішно вилучено;");
			break;
		}
		default: return;
		}
	}
	
	private static void CallEditMenu1(CarList list) {
		byte id = SomeUtils.enterByte("Введіть порядковий номер авто (0 < Id <= " + list.size() + "): ", 1, list.size());
		System.out.println(id-- + "\t" + list.at(id).toString());
		final String message = "Оберіть параметр для редагування:\n" + 
				"1 - Марка\n" +
				"2 - Колір\n" + 
				"3 - Номер\n" + 
				"4 - Рік випуску\n" + 
				"5 - Інформація про власника\n" +
				"0 - Вихід\n";
		byte n = SomeUtils.enterByte(message, 5, 0);
		
		switch(n) {
		case 1:{
			String msg = SomeUtils.ArrayToString(Brand.values(), Mode.NextLine);
			byte answer = SomeUtils.enterByte("Оберіть нову марку авто:\n" +
					msg,
					 1, Brand.values().length);
			Brand brand = Brand.values()[answer - 1];
			if (list.edit(id, brand) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 2:{
			String msg = SomeUtils.ArrayToString(Color.values(), Mode.NextLine);
			byte answer = SomeUtils.enterByte("Оберіть новий колір авто:\n" +
					msg,
					 1, Color.values().length);
			Color color = Color.values()[answer - 1];
			if (list.edit(id, color) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 3:{
			int newNumber = SomeUtils.enterInt("Введіть новий номер авто: ",
					 0, Integer.MAX_VALUE);
			if (list.edit(id, newNumber) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 4:{
			short newYear = SomeUtils.enterShort("Введіть новий рік випуску авто: ",
					 1950, LocalDate.now().getYear());
			if (list.edit(id, newYear) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 5:{
			String ownerInfo = SomeUtils.enterString("Введіть нову інформацію про власника: ", ".{3,}");
			if (list.edit(id, ownerInfo) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		default: return;
		}
	}
	
	private static void CallEditMenu2(WorkDayList list) {
		byte id = SomeUtils.enterByte("Введіть порядковий номер робочого дня (0 < Id <= " + list.size() + "): ", 1, list.size());
		System.out.println(id-- + "\t" + list.at(id).toString());
		final String message = "Оберіть параметр для редагування:\n" + 
				"1 - ПІБ лікаря\n" +
				"2 - Спеціальність лікаря\n" + 
				"3 - Дата робочого дня\n" + 
				"4 - Кількість пацієнтів\n" + 
				"5 - Час початку роботи\n" +
				"0 - Вихід\n";
		final byte n = SomeUtils.enterByte(message, 5, 0);
		
		switch(n) {
		case 1:{
			final String newName = SomeUtils.enterString("Введіть ПІБ лікаря (тільки символи латинського алфавіту та пробіли): \n", Doctor.NAME_REGEX);
			if (list.edit(id, newName) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 2:{
			final String msg = SomeUtils.ArrayToString(IDoctor.Type.values(), Mode.NextLine);
			final byte answer = SomeUtils.enterByte("Оберіть спеціальніть лікаря:\n" +
					msg,
					 1, IDoctor.Type.values().length);
			IDoctor.Type newType = IDoctor.Type.values()[answer - 1];
			if (list.edit(id, newType) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 3:{
			LocalDate newDate = SomeUtils.enterDate("Введіть дату робочого дня (yyyy-mm-dd): ",
					 WorkDay.MIN_DATE, WorkDay.MAX_DATE);
			if (list.edit(id, newDate) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 4:{
			byte newCount = SomeUtils.enterByte("Введіть кількість пацієнтів (кількість > 0): ",
					 0, Byte.MAX_VALUE);
			if (list.edit(id, newCount) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 5:{
			LocalTime newTime = SomeUtils.enterTime("Введіть час початку роботи: ",
					WorkDay.MIN_TIME, WorkDay.MAX_TIME);
			if (list.edit(id, newTime) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		default: return;
		}
	}
	
	private static void CallSortMenu1(CarList list) {
		final String message = "Оберіть параметр для сортування:\n" +
				"1 - Марка;\n" +
				"2 - Колір;\n" +
				"3 - Номер;\n" +
				"4 - Рік випуску;\n" +
				"5 - Інформація про власника;\n" +
				"0 - Вихід;\n";
		byte n = SomeUtils.enterByte(message, 0, 5);
		
		byte order = SomeUtils.enterByte("Сортування в порядку зростання[1] чи спадання[0] ?: ", 0, 1);
		
		switch(n) {
		case 1:{
			list.sort(Fields.Brand, order);
			System.out.println("Результат сортування: ");
			list.print();
			break;
		}
		case 2:{
			list.sort(Fields.Color, order);
			System.out.println("Результат сортування: ");
			list.print();
			break;
		}
		case 3:{
			list.sort(Fields.Number, order);
			System.out.println("Результат сортування: ");
			list.print();
			break;
		}
		case 4:{
			list.sort(Fields.Year, order);
			System.out.println("Результат сортування: ");
			list.print();
			break;
		}
		case 5:{
			list.sort(Fields.OwnerInfo, order);
			System.out.println("Результат сортування: ");
			list.print();
			break;
		}
		default: return;
		}
	}
	
	private static void Solve1() {
		final String FilePath = "D:/eclipse-workspace/L1/Cars.txt";
		final String message = "Оберіть операцію:\n" +
				"1 - Перегляд інформації про авто;\n" +
				"2 - Додавання нової інформації про авто;\n" +
				"3 - Вилучення інформації про авто;\n" +
				"4 - Редагування інформації про авто;\n" +
				"5 - Сортування інформації про авто;\n" +
				"0 - Вихід;\n";
		byte n;
		while(true) {
			System.out.println("\n--== Завдання №1 ==--");
			System.out.println("Робота з базою даних, що містить інформацію про авто, яка зберігається в текстовому файлі: \n" + FilePath); 
			System.out.println("Розроблений консольний застосунок передбачає такі можливості:"); 
			System.out.println("- Додавання записів;\n- Вилуення записів\n- Редагування записів\n- Виведення інформації з файлу на екран"
					+ "\n- Сортування за різними полями\n- Обчиислення кількості різних кольорів кожної марки");
		
			n = SomeUtils.enterByte(message, 0, 5);
			
			CarList list = null;
			try {
				list = new CarList(FilePath);
			} catch (Exception e) {
				System.out.println("Не вдалось зчитати інформацію з файлу;");
				//e.printStackTrace();
				return;
			}
			
			switch(n) {
			case 1:{
				list.print();
				System.out.println("Кількість різних кольорів кожної марки: ");
				list.printInfo();
				break;
			}
			case 2:{
				CallAddMenu1(list);
				break;
			}
			case 3:{
				CallRemoveMenu1(list);
				break;
			}
			case 4:{
				CallEditMenu1(list);
				break;
			}
			case 5:{
				CallSortMenu1(list);
				break;
			}
			default: return;
			}
			}
	}

	private static void Solve2() {
		final String FilePath = "D:/eclipse-workspace/L1/Doctors.txt";
		final String message = "Оберіть операцію:\n" +
				"1 - Перегляд інформації про робочі дні лікарів;\n" +
				"2 - Додавання нової інформації про робочі дні лікарів;\n" +
				"3 - Вилучення інформації про робочі дні лікарів;\n" +
				"4 - Редагування інформації про робочі дні лікарів;\n" +
				"5 - Обчислення середньої кількісті пацієнтів в день за період;\n" +
				"6 - Отримання дат днів, коли час початку роботи був після зазначеного;\n" +
				"0 - Вихід;\n";
		byte n;
		while(true) {
			System.out.println("\n--== Завдання №2 ==--");
			System.out.println("Робота з базою даних, що містить інформацію про робочі дні лікарів, яка зберігається в текстовому файлі: \n" + FilePath); 
			System.out.println("Розроблений консольний застосунок передбачає такі можливості:"); 
			System.out.println("- Додавання записів;\n- Вилуення записів\n- Редагування записів\n- Виведення інформації з файлу на екран"
					+ "\n- Обчислення середньої кількісті пацієнтів в день за період");
		
			n = SomeUtils.enterByte(message, 0, 6);
			
			WorkDayList list = null;
			try {
				list = new WorkDayList(FilePath);
			} catch (Exception e) {
				System.out.println("Не вдалось зчитати інформацію з файлу;");
				e.printStackTrace();
				return;
			}
			
			switch(n) {
			case 1:{
				list.print();
				System.out.println("Кількість днів з максимальним навантаженням: " + list.getMaxCount());
				break;
			}
			case 2:{
				CallAddMenu2(list);
				break;
			}
			case 3:{
				CallRemoveMenu2(list);
				break;
			}
			case 4:{
				CallEditMenu2(list);
				break;
			}
			case 5:{
				System.out.println("Обчислення середньої кількісті пацієнтів в день за період:");
				LocalDate startDate = SomeUtils.enterDate("Введіть початкову дату (yyyy-mm-dd): ", WorkDay.MIN_DATE, WorkDay.MAX_DATE);
				LocalDate endDate = SomeUtils.enterDate("Введіть кінцеву дату (yyyy-mm-dd): ", startDate, WorkDay.MAX_DATE);
				System.out.println("Середня кількість пацієнтів з " + startDate.toString() 
				+ " по " + endDate.toString() + " - " + list.getAverageCount(startDate, endDate));
				break;
			}
			case 6:{
				System.out.println("Отримання дат днів, коли час початку роботи був після зазначеного:");
				LocalTime time = SomeUtils.enterTime("Введіть час початку роботи лікаря (" +
						WorkDay.MIN_TIME.toString() + " - " + WorkDay.MAX_TIME.toString() + "): ", WorkDay.MIN_TIME, WorkDay.MAX_TIME);
				System.out.println("Дні, коли час початку роботи був після " + time.toString() 
				+ ":");
				for(final var i : list.getDays(time)) {
					System.out.println(i.toString());
				}
				break;
			}
			default: return;
			}
			}
	}
	
	public static void CallMenu() {
		byte n;
		final String message = "\n-----===== Лабораторна робота №8 =====-----\n" +
						"Оберіть № завдання:\n" +
						"1 - Завдання №1\n" +
						"2 - Завдання №2\n" +
						"0 - Вихід\n";
		while (true) {
			n = SomeUtils.enterByte(message, 0, 2);

			switch(n) {
				case 1: SolutionL8.Solve1();
					break;
				case 2: SolutionL8.Solve2();
					break;
				default: return;
			}
		}
	}
}
