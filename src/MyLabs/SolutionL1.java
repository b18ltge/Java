package MyLabs;

import MyUtils.MyScanner;
import MyUtils.SomeUtils;

//L1
//09.02.2021
public class SolutionL1{
	// #1
	private static void Solve1() {
		System.out.println("--== Завдання №1 ==--");
		System.out.println("Обчислення значення числа e в залежності від значень чисел f та r");
		System.out.println("e = 3f + 2r, f = 5");
		System.out.println("e = √(8r) / (2f - 4), f = 0");
		System.out.println("e = fr - 9, f = -5");
		System.out.println("f є цілим числом, e - дійсним");
		byte f = SomeUtils.enterByte("Введіть значення числа f: ", Byte.MIN_VALUE, Byte.MAX_VALUE);
		double r = SomeUtils.enterDouble("Введіть значення числа r: ", Double.MIN_VALUE, Double.MAX_VALUE);
		
		double e = .0;
		
		if (f == 5) {
			e = 15.0 + 2 * r;
		}
		else if (f == 0) {
			if (r < 0) {
				System.out.print("Результат: Неможливо обчислити значення √(8r), оскільки r < 0");
				return;
			}
			e = Math.sqrt(8 * r) / -4.0;
		}
		else if (f == -5) {
			e = -5.0 * r - 9.0;
		}
		else {
			System.out.print("Результат: Неможливо обчислити значення e (Число f не задовільняє ні одну умову)");
		}
		
		/*if (f == 5) {
			e = 15.0 + 2 * r;
		}
		if (f == 0) {
			if (r < 0) {
				System.out.print("Результат: Неможливо обчислити значення √(8r), оскільки r < 0");
				return;
			}
			e = Math.sqrt(8 * r) / -4.0;
		}
		if (f == -5) {
			e = -5.0 * r - 9.0;
		}
		if (f != 5 && f != 0 && f != -5) {
			System.out.print("Результат: Неможливо обчислити значення e (Число f не задовільняє ні одну умову)");
			return;
		}*/
		
		System.out.printf("Результат: e = %f",e);
	}

	// #2
	private static void Solve2() {
		double x1,y1,x2,y2,x3,y3;
		
		System.out.println(" --== Завдання №2 ==--");
		System.out.println("Перевірка належності 3 точок одній прямій");
		System.out.print("Введіть координати першої точки (x1 y1): ");
		x1 = MyScanner.in.nextFloat();
		y1 = MyScanner.in.nextFloat();
		System.out.print("Введіть координати другої точки (x2 y2): ");
		x2 = MyScanner.in.nextFloat();
		y2 = MyScanner.in.nextFloat();
		System.out.print("Введіть координати третьої точки (x3 y3): ");
		x3 = MyScanner.in.nextFloat();
		y3 = MyScanner.in.nextFloat();
		
		double expr = (x3 - x1) / (x2 - x1) - (y3 - y1) / (y2 - y1);
		if ( Math.pow(expr,2) <= 1e-8 ) {
			System.out.println("Задані 3 точки лежать на одній прямій;");
		}
		else {
			System.out.println("Задані 3 точки НЕ лежать на одній прямій;");
		}
		
	}

	// #3
	private static void Solve3() {
		System.out.println("--== Завдання №3 ==--");
		System.out.print("Введіть порядковий номер години в добі: ");
		byte n = MyScanner.in.nextByte();
		
		String str;
		switch(n) {
		case 1: str = "Перша";
		break;
		case 2: str = "Друга";
		break;
		case 3: str = "Третя";
		break;
		case 4: str = "Четверта";
		break;
		case 5: str = "П'ята";
		break;
		case 6: str = "Шоста";
		break;
		case 7: str = "Сьома";
		break;
		case 8: str = "Восьма";
		break;
		case 9: str = "Дев'ята";
		break;
		case 10: str = "Десята";
		break;
		case 11: str = "Одинадцята";
		break;
		case 12: str = "Дванадцята";
		break;
		case 13: str = "Тринадцята";
		break;
		case 14: str = "Чотирнадцята";
		break;
		case 15: str = "П'ятнадцята";
		break;
		case 16: str = "Шістнадцята";
		break;
		case 17: str = "Сімнадцята";
		break;
		case 18: str = "Вісімнадцята";
		break;
		case 19: str = "Дев'ятнадцята";
		break;
		case 20: str = "Двадцята";
		break;
		case 21: str = "Двадцять перша";
		break;
		case 22: str = "Двадцять друга";
		break;
		case 23: str = "Двадцять третя";
		break;
		case 24: str = "Двадцять четверта";
		break;
		default: {
			System.out.println("Порядковий номер години в добі не повинен перевищувати 24 або бути меншим за 1;");
			return;
		}
		}
		System.out.println(str + " година");
		}

	// #4
	private static void Solve4() {
		System.out.println("--== Завдання №4 ==--");
		System.out.println("Обчислення значення функції t залежно від значень змінних x,y,z;");
		System.out.println("t = √(3x / (z - 2y)) - yz + sin(x^2)");
		System.out.print("Введіть значення змінної x:");
		double x = MyScanner.in.nextDouble();
		System.out.print("Введіть значення змінної y:");
		double y = MyScanner.in.nextDouble();
		System.out.print("Введіть значення змінної z:");
		double z = MyScanner.in.nextDouble();
		
		if (z - 2 * y == 0)
			throw new ArithmeticException("Для заданих значень y та z виконується ділення на 0;"); 
		else if (3 * x / (z - 2 * y) < 0)
			throw new ArithmeticException("Для заданих значень x, y та z підкореневий вираз < 0"); 
		
		double t = Math.sqrt(3 * x / (z - 2 * y)) - y * z + Math.sin(x * x);
		System.out.println("Результат: t = " + t);
	}

	// Menu
	public static void CallMenu() {
		byte n = -1;
		final String message = "\n-----===== Лабораторна робота №1 =====-----\n" +
						"Оберіть № завдання:\n" +
						"1 - Завдання №1\n" +
						"2 - Завдання №2\n" +
						"3 - Завдання №3\n" +
						"4 - Завдання №4\n" +
						"0 - Вихід\n";
		while (true) {
			n = SomeUtils.enterByte(message, 0, 4);
	
			switch(n) {
				case 1: SolutionL1.Solve1();
					break;
				case 2: SolutionL1.Solve2();
					break;
				case 3: SolutionL1.Solve3();
					break;
				case 4: {
					try{
						SolutionL1.Solve4();
					}
					catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
					break;
				default: return;
			}
		}
	}

}
