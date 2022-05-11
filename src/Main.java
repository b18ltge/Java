import java.io.IOException;

import MyLabs.*;
import MyUtils.*;
final class Main{
	// Main Menu
	private static void CallMenu() {
		byte n;
		String message = "Оберіть № лабораторної роботи:\n" +
						"1 - Лабораторна робота №1\n" +
						"2 - Лабораторна робота №2\n" +
						"3 - Лабораторна робота №3\n" +
						"4 - Лабораторна робота №4\n" +
						"5 - Лабораторна робота №5\n" +
						"6 - Лабораторна робота №6\n" +
						"7 - Лабораторна робота №7\n" +
						"8 - Лабораторна робота №8\n" +
						"-1 - Практична робота №1\n" +
						"0 - Вихід\n";
		while (true) {
			n = MyUtils.SomeUtils.enterByte(message, -1, 8);
			
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
				case 7: SolutionL7.CallMenu();
					break;
				case 8: SolutionL8.CallMenu();
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
