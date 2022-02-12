import java.util.Scanner;

//L1
//09.02.2021
class SolutionL1{
	// #1
	public static void Solve1() {
		System.out.println("--== Завдання №1 ==--");
		System.out.println("Обчислення значення числа e в залежності від значень чисел f та r");
		System.out.println("e = 3f + 2r, f = 5");
		System.out.println("e = √(8r) / (2f - 4), f = 0");
		System.out.println("e = fr - 9, f = -5");
		System.out.println("f є цілим числом, e - дійсним");
		System.out.print("Введіть значення числа f: ");
		byte f = Main.in.nextByte();
		System.out.print("Введіть значення числа r: ");
		double r = Main.in.nextDouble();
		
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
	public static void Solve2() {
		double x1,y1,x2,y2,x3,y3;
		
		System.out.println(" --== Завдання №2 ==--");
		System.out.println("Перевірка належності 3 точок одній прямій");
		System.out.print("Введіть координати першої точки (x1 y1): ");
		x1 = Main.in.nextFloat();
		y1 = Main.in.nextFloat();
		System.out.print("Введіть координати другої точки (x2 y2): ");
		x2 = Main.in.nextFloat();
		y2 = Main.in.nextFloat();
		System.out.print("Введіть координати третьої точки (x3 y3): ");
		x3 = Main.in.nextFloat();
		y3 = Main.in.nextFloat();
		
		double expr = (x3 - x1) / (x2 - x1) - (y3 - y1) / (y2 - y1);
		if ( Math.pow(expr,2) <= 1e-8 ) {
			System.out.println("Задані 3 точки лежать на одній прямій;");
		}
		else {
			System.out.println("Задані 3 точки НЕ лежать на одній прямій;");
		}
		
	}

	// #3
	public static void Solve3() {
		System.out.println("--== Завдання №3 ==--");
		System.out.print("Введіть порядковий номер години в добі: ");
		byte n = Main.in.nextByte();
		
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
	public static void Solve4() {
		System.out.println("--== Завдання №4 ==--");
		System.out.println("Обчислення значення функції t залежно від значень змінних x,y,z;");
		System.out.println("t = √(3x / (z - 2y)) - yz + sin(x^2)");
		System.out.print("Введіть значення змінної x:");
		double x = Main.in.nextDouble();
		System.out.print("Введіть значення змінної y:");
		double y = Main.in.nextDouble();
		System.out.print("Введіть значення змінної z:");
		double z = Main.in.nextDouble();
		
		if (z - 2 * y == 0)
			throw new ArithmeticException("Для заданих значень y та z виконується ділення на 0;"); 
		else if (3 * x / (z - 2 * y) < 0)
			throw new ArithmeticException("Для заданих значень x, y та z підкореневий вираз < 0"); 
		
		double t = Math.sqrt(3 * x / (z - 2 * y)) - y * z + Math.sin(x * x);
		System.out.println("Результат: t = " + t);
	}

	// Menu
	public static void CallMenu() {
		byte n;
		while (true) {
			do {
			System.out.println("\n-----===== Лабораторна робота №1 =====-----");
			System.out.println("Оберіть № завдання:");
			System.out.println("1 - Завдання №1");
			System.out.println("2 - Завдання №2");
			System.out.println("3 - Завдання №3");
			System.out.println("4 - Завдання №4");
			System.out.println("0 - Вихід");
			n = Main.in.nextByte();
		}while(n < 0 || n > 4);

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


//L2
//12.02.2022
class SolutionL2{
	public static short Calc(int a, int b) {
		if (a > b)
			return 0;
		
		return (short) (a * a + Calc(a + 2,b));
	}
	
	// #1
	public static void Solve1() {
		System.out.println("--== Завдання №1 ==--");
		System.out.println("Обчислення значення функції а в залежності від значень цілих змінних x та y");
		System.out.println("a = (1 + y)(x + (y / (x^2 + 4)) / (e^(-x - 2) - (1 / (x^2 + 4)))");
		System.out.print("Введіть значення змінної x: ");
		int x = Main.in.nextInt();
		System.out.print("Введіть значення змінної y: ");
		int y = Main.in.nextInt();
		
		// Знаменники дробів завжди > 0 при будь-яких x та y :)
		double a = (1.0 + y) * (x + (y / (x*x + 4.0) ) ) / (Math.exp(-1.0 * x - 2.0) + (1.0 / (x*x + 4.0) ) );
		System.out.printf("Результат: a = %.3f",a);
		
	}
	
	// #2
	public static void Solve2() {
		System.out.println("--== Завдання №2 ==--");
		System.out.println("Обчислення суми квадратів всіх непарних чисел у діапазоні [20;30]");
		
		byte n;
		while(true) {
			do {
				System.out.println("\nОберіть спосіб реалізації:");
				System.out.println("1 - Використання циклу з параметром");
				System.out.println("2 - Використання циклу з передумовою");
				System.out.println("3 - Використання циклу з післяумовою");
				System.out.println("4 - Без використання циклу");
				System.out.println("0 - Вихід");
				n = Main.in.nextByte();
			} while (n < 0 || n > 4);
			
			short result = 0;
			switch(n) {
			case 1: {
				for(short i = 21; i < 30; i += 2) {
					result += i * i;
				}
				break;
			}
			case 2: {
				short i = 19;
				while((i += 2) < 30) {
					result += i * i;
				}
				break;
			}
			case 3: {
				short i = 21;
				do {
					result += i * i;
				}
				while((i += 2) < 30);
				break;
			}
			case 4: {
				//result = 3165;
				result = SolutionL2.Calc(21,30);
				break;
			}
			default: return;
			}
			System.out.printf("Результат обчислень: %d", result);
		}
	}

	// #3
	public static void Solve3() {
		System.out.println("--== Завдання №3 ==--");
		System.out.println("Обчислення суми добутків елементів числових послідовностей");
		System.out.println("S(П(i / j))  i = 2..7, j = 1..i-1");
		float result = 0.0f;
		for(byte i = 2; i < 8; ++i) {
			float res = 1;
			for(byte j = 1; j < i; ++j) {
				res *= i / (float)j; 
			}
			result += res;
		}
		System.out.printf("Результат обчислень: %.3f", result);
	}

	// #4
	public static void Solve4() {
		System.out.println("--== Завдання №4 ==--");
		System.out.println("Обчислення значень функції f(x) на проміжку [a,b] з кроком dx");
		System.out.println("y = sin(x) / x;   a = -π/2; b = π/2; dx = π/30");
		System.out.println("Результат обчислень: ");
		byte i = -15;
		for(double x = Math.PI / -2.0; x <= Math.PI / 2.0; x += Math.PI / 30.0, ++i) {
			if (i == 0) {
				System.out.println("Для x = 0 відбувається ділення на 0;");
				continue;
			}
			System.out.printf("x = %dπ/30 (%.3f); f(x) = %.3f\n", i, x, Math.sin(x) / x);
		}
	}

	// Menu
	public static void CallMenu() {
		byte n;
		while (true) {
			do {
			System.out.println("\n-----===== Лабораторна робота №2 =====-----");
			System.out.println("Оберіть № завдання:");
			System.out.println("1 - Завдання №1");
			System.out.println("2 - Завдання №2");
			System.out.println("3 - Завдання №3");
			System.out.println("4 - Завдання №4");
			System.out.println("0 - Вихід");
			n = Main.in.nextByte();
		}while(n < 0 || n > 4);

		switch(n) {
		case 1: SolutionL2.Solve1();
		break;
		case 2: SolutionL2.Solve2();
		break;
		case 3: SolutionL2.Solve3();
		break;
		case 4: {
			try{
				SolutionL2.Solve4();
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


final class Main {
	public static Scanner in = new Scanner(System.in);
	
	// Main Menu
	public static void CallMenu() {
		byte n;
		while (true) {
			do {
			System.out.println("Оберіть № лабораторної роботи:");
			System.out.println("1 - Лабораторна робота №1");
			System.out.println("2 - Лабораторна робота №2");
			System.out.println("0 - Вихід");
			n = Main.in.nextByte();
		}while(n < 0 || n > 2);

		switch(n) {
		case 1: SolutionL1.CallMenu();
		break;
		case 2: SolutionL2.CallMenu();
		break;
		default: {
			Main.in.close();
			return;
		}
		}
		}
	}
	
	public static void main(String[] args) {
		try {
			CallMenu();
		}
		catch (Exception ex) {
			Main.in.close();
			System.out.println("Щось пішло не так..");
		}
	}
}
