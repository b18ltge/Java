package MyLabs;

import MyUtils.MyScanner;
//L2
//12.02.2022
public class SolutionL2{
	private static short Calc(int a, int b) {
		if (a > b)
			return 0;
		
		return (short) (a * a + Calc(a + 2,b));
	}
	
	// #1
	private static void Solve1() {
		System.out.println("--== Завдання №1 ==--");
		System.out.println("Обчислення значення функції а в залежності від значень цілих змінних x та y");
		System.out.println("a = (1 + y)(x + (y / (x^2 + 4)) / (e^(-x - 2) - (1 / (x^2 + 4)))");
		int x,y;
		
		while(true) {
			try {
				System.out.print("Введіть значення змінної x: ");
				x = MyScanner.in.nextInt();
				System.out.print("Введіть значення змінної y: ");
				y = MyScanner.in.nextInt();
				break;
			}
			catch(java.util.InputMismatchException ex) {
				System.out.println("Введені значення задані некоректно. Задайте інше значення.");
				MyScanner.in.skip(".*");	// regex 
				continue;
			}
		}
		
		// Знаменники дробів завжди > 0 при будь-яких x та y
		double a = (1.0 + y) * (x + (y / (x*x + 4.0) ) ) / (Math.exp(-1.0 * x - 2.0) + (1.0 / (x*x + 4.0) ) );
		System.out.printf("Результат: a = %.3f",a);
		
	}
	
	// #2
	private static void Solve2() {
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
				n = MyScanner.in.nextByte();
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
	private static void Solve3() {
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
	private static void Solve4() {
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
		byte n = -1;
		while (true) {
			do {
			System.out.println("\n-----===== Лабораторна робота №2 =====-----");
			System.out.println("Оберіть № завдання:");
			System.out.println("1 - Завдання №1");
			System.out.println("2 - Завдання №2");
			System.out.println("3 - Завдання №3");
			System.out.println("4 - Завдання №4");
			System.out.println("0 - Вихід");
			try {
				n = MyScanner.in.nextByte();
			}
			catch (Exception ex) {
				MyScanner.in.skip(".*");
			}
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

