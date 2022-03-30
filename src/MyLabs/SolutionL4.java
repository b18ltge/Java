package MyLabs;

import MyUtils.MyScanner;

public class SolutionL4{
	// #1
	private static void Solve1() {
		System.out.println("--== �������� �1 ==--");
		System.out.println("����������� ������� �������� ������������� �� ���������� ��������;");
		
		System.out.print("������� ������ �������: ");
		byte Height = MyScanner.in.nextByte();
		while(Height < 1) {
			System.out.println("������� ������ ������� < 1, ������� ���� ��������;");
			System.out.print("������� ������ �������: ");
			Height = MyScanner.in.nextByte();
		}
		
		System.out.print("������� ������� �������: ");
		byte Length = MyScanner.in.nextByte();
		while(Length < 1) {
			System.out.println("������� ������� ������� < 1, ������� ���� ��������;");
			System.out.print("������� ������� �������: ");
			Length = MyScanner.in.nextByte();
		}
		
		int MyArray[][] = new int[Height][Length];
		for(byte i = 0; i < Height; ++i) {
			for(byte j = 0; j < Length; ++j) {
				System.out.print("������� �������� ��� �������� �������: ");
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
		
		System.out.println("������������ ������� �������: " + Max);
		System.out.println("ʳ������ �������� ������������� ��������: " + MaxCount);
		System.out.println("̳�������� ������� �������: " + Min);
		System.out.println("ʳ������ �������� ���������� ��������: " + MinCount);
	}
	
	// #2
	private static void Solve2() {
		System.out.println("--== �������� �2 ==--");
		System.out.println("�������� ������������ ������� ������� ������� �������;");
		
		System.out.print("������� ������� �������: ");
		byte Size = MyScanner.in.nextByte();
		while(Size < 2) {
			System.out.println("�������� ������ ������� < 2, ������� ���� ��������;");
			System.out.print("������� ������� �������: ");
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
					System.out.println("������ ������� �� � ����������� ������� ������� �������;");
					return;
				}
			}
		}
		System.out.println("������ ������� � ����������� ������� ������� �������;");
	}
	
	// Menu
	public static void CallMenu() {
		byte n = -1;
		while (true) {
			do {
			System.out.println("\n-----===== ����������� ������ �4 =====-----");
			System.out.println("������ � ��������:");
			System.out.println("1 - �������� �1");
			System.out.println("2 - �������� �2");
			System.out.println("0 - �����");
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