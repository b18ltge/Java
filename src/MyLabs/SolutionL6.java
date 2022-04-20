package MyLabs;

import Misc.L6_1.Student;
import Misc.L6_2.File;
import Misc.L6_2.FileAttribute;
import Misc.L6_2.FileList;
import MyUtils.MyScanner;
import MyUtils.SomeUtils;
import MyUtils.MyConsoleBufferedReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.LinkedList;

public class SolutionL6{
	private static Student readStudent(String str) {
		if(str == null)
			return null;
		
		String[] words = new String[8];
		byte wordsPos = 0;
		
		for(int i = 0, startPos = 0; i < str.length(); ++i) {
			if(str.charAt(i) == ' ') {
				words[wordsPos] = str.substring(startPos, i);
				startPos = i + 1;
				++wordsPos;
			}
			if (i == str.length() - 1) {
				words[wordsPos] = str.substring(startPos, i + 1);
			}
		}
		
		byte Marks[] = {Byte.parseByte(words[3]), 
				Byte.parseByte(words[4]), 
				Byte.parseByte(words[5]), 
				Byte.parseByte(words[6]), 
				Byte.parseByte(words[7])};
		
		return new Student(words[0], words[1], Short.parseShort(words[2]), Marks);
	}
	
	private static LinkedList<Student> readFile(String FileName) throws Exception {
		LinkedList<Student> result = new LinkedList<Student>();
		
		BufferedReader buffReader = new BufferedReader(new FileReader(FileName));
		String str;
		while((str = buffReader.readLine()) != null) 
		{  
			Student student = readStudent(str);
			if (student != null)
			result.add(student);	// порядкове читання текстового файлу
		}
		buffReader.close();
		
		return result;
	} 
	
	private static Student inputStudent() throws Exception {
		String name, surname;
		short number;
		byte Marks[] = new byte[5];

		System.out.print("Введіть ім'я студента (Ім'я може містити тільки символи латинського алфавіту): ");
		name = MyConsoleBufferedReader.buffReader.readLine();
		System.out.print("Введіть прізвище (Прізвище може містити тільки символи латинського алфавіту): ");
		surname = MyConsoleBufferedReader.buffReader.readLine();
		System.out.print("Введіть № залікової книжки (№ залікової книжки повинен бути >= 0): ");
		number = MyScanner.in.nextShort();
		System.out.print("Введіть оцінку з математики [0..100]: ");
		Marks[0] = MyScanner.in.nextByte();
		System.out.print("Введіть оцінку з біології [0..100]: ");
		Marks[1] = MyScanner.in.nextByte();
		System.out.print("Введіть оцінку з іноземної мови [0..100]: ");
		Marks[2] = MyScanner.in.nextByte();
		System.out.print("Введіть оцінку з географії [0..100]: ");
		Marks[3] = MyScanner.in.nextByte();
		System.out.print("Введіть оцінку з фізики [0..100]: ");
		Marks[4] = MyScanner.in.nextByte();

		return new Student(name,surname,number,Marks);
	}
	
	private static void CallEditMenu(FileList fileList, byte id) throws Exception {
		System.out.println("Інформація про файл з порядковим номером " + id +":");
		System.out.println(id + ") " + fileList.at(--id).toString());
		byte n;
		final String message = "Оберіть параметр для редагування:\n" +
								"1 - Назва файлу;\n" +
								"2 - Розширення файлу;\n" +
								"3 - Розмір файлу;\n" +
								"4 - Дата створення файлу;\n" +
								"5 - Атрибути файлу;\n" +
								"0 - Вихід;\n";
		n = SomeUtils.enterByte(message, 0, 5);
		
		switch(n) {
		case 1:{
			System.out.print("Введіть нову назву файлу (Назва може містити тільки символи латинського алфавіту та цифри):");
			String name = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
			if (fileList.edit(id, name, (byte)1) == true)
				System.out.println("Назву файлу змінено успішно;");
			break;
		}
		case 2:{
			System.out.print("Введіть нове розширення файлу (Розширення може містити тільки символи латинського алфавіту нижнього регістру):");
			String extension = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
			if (fileList.edit(id, extension, (byte)-1) == true)
				System.out.println("Розширення файлу змінено успішно;");
			break;
		}
		case 3:{ 
			System.out.print("Введіть новий розмір файлу (В байтах):");
			long size = MyUtils.MyScanner.in.nextLong();
			if (fileList.edit(id, size) == true)
				System.out.println("Розмір файлу змінено успішно;");
			break;
		}
		case 4:{
			System.out.print("Введіть нову дату створення файлу (yyyy-mm-dd):");
			LocalDate date;
			try {
				date = LocalDate.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
			}
			catch(Exception ex) {
				date = null;
			}
			if (fileList.edit(id, date) == true)
				System.out.println("Дату створення файлу змінено успішно;");
			break;
		}
		case 5:{
			System.out.print("Введіть атрибути файлу [(R/-)(W/-)(X/-)]: ");
			FileAttribute FileAtt;
			try {
				FileAtt = FileAttribute.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
			} catch (Exception e) {
				FileAtt = null;
			}
			if (fileList.edit(id, FileAtt) == true)
				System.out.println("Атрибути файлу змінено успішно;");
			break;
		}
		default: return;
		}
	}
	
