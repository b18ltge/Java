package MyLabs;

import java.io.*;
import MyUtils.MyScanner;
import MyUtils.MyConsoleBufferedReader;

public class SolutionL5{
	private static boolean CheckWord(String word) {
		if (word == null)
			return false;
		// �������� �� ���������
		else if ((word.length() & 1) == 1)				// 10101010(0,1)
			return false;								// 00000000  1
												// AND:	   00000000(0,1)
		
		else if (!word.matches("[a-zA-Z]+"))	//regex
			return false;
		
		int vowelCount = 0;
		int consonantCount = 0;
		for(int i = 0; i < word.length(); ++i) {
			switch(word.charAt(i)) {
			case 'A':
			case 'E':
			case 'O':
			case 'I':
			case 'U':
			case 'a':
			case 'e':
			case 'o':
			case 'i':
			case 'u': 
				if (++vowelCount > word.length() >> 1)	// ���� ��������/����������� ����� �������� -> return
					return false;
				break;
			default:
				if (++consonantCount > word.length() >> 1)
					return false;
			}
		}
		
		return vowelCount == consonantCount;
	}
	
	// #1
	private static void Solve1() {
		System.out.println("--== �������� �1 ==--");
		System.out.println("� ��������� ��������� ������ ��������� �����;");
		System.out.println("���������� ���������� �������� ������ 䳿:");
		System.out.println("a)���� ������� ���, �� ������ �������� ������� �������� � ����������� ����;");
		System.out.println("�)�������� �� ����� �������� �����;");
		System.out.println("��������� ����� ���� ������ ����� ����� ����������� �������, ������, �� ����. �������(������, ����);");
		System.out.print("������ ��������� �����: ");
		
		if (MyConsoleBufferedReader.buffReader == null)
			MyConsoleBufferedReader.buffReader = new BufferedReader(new InputStreamReader(System.in));
		
		String str = "";
		try {
			str = MyConsoleBufferedReader.buffReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		while(!str.matches("[a-zA-Z., ]+"))	//regex :)
		{
			System.out.println("��������� ����� ���� ������ ����� ����� ����������� �������, ������, �� ����. �������(������, ����);");
			System.out.print("������ ��������� �����: ");
			try {
				str = MyConsoleBufferedReader.buffReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		byte wordCount = 0;
		int pos1 = 0, pos2 = 0, maxLength = 0;	// ��� ���������� �����;
		for(int i = 0, startPos = -1, endPos = -1; i < str.length(); ++i) {
			if (str.charAt(i) == ' ' || str.charAt(i) == '.' || str.charAt(i) == ',') {
				// ʳ���� �����
				if (i - 1 >= 0) {
					if (str.charAt(i - 1) != ' ' && str.charAt(i - 1) != ',' && str.charAt(i - 1) != '.') {
						endPos = i;
						if (endPos - startPos > maxLength) {
							pos1 = startPos;
							pos2 = endPos;
							maxLength = endPos - startPos;
						}
					}
				}
				
				// �������� "�����������" �����
				if (startPos != -1) {
					if (SolutionL5.CheckWord(str.substring(startPos, endPos))) {
						++wordCount;
					}
					startPos = -1;
					endPos = -1;
				}
			}
			else {
				// ������� �����
				if (startPos == -1) {
					startPos = i;
					endPos = i;
				}
				// ���� ����� ���������� ������
				if (i == str.length() - 1) {
					endPos = i + 1;
					if (SolutionL5.CheckWord(str.substring(startPos, endPos))) {
						++wordCount;
					}
					if (endPos - startPos > maxLength) {
						pos1 = startPos;
						pos2 = endPos;
						maxLength = endPos - startPos;
					}
					//startPos = -1;
					//endPos = -1;
				}
			}
		}
		
		System.out.println("ʳ������ ���, �� ������ �������� ������� �������� �� �����������: " + wordCount);
		System.out.println("�������� ����� � �����: " + str.substring(pos1, pos2));
	}
	
	// #2
	private static void Solve2() {
		final String FilePath = "D:/eclipse-workspace/L1/MyFile.txt";
		final String NEWFilePath = "D:/eclipse-workspace/L1/MyNEWFile.txt";
		
		System.out.println("--== �������� �2 ==--");
		System.out.println("��������� � ���������� ����� ������� �����, ���� ���������� �������� '!';");
		System.out.println("���� ���������� ����� (" + FilePath + "):");
		
		// ���������� ����� ����� � ���� ������� StringBuffer
		StringBuffer Text = new StringBuffer("");
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(FilePath));
			try {
				String str;
				while((str = buffReader.readLine()) != null) {  // ��������� ������� ���������� �����
					Text.append(str + '\n');
				}
				System.out.println(Text);	
				buffReader.close();
			} 
			catch (IOException e) {
				e.getMessage();
			}
		} 
		catch (FileNotFoundException e) {
			e.getMessage();
		}
		
		
		// ������� ����� �����
		int startPos = 0;
		int endPos = 0;
		for(int i = 0; i < Text.length(); ++i) {
			if (Text.charAt(i) == '\n') {
				endPos = i;
				if (i - 1 >= 0) {
					if (Text.charAt(i - 1) == '!') {
						Text.delete(startPos, endPos + 1);
						break;
					}
					else startPos = i + 1;
				}
			}
		}
		Text.deleteCharAt(Text.length() - 1);	// �������� ������ '\n' � ����, ���� ��� ��������� ��� ���������
		System.out.println("���� ������ ����� ���� ��������� �������� (" + NEWFilePath + "):");
		System.out.println(Text);	
		
		// ����� �����
		try {
			BufferedWriter buffWriter = new BufferedWriter(new FileWriter(NEWFilePath));
			buffWriter.write(Text.toString());
			buffWriter.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// #3
	private static void Solve3() {
		final String InputFile = "D:/eclipse-workspace/L1/input.txt";
		final String OutputFile = "D:/eclipse-workspace/L1/output.txt";
		
		System.out.println("--== �������� �3 ==--");
		System.out.println("� ����� " + InputFile + " ����������� ��������� �����;");
		System.out.println("���������� ���������� �������� ������ 䳿:");
		System.out.println("a)���� ������� ��� � �����;");
		System.out.println("��������� ��������� ������ �������� ���������� � ���� " + OutputFile + ";");
		
		String str = "";
		// ���������� �����
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(InputFile));
			try {
				str = buffReader.readLine();
				System.out.println("���� ����� " + InputFile + ":");
				System.out.println(str);	
				buffReader.close();
			} 
			catch (IOException e) {
				e.getMessage();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		if (!str.matches("[a-zA-Z., ]+"))	//regex :)
		{
			System.out.println("��������� ����� ���� ������ ����� ����� ����������� ������� �� ������;");
			return;
		}
		
		byte wordCount = 0;
		for(int i = 0, startPos = -1; i < str.length(); ++i) {
			if (str.charAt(i) == ' ' || str.charAt(i) == '.' || str.charAt(i) == ',') {	
				// ϳ�������� ���
				if (startPos != -1) {
					++wordCount;
					startPos = -1;
				}
			}
			else {
				// ������� �����
				if (startPos == -1) {
					startPos = i;
				}
				// ���� ����� ���������� ������
				if (i == str.length() - 1) {
					++wordCount;
				}
			}
		}
		
		// ����� �����
		try {
			BufferedWriter buffWriter = new BufferedWriter(new FileWriter(OutputFile));
			buffWriter.write("ʳ������ ��� � �����: " + wordCount);
			buffWriter.close();
			System.out.println("��������� ������ �������� �������� � ���� " + OutputFile + ';');
		} 
		catch (IOException e) {
			e.getMessage();
		}
	}

	// Menu
	public static void CallMenu() {
			byte n = -1;
			while (true) {
				do {
				System.out.println("\n-----===== ����������� ������ �5 =====-----");
				System.out.println("������ � ��������:");
				System.out.println("1 - �������� �1");
				System.out.println("2 - �������� �2");
				System.out.println("3 - �������� �3");
				System.out.println("0 - �����");
				try {
					n = MyScanner.in.nextByte();
				}
				catch (Exception ex) {
					MyScanner.in.skip(".*");
				}
			}while(n < 0 || n > 3);

			switch(n) {
			case 1: SolutionL5.Solve1();
			break;
			case 2: SolutionL5.Solve2();
			break;
			case 3: SolutionL5.Solve3();
			break;
			default: return;
			}
			}
		}
}
