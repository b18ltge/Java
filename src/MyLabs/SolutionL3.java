package MyLabs;

import MyUtils.MyScanner;

public class SolutionL3{
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
		
		if (pos == -1) return "����� �� ������ ��'����� ��������;";
		
		long result = 1;
		for(byte i = 0; i < pos; ++i) {
			result *= MyArray[i];
		}
		
		return Long.toString(result);
	}
	
	// #1
	private static void Solve1() {
		System.out.println("--== �������� �1 ==--");
		System.out.println("���������� ���� ����� ��������� ������ ����� � ����� ������� n ��������;");
		System.out.print("������ ����� ������: ");
		
		byte size = MyScanner.in.nextByte();
		while (size < 3) {
			System.out.println("�������� ����� ������ < 3, ������� �����;");
			System.out.print("������ ����� ������: ");
			size = MyScanner.in.nextByte();
		}
		
		double MyArray[] = new double[size];
		for(byte i = 0; i < size; ++i) {
			System.out.print("������� �������� " + (i + 1) + "-�� ��������: ");
			MyArray[i] = MyScanner.in.nextDouble();
		}
		
		System.out.println("��������� �����: ");
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
		
		System.out.print("\n3 ��������� �������� ������: " + max1 + " " + max2 + " " + max3);
		System.out.println("\n����: " + (max1  + max2 + max3));
	}

	// #2
	private static void Solve2() {
		System.out.println("--== �������� �2 ==--");
		System.out.println("����������:\n"
				+ "1)C��� ������ ��'����� �������� ������;\n"
				+ "2)������� �������� ������ ������������ �� ���������� ��'������ ��������;");
		
		byte n = -1;
		do {
			System.out.println("������ ����� ���������� ������:");
			System.out.println("1 - � ���������");
			System.out.println("2 - ���������� ����� �� ������� [-100;100]");
			System.out.println("0 - �����");
			n = MyScanner.in.nextByte();
			
		} while(n > 2 || n < 0);
		
		if (n == 0) return;
		
		System.out.print("������ ����� ������: ");
		byte size = MyScanner.in.nextByte();
		while (size < 1) {
			System.out.println("�������� ����� ������ < 1, ������� �����;");
			System.out.print("������ ����� ������: ");
			size = MyScanner.in.nextByte();
		}
		int MyArray[] = new int[size];
		
		
		if (n == 1) {
			for(byte i = 0; i < size; ++i) {
				System.out.print("������� �������� " + (i + 1) + "-�� ��������: ");
				MyArray[i] = MyScanner.in.nextInt();
			}
		}
		else {
			SolutionL3.FillArray(MyArray);
		}
		
		System.out.println("��������� �����: ");
		for(byte i = 0; i < size; ++i) {
			System.out.print(MyArray[i] + " ");
		}
		
		System.out.println("\n1)C��� ������ ��'����� �������� ������: " + SolutionL3.CalcSum(MyArray));
		System.out.println("2)������� �������� ������ ������������ �� ���������� ��'������ ��������: " + SolutionL3.CalcMul(MyArray));
	}

	// Menu
	public static void CallMenu() {
		byte n = -1;
		while (true) {
			do {
			System.out.println("\n-----===== ����������� ������ �3 =====-----");
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
		case 1: SolutionL3.Solve1();
		break;
		case 2: SolutionL3.Solve2();
		break;
		default: return;
		}
		}
	}
}
