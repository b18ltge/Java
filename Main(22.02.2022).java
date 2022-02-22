import java.util.Scanner;

//L1
//09.02.2021
class SolutionL1{
	// #1
	private static void Solve1() {
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
	private static void Solve2() {
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
	private static void Solve3() {
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
	private static void Solve4() {
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
				x = Main.in.nextInt();
				System.out.print("Введіть значення змінної y: ");
				y = Main.in.nextInt();
				break;
			}
			catch(java.util.InputMismatchException ex) {
				System.out.println("Введені значення задані некоректно. Задайте інше значення.");
				Main.in.skip(".*");	// regex 
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


class SolutionL3{
	private static void FillArray(int[] MyArray) {
		for(byte i = 0; i < MyArray.length; ++i) {
			MyArray[i] = (int) (Math.random() * 200 - 100);
		}
	}
	
	private static int CalcSum(int[] MyArray) {
		int result = 0;
		for(byte i = 0; i < MyArray.length; ++i) {
			if (MyArray[i] < 0)
				result += MyArray[i] * -1;
		}
		return result;
	}
	
	private static String CalcMul(int[] MyArray) {
		int pos = -1;
		
		for(int i = MyArray.length - 1; i >= 0; --i) {
			if (MyArray[i] < 0) {
				pos = i;
				break;
			}
		}
		
		if (pos == -1) return "Масив не містить від'ємних елементів;";
		
		long result = 1;
		for(byte i = 0; i < pos; ++i) {
			result *= MyArray[i];
		}
		
		return Long.toString(result);
	}
	
	// #1
	private static void Solve1() {
		System.out.println("--== Завдання №1 ==--");
		System.out.println("Обчислення суми трьох найбільший дійсних чисел в масиві розміром n елементів;");
		System.out.print("Введіть розмір масиву: ");
		
		byte size = Main.in.nextByte();
		while (size < 3) {
			System.out.println("Введений розмір масиву < 3, задайте інший;");
			System.out.print("Введіть розмір масиву: ");
			size = Main.in.nextByte();
		}
		
		double MyArray[] = new double[size];
		for(byte i = 0; i < size; ++i) {
			System.out.print("Задайте значення " + (i + 1) + "-го елементу: ");
			MyArray[i] = Main.in.nextDouble();
		}
		
		System.out.println("Створений масив: ");
		for(byte i = 0; i < size; ++i) {
			System.out.print(MyArray[i] + " ");// 4 6 3 2 5 4
		}
		
		double max1 = Math.max(Math.max(MyArray[0], MyArray[1]),MyArray[2]);
		double max3 = Math.min(Math.min(MyArray[0], MyArray[1]),MyArray[2]);
		double max2 = (MyArray[0] != max1 && MyArray[0] != max3) ? MyArray[0] 
				: (MyArray[1] != max1 && MyArray[1] != max3) ? MyArray[1] : MyArray[2];
		
		for(byte i = 3; i < size; ++i) {
			if (MyArray[i] > max1) {
				max3 = max2;
				max2 = max1;
				max1 = MyArray[i];
			}
			else if (MyArray[i] > max2) {
				max3 = max2;
				max2 = MyArray[i];
			}
			else if (MyArray[i] > max3) {
				max3 = MyArray[i];
			}
		}
		
		System.out.print("\n3 Найбільших елементів масиву: " + max1 + " " + max2 + " " + max3);
		System.out.println("\nСума: " + (max1  + max2 + max3));
	}

	// #2
	private static void Solve2() {
		System.out.println("--== Завдання №2 ==--");
		System.out.println("Визначення:\n"
				+ "1)Cуми модулів від'ємний елементів масиву;\n"
				+ "2)Добутку елементів масиву розташованих до останнього від'ємного елемента;");
		
		byte n = -1;
		do {
			System.out.println("Оберіть спосіб заповнення масиву:");
			System.out.println("1 - З клавіатури");
			System.out.println("2 - Випадковим чином на проміжку [-100;100]");
			System.out.println("0 - Вихід");
			n = Main.in.nextByte();
			
		} while(n > 2 || n < 0);
		
		if (n == 0) return;
		
		System.out.print("Введіть розмір масиву: ");
		byte size = Main.in.nextByte();
		while (size < 1) {
			System.out.println("Введений розмір масиву < 1, задайте інший;");
			System.out.print("Введіть розмір масиву: ");
			size = Main.in.nextByte();
		}
		int MyArray[] = new int[size];
		
		
		if (n == 1) {
			for(byte i = 0; i < size; ++i) {
				System.out.print("Задайте значення " + (i + 1) + "-го елементу: ");
				MyArray[i] = Main.in.nextInt();
			}
		}
		else {
			SolutionL3.FillArray(MyArray);
		}
		
		System.out.println("Створений масив: ");
		for(byte i = 0; i < size; ++i) {
			System.out.print(MyArray[i] + " ");
		}
		
		System.out.println("\n1)Cума модулів від'ємних елементів масиву: " + SolutionL3.CalcSum(MyArray));
		System.out.println("2)Добуток елементів масиву розташованих до останнього від'ємного елемента: " + SolutionL3.CalcMul(MyArray));
	}

	// Menu
	public static void CallMenu() {
		byte n;
		while (true) {
			do {
			System.out.println("\n-----===== Лабораторна робота №3 =====-----");
			System.out.println("Оберіть № завдання:");
			System.out.println("1 - Завдання №1");
			System.out.println("2 - Завдання №2");
			System.out.println("0 - Вихід");
			n = Main.in.nextByte();
		}while(n < 0 || n > 2);

		switch(n) {
		case 1: SolutionL3.Solve1();
		break;
		case 2: SolutionL3.Solve2();
		break;
		default: return;
		}
		}
	}
}

class SolutionL4{
	// #1
	private static void Solve1() {
		System.out.println("--== Завдання №1 ==--");
		System.out.println("Знаходження кількості входжень максимального та мінімального елементів;");
		
		System.out.print("Задайте висоту матриці: ");
		byte Height = Main.in.nextByte();
		while(Height < 1) {
			System.out.println("Введена висота матриці < 1, задайте інше значення;");
			System.out.print("Задайте висоту матриці: ");
			Height = Main.in.nextByte();
		}
		
		System.out.print("Задайте довжину матриці: ");
		byte Length = Main.in.nextByte();
		while(Length < 1) {
			System.out.println("Введена довжина матриці < 1, задайте інше значення;");
			System.out.print("Задайте довжину матриці: ");
			Length = Main.in.nextByte();
		}
		
		int MyArray[][] = new int[Height][Length];
		for(byte i = 0; i < Height; ++i) {
			for(byte j = 0; j < Length; ++j) {
				System.out.print("Задайте значення для елемента матриці: ");
				MyArray[i][j] = Main.in.nextInt();
			}
		}
		
		
		int Max, Min;
		Max = Min = MyArray[0][0];
		byte MaxCount = 0;
		byte MinCount = 0;
		
		for(byte i = 0; i < Height; ++i) {
			for(byte j = 0; j < Length; ++j) {
				if (MyArray[i][j] > Max) {
					Max = MyArray[i][j];
					MaxCount = 1;
				}
				else if (MyArray[i][j] < Min) {
					Min = MyArray[i][j];
					MinCount = 1;
				}
				else {
					if (MyArray[i][j] == Max) {
						++MaxCount;
					}
					if (MyArray[i][j] == Min) {
						++MinCount;
					}
				}
				
				System.out.printf("%5d", MyArray[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("Максимальний елемент матриці: " + Max);
		System.out.println("Кількість входжень максимального елемента: " + MaxCount);
		System.out.println("Мінімальний елемент матриці: " + Min);
		System.out.println("Кількість входжень мінімального елемента: " + MinCount);
	}
	
	// #2
	private static void Solve2() {
		System.out.println("--== Завдання №2 ==--");
		System.out.println("Перевірка симетричності матриці відносно головної діагоналі;");
		
		System.out.print("Задайте порядок матриці: ");
		byte Size = Main.in.nextByte();
		while(Size < 2) {
			System.out.println("Введений порядк матриці < 2, задайте інше значення;");
			System.out.print("Задайте порядок матриці: ");
			Size = Main.in.nextByte();
		}
		
		
		//int MyArray[][] = {{1, 5, 7, 8}, {2, 6, 4, 3}, {3, 4, 7, 2}, {8, 7, 5, 9}}; //	Size = 4;
		int MyArray[][] = new int[Size][Size];
		for(byte i = 0; i < Size; ++i) {
			for(byte j = 0; j < Size; ++j) {
				MyArray[i][j] = (int) (Math.random() * 200 - 100);
				System.out.printf("%5d", MyArray[i][j]);
			}
			System.out.println();
		}
		

		
		for(byte i = 0; i < Size >> 1; ++i) {
			for(byte j = (byte)(Size - 1); j > i; --j) {
				if (MyArray[i][j] != MyArray[Size - i - 1][Size - 1 - j]) {
					System.out.println("Задана матриця НЕ є симетричною відносно головної діагоналі;");
					return;
				}
			}
		}
		System.out.println("Задана матриця є симетричною відносно головної діагоналі;");
	}
	
	// Menu
	public static void CallMenu() {
		byte n;
		while (true) {
			do {
			System.out.println("\n-----===== Лабораторна робота №4 =====-----");
			System.out.println("Оберіть № завдання:");
			System.out.println("1 - Завдання №1");
			System.out.println("2 - Завдання №2");
			System.out.println("0 - Вихід");
			n = Main.in.nextByte();
		}while(n < 0 || n > 2);

		switch(n) {
		case 1: SolutionL4.Solve1();
		break;
		case 2: SolutionL4.Solve2();
		break;
		default: return;
		}
		}
	}
}


final class Main {
	public static Scanner in = new Scanner(System.in);
	
	// Main Menu
	private static void CallMenu() {
		byte n;
		while (true) {
			do {
			System.out.println("Оберіть № лабораторної роботи:");
			System.out.println("1 - Лабораторна робота №1");
			System.out.println("2 - Лабораторна робота №2");
			System.out.println("3 - Лабораторна робота №3");
			System.out.println("4 - Лабораторна робота №4");
			System.out.println("0 - Вихід");
			n = Main.in.nextByte();
		}while(n < 0 || n > 4);

		switch(n) {
		case 1: SolutionL1.CallMenu();
		break;
		case 2: SolutionL2.CallMenu();
		break;
		case 3: SolutionL3.CallMenu();
		break;
		case 4: SolutionL4.CallMenu();
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