	private static void CallRemoveMenu(FileList fileList) {
		byte n;
		final String message = "Оберіть параметр для вилучення:\n" +
								"1 - Вилучення за порядковим номером;\n" +
								"2 - Вилучення за назвою;\n" +
								"3 - Вилучення за розширенням;\n" +
								"4 - Вилучення за розміром;\n" +
								"5 - Вилучення за датою створення;\n" +
								"6 - Вилучення за атрибутами;\n" +
								"0 - Вихід;\n";
		n = SomeUtils.enterByte(message, 0, 6);
		
		switch(n) {
		case 1:{
			byte id;
			System.out.println("Вилучення за порядковим номером;");
			System.out.println("Порядковий номер НЕ повинен бути меншим за 1 або більшим за загальну кількість записів;");
			
			do {
				System.out.print("Введіть порядковий номер: ");
				id = MyUtils.MyScanner.in.nextByte();
			} while(id < 1 || id > fileList.size());

			fileList.remove((byte) (id - 1));
			System.out.println("Запис з порядковим номером " + id + " вилучено успішно;");
			break;
		}
		case 2:{
			System.out.println("Вилучення за назвою;");
			System.out.print("Введіть назву файлу (Назва може містити символи латинського алфавіту та цифри):");
			String name;
			try {
				name = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
			} catch (IOException e) {
				name = null;
			}
			
			byte All;
			do {
				System.out.print("Вилучення першого файлу[0] чи всіх файлів[1] із заданою назвою?: ");
				All = MyUtils.MyScanner.in.nextByte();
			} while(All != 0 && All != 1);
			System.out.println("Вилучено успішно " + fileList.remove(name, (byte)1, All) + " файлів;");
			break;
		}
		case 3:{
			System.out.println("Вилучення за розширенням;");
			System.out.print("Введіть розширення файлу(Розширення може містити тільки символи латинського алфавіту нижнього регістру): ");
			String extension;
			try {
				extension = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
			} catch (IOException e) {
				extension = null;
			}
			
			byte All;
			do {
				System.out.print("Вилучення першого файлу[0] чи всіх файлів[1] із заданим розширенням?: ");
				All = MyUtils.MyScanner.in.nextByte();
			} while(All != 0 && All != 1);
			System.out.println("Вилучено успішно " + fileList.remove(extension, (byte)0, All) + " файлів;");
			break;
		}
		case 4:{
			System.out.println("Вилучення за розміром;");
			System.out.print("Введіть розмір файлу (Наприклад: 32.67KB):");
			String StringSize;
			try {
				StringSize = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
			} catch (IOException e) {
				StringSize = null;
			}
			
			byte All;
			do {
				System.out.print("Вилучення першого файлу[0] чи всіх файлів[1] із заданим розміром?: ");
				All = MyUtils.MyScanner.in.nextByte();
			} while(All != 0 && All != 1);
			System.out.println("Вилучено успішно " + fileList.remove(StringSize, All) + " файлів;");
			break;
		}
		case 5:{
			System.out.println("Вилучення за датою створення;");
			System.out.print("Введіть дату створення (yyyy-mm-dd):");
			LocalDate Date;
			try {
				Date = LocalDate.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
			} catch (Exception e) {
				Date = null;
			}
			
			byte All;
			do {
				System.out.print("Вилучення першого файлу[0] чи всіх файлів[1] із заданою датою створення?: ");
				All = MyUtils.MyScanner.in.nextByte();
			} while(All != 0 && All != 1);
			System.out.println("Вилучено успішно " + fileList.remove(Date, All) + " файлів;");
			break;
		}
		case 6:{
			System.out.println("Вилучення за атрибутами;");
			System.out.print("Введіть атрибути [(R/-)(W/-)(X/-)]: ");
			FileAttribute FileAtt;
			try {
				FileAtt = FileAttribute.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
			} catch (Exception e) {
				FileAtt = null;
			}
			
			byte All;
			do {
				System.out.print("Вилучення першого файлу[0] чи всіх файлів[1] із заданими атрибутами?: ");
				All = MyUtils.MyScanner.in.nextByte();
			} while(All != 0 && All != 1);
			System.out.println("Вилучено успішно " + fileList.remove(FileAtt, All) + " файлів;");
			break;
		}
		default: return;
		}
	}
	
