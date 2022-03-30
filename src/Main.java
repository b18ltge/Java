import java.io.IOException;

import MyLabs.*;
import MyUtils.*;

final class Main{
	// Main Menu
	private static void CallMenu() {
		byte n = -1;
		while (true) {
			do {
			System.out.println("Оберіть № лабораторної роботи:");
			System.out.println("1 - Лабораторна робота №1");
			System.out.println("2 - Лабораторна робота №2");
			System.out.println("3 - Лабораторна робота №3");
			System.out.println("4 - Лабораторна робота №4");
			System.out.println("5 - Лабораторна робота №5");
			System.out.println("6 - Лабораторна робота №6");
			System.out.println("-1 - Практична робота №1");
			System.out.println("0 - Вихід");
			try {
				n = MyScanner.in.nextByte();
			}
			catch (Exception ex) {
				MyScanner.in.skip(".*");
			}
		} while(n < -1 || n > 6);

		switch(n) {
		case 1: SolutionL1.CallMenu();
		break;
		case 2: SolutionL2.CallMenu();
		break;
		case 3: SolutionL3.CallMenu();
		break;
		case 4: SolutionL4.CallMenu();
		break;
		case 5: SolutionL5.CallMenu();
		break;
		case 6: SolutionL6.CallMenu();
		break;
		case -1: SolutionP1.CallMenu();
		break;
		default: {
			MyScanner.in.close();
			
			if (MyConsoleBufferedReader.buffReader != null)
				try {
					MyConsoleBufferedReader.buffReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
			MyScanner.in.close();
			
			if (MyConsoleBufferedReader.buffReader != null)
				try {
					MyConsoleBufferedReader.buffReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			System.out.println("Щось пішло не так..");
			ex.printStackTrace();
		}
	}
}
