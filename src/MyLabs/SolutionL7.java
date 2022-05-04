package MyLabs;

import MyUtils.SomeUtils;

import java.time.LocalDate;
import Misc.L7_1.*;
import Misc.L7_1.Poet.Language;

public class SolutionL7 {
	private static void CallRemoveMenu(PerformanceList list) {
		final String message = "Оберіть параметр для вилучення:\n" + 
						"1 - Порядковий номер\n" + 
						"2 - Прізвище\n" +
						"3 - Мова\n" + 
						"4 - К-сть збірок\n" + 
						"5 - Дата виступу\n" + 
						"6 - Місце виступу\n" +
						"7 - К-сть слухачів\n" + 
						"0 - Вихід\n";
		byte n = SomeUtils.enterByte(message, 7, 0);
		
		switch(n) {
		case 1:{
			byte id = SomeUtils.enterByte("Введіть порядковий номер виступу (0 < Id <= " + list.size() + "): ", 1, list.size());
			list.remove(--id);
			System.out.println("Виступ з порядковим номером " + (id + 1) + " вилучено успішно");
			break;
		}
		case 2:{
			String surname = SomeUtils.enterString("Введіть прізвище поета(прізвище може містить тільки символи латинського алфавіту): ", "[a-zA-Z]+");
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про виступи з даним прізвищем чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(surname, (byte)1, all) + " записів успішно вилучено;");
			break;
		}
		case 3:{
			byte answer = SomeUtils.enterByte("Оберіть мову поета:\n" +
					"1 - EN\n" +
					"2 - DE\n" +
					"3 - FR\n" +
					"4 - UKR\n", 1, 4);
			Language lang = Poet.getLanguageFromInt(answer);
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про виступи з даною мову чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(lang, all) + " записів успішно вилучено;");
			break;
		}
		case 4:{
			byte count = SomeUtils.enterByte("Введіть к-сть збірок поета(0 < к-сть збірок < 100): ", 1, 100);
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про виступи з даною к-стю збірок чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(count, all) + " записів успішно вилучено;");
			break;
		}
		case 5:{
			LocalDate newDate = SomeUtils.enterDate("Введіть дату виступу поета (yyyy-mm-dd): ", 
					LocalDate.of(1900,1,1), LocalDate.now().plusYears(7));
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про виступи з даною датою виступу чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(newDate, all) + " записів успішно вилучено;");
			break;
		}
		case 6:{
			String newLocation = SomeUtils.enterString("Введіть місце проведення виступу (К-сть символів > 2): ", ".{3,}");
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про виступи з даним місцем проведення чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(newLocation, (byte)0, all) + " записів успішно вилучено;");
			break;
		}
		case 7:{
			short newListeners = SomeUtils.enterShort("Введіть к-сть слухачів (к-сть > 0): ", 1, Short.MAX_VALUE);
			byte all = SomeUtils.enterByte("Вилучити всі записи[1] про виступи з даною к-стю слухачів чи тільки перший[0]?", 0, 1);
			System.out.println(list.remove(newListeners, all) + " записів успішно вилучено;");
			break;
		}
		default: return;
		}
	}
	
	private static void CallEditMenu(PerformanceList list) {
		byte id = SomeUtils.enterByte("Введіть порядковий номер виступу (0 < Id <= " + list.size() + "): ", 1, list.size());
		System.out.println(id-- + list.at(id).toString());
		final String message = "Оберіть параметр для редагування:\n" + 
				"1 - Прізвище\n" +
				"2 - Мова\n" + 
				"3 - К-сть збірок\n" + 
				"4 - Дата виступу\n" + 
				"5 - Місце виступу\n" +
				"6 - К-сть слухачів\n" + 
				"0 - Вихід\n";
		byte n = SomeUtils.enterByte(message, 6, 0);
		
		switch(n) {
		case 1:{
			String surname = SomeUtils.enterString("Введіть нове прізвище поета(прізвище може містить тільки символи латинського алфавіту): ", "[a-zA-Z]+");
			if (list.edit(id, surname, (byte)1) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 2:{
			byte answer = SomeUtils.enterByte("Оберіть мову поета:\n" +
					"1 - EN\n" +
					"2 - DE\n" +
					"3 - FR\n" +
					"4 - UKR\n", 1, 4);
			Language lang = Poet.getLanguageFromInt(answer);
			if (list.edit(id, lang) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 3:{
			byte count = SomeUtils.enterByte("Введіть к-сть збірок поета(0 < к-сть збірок < 100): ", 1, 100);
			if (list.edit(id, count) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 4:{
			LocalDate newDate = SomeUtils.enterDate("Введіть дату виступу поета (yyyy-mm-dd): ", 
					LocalDate.of(1900,1,1), LocalDate.now().plusYears(7));
			if (list.edit(id, newDate) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 5:{
			String newLocation = SomeUtils.enterString("Введіть місце проведення виступу (К-сть символів > 2): ", ".{3,}");
			if (list.edit(id, newLocation, (byte)0) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		case 6:{
			short newListeners = SomeUtils.enterShort("Введіть к-сть слухачів (к-сть > 0): ", 1, Short.MAX_VALUE);
			if (list.edit(id, newListeners) == true)
				System.out.println("Запис з порядковим номером " + ++id + " змінено успішно;");
			else System.out.println("Не вдалось змінити запис;");
			break;
		}
		default: return;
		}
	}
	
	private static void Solve1() {
		final String FilePath = "D:/eclipse-workspace/L1/Performances.txt";
		final String message = "Оберіть операцію:\n" +
								"1 - Перегляд інформації про виступи поетів;\n" +
								"2 - Додавання нової інформації про виступи поетів;\n" +
								"3 - Вилучення інформації про виступи поетів;\n" +
								"4 - Редагування інформації про виступи поетів;\n" +
								"0 - Вихід;\n";
		byte n;
		while(true) {
			System.out.println("\n--== Завдання №1,2 ==--");
			System.out.println("Робота з базою даних, що містить інформацію про виступи поетів, яка зберігається в текстовому файлі: \n" + FilePath); 
			System.out.println("Розроблений консольний застосунок передбачає такі можливості:"); 
			System.out.println("- Додавання записів;\n- Вилуення записів\n- Редагування записів\n- Виведення інформації з файлу на екран");
			
			n = SomeUtils.enterByte(message, 0, 4);
			
			PerformanceList list;
			try {
				list = new PerformanceList(FilePath);
			} catch (Exception e) {
				System.out.println("Не вдалось зчитати інформацію з файлу;");
				return;
			}
			
			switch(n) {
				case 1:{
					list.print();
					System.out.println("Сумарна к-сть слухачів: " + list.getTotalListeners());
					System.out.println("Дата з найбільшою к-стю слухачів: " + list.getMaxListenersDate());
					break;
				}
				case 2:{
					System.out.println("Додавання нової інформації про виступи поетів: ");
					String newSurname = SomeUtils.enterString("Введіть прізвище поета(прізвище може містить тільки символи латинського алфавіту): ", "[a-zA-Z]+");

					byte answer = SomeUtils.enterByte("Оберіть мову поета:\n" +
														"1 - EN\n" +
														"2 - DE\n" +
														"3 - FR\n" +
														"4 - UKR\n", 1, 4);
					Language lang = Poet.getLanguageFromInt(answer);
					
					answer = SomeUtils.enterByte("Введіть к-сть збірок поета(0 < к-сть збірок < 100): ", 1, 100);
					
					LocalDate newDate = SomeUtils.enterDate("Введіть дату виступу поета (yyyy-mm-dd): ", 
							LocalDate.of(1900,1,1), LocalDate.now().plusYears(7));
					
					String newLocation = SomeUtils.enterString("Введіть місце проведення виступу (К-сть символів > 2): ", ".{3,}");

					short newListeners = SomeUtils.enterShort("Введіть к-сть слухачів (к-сть > 0): ", 1, Short.MAX_VALUE);
					
					try {
						list.add(new Performance(newSurname,lang,answer,newDate,newLocation,newListeners));
						System.out.println("Новий запис успішно додано;");
					} catch (Exception e) {
						System.out.println("Не вдалось додати новий запис до файлу;");
					}

					break;
				}
				case 3:{
					CallRemoveMenu(list);
					break;
				}
				case 4:{
					CallEditMenu(list);
					break;
				}
				default: return;
			}
		}
	}

	public static void CallMenu() {
		byte n;
		final String message = "\n-----===== Лабораторна робота №7 =====-----\n" +
						"Оберіть № завдання:\n" +
						"1 - Завдання №1,2\n" +
						"0 - Вихід\n";
		while (true) {
			n = SomeUtils.enterByte(message, 0, 1);

			switch(n) {
				case 1: SolutionL7.Solve1();
					break;
				default: return;
			}
		}
	}
}
