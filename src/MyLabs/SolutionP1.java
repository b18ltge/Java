package MyLabs;

import MyUtils.MyScanner;

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
				throw new Exception("��������� ��������� ������� �������� ������;");
			
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
			System.out.println("--== �������� �1 ==--");
			System.out.println("���������� ���������� �� ������� �������� ������*;\n"
					+ "* - �������� �������� ������ ��������� ���������� ����� �� ������� [-100;100]");
			
			byte size;
			do {
				System.out.print("������ ����� ������ (����� ������ �� ������� ���� ������ �� 1): ");
				size = MyScanner.in.nextByte();
			} while (size < 1);


			Double MyArray[] = new Double[size];
			String str = "";
			System.out.println("�����, ������������ ���������� �����: ");
			for(byte i = 0; i < MyArray.length; ++i) {
				MyArray[i] = (int) ((Math.random() * 200.0 - 100.0) * 100.0) / 100.0;
				str += MyArray[i] + " ";
			}

			System.out.println(str);
			System.out.print("����� ���������� �� ������� �������� ������: " + Task1(MyArray));
		}

		// #1.2
		private static void Solve2() {
			System.out.println("--== �������� �2 ==--");
			System.out.println("���������� ������� �������� ������, ������������ �� ������ � ������ ��������� ����������;");
			
			byte size;
			do {
				System.out.print("������ ����� ������ (����� ������ �� ������� ���� ������ �� 1): ");
				size = MyScanner.in.nextByte();
			} while (size < 1);

			
			Double MyArray[] = new Double[size];
			String str = "";
			System.out.println("�����, ������������ ���������� �����: ");
			for(byte i = 0; i < MyArray.length; ++i) {
				MyArray[i] = (int) ((Math.random() * 200.0 - 100.0) * 100.0) / 100.0;
				str += MyArray[i] + " ";
			}
			System.out.println(str);
			
			double result = 0;
			try {
				result = Task2(MyArray);
				System.out.print("������� �������� ������, ������������ �� ������ � ������ ��������� ����������: " + result);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		// #2
		private static void Solve3() {
			System.out.println("--== �������� �3 ==--");
			System.out.println("�������� ����������, �� ���� ������ �������� ������ (����� �������� �������� ������ �������� ������ � ������)");
			
			byte size;
			do {
				System.out.print("������ ����� ������ (����� ������ �� ������� ���� ������ �� 1 �� �� ���� ������): ");
				size = MyScanner.in.nextByte();
			} while (size < 1 || (size & 1) == 1);

			
			Double MyArray[] = new Double[size];
			String str = "";
			System.out.println("�����, ������������ ���������� �����: ");
			for(byte i = 0; i < MyArray.length; ++i) {
				MyArray[i] = (int) ((Math.random() * 200.0 - 100.0) * 100.0) / 100.0;
				str += MyArray[i] + "  ";
			}
			System.out.println(str);
			
			System.out.println("��������� ��������� ����������: ");
			MyArray = Task3(MyArray);
			for(byte i = 0; i < MyArray.length; ++i) {
				System.out.print(MyArray[i] + "  ");
			}
		}
		
		// Menu
		public static void CallMenu() {
			byte n = -1;
			while (true) {
				do {
				System.out.println("\n-----===== ��������� ������ �1 =====-----");
				System.out.println("������ � ��������:");
				System.out.println("1 - �������� �1.1");
				System.out.println("2 - �������� �1.2");
				System.out.println("3 - �������� �2");
				System.out.println("0 - �����");
				try {
					n = MyScanner.in.nextByte();
				}
				catch (Exception ex) {
					MyScanner.in.skip(".*");
				}
			}while(n < 0 || n > 3);

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
