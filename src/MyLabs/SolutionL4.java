package MyLabs;

import MyUtils.MyScanner;

public class SolutionL4{
	// #1
	private static void Solve1() {
		System.out.println("--== Завдання №1 ==--");
		System.out.println("Знаходження кількості входжень максимального та мінімального елементів;");
		
		System.out.print("Задайте висоту матриці: ");
		byte Height = MyScanner.in.nextByte();
		while(Height < 1) {
			System.out.println("Введена висота матриці < 1, задайте інше значення;");
			System.out.print("Задайте висоту матриці: ");
			Height = MyScanner.in.nextByte();
		}
		
		System.out.print("Задайте довжину матриці: ");
		byte Length = MyScanner.in.nextByte();
		while(Length < 1) {
			System.out.println("Введена довжина матриці < 1, задайте інше значення;");
			System.out.print("Задайте довжину матриці: ");
			Length = MyScanner.in.nextByte();
		}
		
		int MyArray[][] = new int[Height][Length];
		for(byte i = 0; i < Height; ++i) {
			for(byte j = 0; j < Length; ++j) {
				System.out.print("Задайте значення для елемента матриці: ");
				MyArray[i][j] = MyScanner.in.nextInt();
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
		byte Size = MyScanner.in.nextByte();
		while(Size < 2) {
			System.out.println("Введений порядк матриці < 2, задайте інше значення;");
			System.out.print("Задайте порядок матриці: ");
			Size = MyScanner.in.nextByte();
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
		byte n = -1;
		while (true) {
			do {
			System.out.println("\n-----===== Лабораторна робота №4 =====-----");
			System.out.println("Оберіть № завдання:");
			System.out.println("1 - Завдання №1");
			System.out.println("2 - Завдання №2");
			System.out.println("0 - Вихід");
			try {
				n = MyScanner.in.nextByte();
			}
			catch (Exception ex) {
				MyScanner.in.skip(".*");
			}
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