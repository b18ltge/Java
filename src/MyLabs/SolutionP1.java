package MyLabs;

import MyUtils.MyScanner;
import MyUtils.SomeUtils;

public class SolutionP1 {
		public static byte Task1(Double[] MyArray) {
			byte MinId = 0;
			for(byte i = 0; i < MyArray.length; ++i) {
				if (Math.abs(MyArray[i]) < Math.abs(MyArray[MinId]))
					MinId = i;
			}
			return ++MinId;
		}
		
		public static double Task2(Double[] MyArray) throws Exception {
			byte pos = -1;	// first zero
			byte pos2 = -1;	// last zero
			double result = 1.0;
			for(byte i = 0; i < MyArray.length; ++i) {
				if (pos2 != -1)
					continue;
				if (pos != -1) {
					if (MyArray[i] != 0.0 && pos2 == -1)
						result *= MyArray[i];
					else pos2 = i;
				}
				else if (MyArray[i] == 0.0)
					pos = i;
			}
			if (pos == -1 || pos2 == -1 || (pos2 - pos) == 1)	// no first or second zero or no space between them;
				throw new Exception("Неможливо обчислити добуток елементів масиву;");
			
			return result;
		}
		
		public static Double[] Task3(Double[] MyArray) {
			Double result[] = new Double[MyArray.length];
			int half = MyArray.length >> 1;
			for(int i = 0; i < half; ++i) {
				result[i] = MyArray[half + i];
			}
			for(int i = 0; i < half; ++i) {
				result[half + i] = MyArray[i];
			}
			return result;
		}
		
		// #1.1
		private static void Solve1() {
			System.out.println("--== Завдання №1.1 ==--");
			System.out.println("Визначення мінімального за модулем елемента масиву*;\n"
					+ "* - Значення елементів масиву задаються випадковим чином на проміжку [-100;100]");
			
			byte size;
			do {
				System.out.print("Введіть розмір масиву (Розмір масиву не повинен бути меншим за 1): ");
				size = MyScanner.in.nextByte();
			} while (size < 1);


			Double MyArray[] = new Double[size];
			String str = "";
			System.out.println("Масив, згенерований випадковим чином: ");
			for(byte i = 0; i < MyArray.length; ++i) {
				MyArray[i] = (int) ((Math.random() * 200.0 - 100.0) * 100.0) / 100.0;
				str += MyArray[i] + " ";
			}

			System.out.println(str);
			System.out.print("Номер мінімального за модулем елемента масиву: " + Task1(MyArray));
		}

		// #1.2
		private static void Solve2() {
			System.out.println("--== Завдання №1.2 ==--");
			System.out.println("Обчислення добутку елементів масиву, розташованих між першим й другим нульовими елементами;");
			
			byte size;
			do {
				System.out.print("Введіть розмір масиву (Розмір масиву не повинен бути меншим за 1): ");
				size = MyScanner.in.nextByte();
			} while (size < 1);

			
			Double MyArray[] = new Double[size];
			String str = "";
			System.out.println("Масив, згенерований випадковим чином: ");
			for(byte i = 0; i < MyArray.length; ++i) {
				MyArray[i] = (int) ((Math.random() * 200.0 - 100.0) * 100.0) / 100.0;
				str += MyArray[i] + " ";
			}
			System.out.println(str);
			
			double result = 0;
			try {
				result = Task2(MyArray);
				System.out.print("Добуток елементів масиву, розташованих між першим й другим нульовими елементами: " + result);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		// #2
		private static void Solve3() {
			System.out.println("--== Завдання №2 ==--");
			System.out.println("Написати підпрограму, що міняє місцями елементи масиву (перша половина елементів масиву міняється місцями з другою)");
			
			byte size;
			do {
				System.out.print("Введіть розмір масиву (Розмір масиву не повинен бути меншим за 1 та має бути парним): ");
				size = MyScanner.in.nextByte();
			} while (size < 1 || (size & 1) == 1);

			
			Double MyArray[] = new Double[size];
			String str = "";
			System.out.println("Масив, згенерований випадковим чином: ");
			for(byte i = 0; i < MyArray.length; ++i) {
				MyArray[i] = (int) ((Math.random() * 200.0 - 100.0) * 100.0) / 100.0;
				str += MyArray[i] + "  ";
			}
			System.out.println(str);
			
			System.out.println("Результат виконання підпрограми: ");
			MyArray = Task3(MyArray);
			for(byte i = 0; i < MyArray.length; ++i) {
				System.out.print(MyArray[i] + "  ");
			}
		}
		
		// Menu
		public static void CallMenu() {
			byte n;
			final String message = "\n-----===== Практична робота №1 =====-----\n" +
							"Оберіть № завдання:\n" +
							"1 - Завдання №1.1\n" +
							"2 - Завдання №1.2\n" +
							"3 - Завдання №2\n" +
							"0 - Вихід\n";
			while (true) {
				n = SomeUtils.enterByte(message, 0, 3);

			switch(n) {
			case 1: SolutionP1.Solve1();
			break;
			case 2: SolutionP1.Solve2();
			break;
			case 3: SolutionP1.Solve3();
			break;
			default: return;
			}
			}
		}
}