	private static void CallMenuForSolve2(FileList fileList) throws IOException {
		byte n;
		final String message = "Оберіть операцію:\n" +
						"1 - Виведення інформації про файли на екран;\n" +
						"2 - Додати новий запис;\n" +
						"3 - Вилучити запис;\n" +
						"4 - Пошук записів(за датою створення);\n" +
						"5 - Сортування записів(за розширенням);\n" +
						"6 - Редагування записів;\n" +
						"0 - Вихід;\n";
		while(true) {
			n = SomeUtils.enterByte(message, 0, 6);
			
			switch(n) {
			case 1: {
				System.out.println("Інформація про всі файли бази даних:");
				fileList.print();
				break;
			}
			case 2:{
				System.out.println("Додавання нового запису:");
				System.out.println("Введіть назву файлу (Назва може містити символи латинського алфавіту та цифри):");
				String name = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
				System.out.println("Введіть розширення файлу (Розширення може містити тільки символи латинського алфавіту нижнього регістру):");
				String extension = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
				System.out.println("Введіть розмір файлу (в байтах):");
				long size = MyUtils.MyScanner.in.nextLong();
				System.out.println("Введіть дату створення файлу (yyyy-mm-dd):");
				LocalDate date;
				try {
					date = LocalDate.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
				}
				catch (DateTimeParseException e) {
					date = null;
				}
				System.out.println("Введіть атрибути файлу (R/-)(W/-)(X/-):");
				FileAttribute FileAtt = FileAttribute.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
				
				File newFile;
				try {
					newFile = new File(name,extension,size,date,FileAtt);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				
				fileList.add(newFile);
				fileList.WriteToFile();
				break;
			}
			case 3:{
				System.out.println("Вилучення записів:");
				CallRemoveMenu(fileList);
				fileList.WriteToFile();
				break;
			}
			case 4:{
				System.out.println("Пошук записів за датою створення:");
				System.out.print("Введіть дату створення (yyyy-mm-dd): ");
				LocalDate date;
				try {
					date = LocalDate.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
				}
				catch(Exception ex) {
					date = null;
				}
				LinkedList<File> result = fileList.search(date);
				System.out.println("Результат пошуку:");
				if (result.isEmpty() == true) {
					System.out.println("Не знайдено жодного файлу із заданою датою створення;");
					continue;
				}
				
				for(var i : result) {
					System.out.println(i.toString());
				}
				break;
			}
			case 5:{
				System.out.println("Сортування записів за розширенням:");
				fileList.sort();
				fileList.WriteToFile();
				System.out.println("Записи успішно відсортовано;");
				break;
			}
			case 6:{
				byte id;
				System.out.println("Редагування записів:");
				System.out.println("Порядковий номер НЕ повинен бути меншим за 1 або більшим за загальну кількість записів;");
				
				do {
					System.out.print("Введіть порядковий номер: ");
					id = MyUtils.MyScanner.in.nextByte();
				} while(id < 1 || id > fileList.size());
				
				// Редагування id-го запису за різними параметрами;
				// 
				try {
					CallEditMenu(fileList,id);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				break;
			}
			default: return;
			}
		}
	}
	
	private static void Solve1() {
		System.out.println("--== Завдання №1 ==--");
		System.out.println("Робота з інформацією про успішність групи студентів;"); 
		System.out.println("Розроблений консольний застосунок виконує певні завдання:"); 
		System.out.println("а) Впорядковує записи у порядку зростання зростання середнього балу та виводить їх на екран у формі таблиці;\n"
				+ "б) Визначає відсоток студентів, що мають незадовільні оцінки;");
		
		byte n;
		do {
			System.out.println("Оберіть спосіб задання списку студентів:");
			System.out.println("1 - З клавіатури");
			System.out.println("2 - Імпорт з текстового фалйу Students.txt");
			System.out.println("0 - Вихід");
			n = MyScanner.in.nextByte();
		} while(n < 0 || n > 2);
		
		
		LinkedList<Student> StudentList;
		switch(n) {
		case 1: {
			System.out.println("Введення інформації про студентів з клавіатури:");
			if (MyConsoleBufferedReader.buffReader == null)
				MyConsoleBufferedReader.buffReader = new BufferedReader(new InputStreamReader(System.in));
			StudentList = new LinkedList<Student>();
			
			
			String answer = null;
			do {
				try {
					StudentList.add(inputStudent());
				} catch (Exception e1) {
					System.out.print("Інформація про студента була задана некоректно.");
					System.out.print(e1.getMessage());
					MyScanner.in.skip(".*");
				}
				
				System.out.print("Чи бажає ввести інформацію про наступного студента? (Y - так, N - ні): ");
				try {
					answer = MyConsoleBufferedReader.buffReader.readLine();
				} catch (IOException e) {
					System.out.print(e.getMessage());
					return;
				}
			} while(answer.equals("y") || answer.equals("Y"));
			// while(answer == "y" || answer == "Y"); // WTF ???!!!
			break;
		}
		case 2: {
			System.out.println("Імпорт інформації про студентів з файлу Students.txt:");
			try {
				StudentList = readFile("D:/eclipse-workspace/L1/Students.txt");
				
			} catch (Exception e) {
				System.out.println("Не вдалось зчитати інформацію про стундентів з файлу.");
				e.printStackTrace();
				return;
			}
			break;
		}
		default: return;
		}
		
		
		System.out.println("--== Інформація про студентів ==--");
		if (StudentList.size() == 0) {
			System.out.println("--== Списокк студентів порожній! ==--");
			return;
		}
		
		System.out.println("Ім'я\t\tПрізвище\t№ залікової\tМатематика\tБіологія\tІноземна мова\tГеографія\tФізика\t\tСерденій бал");
		for(Student i : StudentList) {
			i.printInfo();
		}
		
		Collections.sort(StudentList);
		
		byte count = 0;
		System.out.println("--== Інформація про студентів ==--");
		System.out.println("Ім'я\t\tПрізвище\t№ залікової\tМатематика\tБіологія\tІноземна мова\tГеографія\tФізика\t\tСерденій бал");
		for(Student i : StudentList) {
			i.printInfo();
			if (i.hasLowMarks())
				++count;
		}
		
		float precent = count * 1.0f / StudentList.size() * 100.f;
		System.out.printf("Відсоток студентів, що мають незадовільні оцінки: %.3f%%",precent);
		System.out.println("\nНезадовільною вважаться оцінка, яка є меншою за 20;");
	}
	
	private static void Solve2() {
		final String FilePath = "D:/eclipse-workspace/L1/FileList.txt";
		System.out.println("--== Завдання №2 ==--");
		System.out.println("Консольний застосунок для роботи з базою даних, що зберігається у текстовому файлі");
		System.out.println("База даних містить інформацію про файли(назва, розширення, дата створення, розмір, атрибути)");
		System.out.println("Інформація зберігається в файлі " + FilePath);
	
		
		FileList fileList = null;
		try {
			fileList = new FileList(FilePath);
		} catch (Exception e) {
			System.out.print("Не вдалось зчитати інформацію з файлу;");
			e.printStackTrace();
			return;
		}
	
		try {
			CallMenuForSolve2(fileList);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static void CallMenu() {
		byte n;
		final String message = "\n-----===== Лабораторна робота №6 =====-----\n" +
						"Оберіть № завдання:\n" +
						"1 - Завдання №1\n" +
						"2 - Завдання №2\n" +
						"0 - Вихід\n";
		while (true) {
			n = SomeUtils.enterByte(message, 0, 2);

			switch(n) {
				case 1: SolutionL6.Solve1();
					break;
				case 2: SolutionL6.Solve2();
					break;
				default: return;
			}
		}
	}
}