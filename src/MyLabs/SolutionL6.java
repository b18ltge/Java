package MyLabs;

import Misc.L6_1.Student;
import Misc.L6_2.File;
import Misc.L6_2.FileAttribute;
import Misc.L6_2.FileList;
import MyUtils.MyScanner;
import MyUtils.MyConsoleBufferedReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.LinkedList;

public class SolutionL6{
	private static Student readStudent(String str) {
		if(str == null)
			return null;
		
		String[] words = new String[8];
		byte wordsPos = 0;
		
		for(int i = 0, startPos = 0; i < str.length(); ++i) {
			if(str.charAt(i) == ' ') {
				words[wordsPos] = str.substring(startPos, i);
				startPos = i + 1;
				++wordsPos;
			}
			if (i == str.length() - 1) {
				words[wordsPos] = str.substring(startPos, i + 1);
			}
		}
		
		byte Marks[] = {Byte.parseByte(words[3]), 
				Byte.parseByte(words[4]), 
				Byte.parseByte(words[5]), 
				Byte.parseByte(words[6]), 
				Byte.parseByte(words[7])};
		
		return new Student(words[0], words[1], Short.parseShort(words[2]), Marks);
	}
	
	private static LinkedList<Student> readFile(String FileName) throws Exception {
		LinkedList<Student> result = new LinkedList<Student>();
		
		BufferedReader buffReader = new BufferedReader(new FileReader(FileName));
		String str;
		while((str = buffReader.readLine()) != null) 
		{  
			Student student = readStudent(str);
			if (student != null)
			result.add(student);	// ��������� ������� ���������� �����
		}
		buffReader.close();
		
		return result;
	} 
	
	private static Student inputStudent() throws Exception {
		String name, surname;
		short number;
		byte Marks[] = new byte[5];

		System.out.print("������ ��'� �������� (��'� ���� ������ ����� ������� ����������� �������): ");
		name = MyConsoleBufferedReader.buffReader.readLine();
		System.out.print("������ ������� (������� ���� ������ ����� ������� ����������� �������): ");
		surname = MyConsoleBufferedReader.buffReader.readLine();
		System.out.print("������ � ������� ������ (� ������� ������ ������� ���� >= 0): ");
		number = MyScanner.in.nextShort();
		System.out.print("������ ������ � ���������� [0..100]: ");
		Marks[0] = MyScanner.in.nextByte();
		System.out.print("������ ������ � ����㳿 [0..100]: ");
		Marks[1] = MyScanner.in.nextByte();
		System.out.print("������ ������ � �������� ���� [0..100]: ");
		Marks[2] = MyScanner.in.nextByte();
		System.out.print("������ ������ � ��������� [0..100]: ");
		Marks[3] = MyScanner.in.nextByte();
		System.out.print("������ ������ � ������ [0..100]: ");
		Marks[4] = MyScanner.in.nextByte();

		return new Student(name,surname,number,Marks);
	}
	
	private static void CallEditMenu(FileList fileList, byte id) throws Exception {
		System.out.println("���������� ��� ���� � ���������� ������� " + id +":");
		System.out.println(id + ") " + fileList.at(--id).toString());
		byte n;
		do {
			System.out.println("������ �������� ��� �����������:");
			System.out.println("1 - ����� �����;");
			System.out.println("2 - ���������� �����;");
			System.out.println("3 - ����� �����;");
			System.out.println("4 - ���� ��������� �����;");
			System.out.println("5 - �������� �����;");
			System.out.println("0 - �����;");
			n = MyUtils.MyScanner.in.nextByte();
		} while(n < 0 || n > 5);
		
		switch(n) {
		case 1:{
			System.out.print("������ ���� ����� ����� (����� ���� ������ ����� ������� ����������� ������� �� �����):");
			String name = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
			if (fileList.edit(id, name, (byte)1) == true)
				System.out.println("����� ����� ������ ������;");
			break;
		}
		case 2:{
			System.out.print("������ ���� ���������� ����� (���������� ���� ������ ����� ������� ����������� ������� �������� �������):");
			String extension = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
			if (fileList.edit(id, extension, (byte)-1) == true)
				System.out.println("���������� ����� ������ ������;");
			break;
		}
		case 3:{ 
			System.out.print("������ ����� ����� ����� (� ������):");
			long size = MyUtils.MyScanner.in.nextLong();
			if (fileList.edit(id, size) == true)
				System.out.println("����� ����� ������ ������;");
			break;
		}
		case 4:{
			System.out.print("������ ���� ���� ��������� ����� (yyyy-mm-dd):");
			LocalDate date;
			try {
				date = LocalDate.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
			}
			catch(Exception ex) {
				date = null;
			}
			if (fileList.edit(id, date) == true)
				System.out.println("���� ��������� ����� ������ ������;");
			break;
		}
		case 5:{
			System.out.print("������ �������� ����� [(R/-)(W/-)(X/-)]: ");
			FileAttribute FileAtt;
			try {
				FileAtt = FileAttribute.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
			} catch (Exception e) {
				FileAtt = null;
			}
			if (fileList.edit(id, FileAtt) == true)
				System.out.println("�������� ����� ������ ������;");
			break;
		}
		default: return;
		}
	}
	
	private static void CallRemoveMenu(FileList fileList) {
		byte n;
		do {
			System.out.println("������ �������� ��� ���������:");
			System.out.println("1 - ��������� �� ���������� �������;");
			System.out.println("2 - ��������� �� ������;");
			System.out.println("3 - ��������� �� �����������;");
			System.out.println("4 - ��������� �� �������;");
			System.out.println("5 - ��������� �� ����� ���������;");
			System.out.println("6 - ��������� �� ����������;");
			System.out.println("0 - �����;");
			n = MyUtils.MyScanner.in.nextByte();
		} while(n < 0 || n > 6);
		
		switch(n) {
		case 1:{
			byte id;
			System.out.println("��������� �� ���������� �������;");
			System.out.println("���������� ����� �� ������� ���� ������ �� 1 ��� ������ �� �������� ������� ������;");
			
			do {
				System.out.print("������ ���������� �����: ");
				id = MyUtils.MyScanner.in.nextByte();
			} while(id < 1 || id > fileList.size());

			fileList.remove((byte) (id - 1));
			System.out.println("����� � ���������� ������� " + id + " �������� ������;");
			break;
		}
		case 2:{
			System.out.println("��������� �� ������;");
			System.out.print("������ ����� ����� (����� ���� ������ ������� ����������� ������� �� �����):");
			String name;
			try {
				name = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
			} catch (IOException e) {
				name = null;
			}
			
			byte All;
			do {
				System.out.print("��������� ������� �����[0] �� ��� �����[1] �� ������� ������?: ");
				All = MyUtils.MyScanner.in.nextByte();
			} while(All != 0 && All != 1);
			System.out.println("�������� ������ " + fileList.remove(name, (byte)1, All) + " �����;");
			break;
		}
		case 3:{
			System.out.println("��������� �� �����������;");
			System.out.print("������ ���������� �����(���������� ���� ������ ����� ������� ����������� ������� �������� �������): ");
			String extension;
			try {
				extension = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
			} catch (IOException e) {
				extension = null;
			}
			
			byte All;
			do {
				System.out.print("��������� ������� �����[0] �� ��� �����[1] �� ������� �����������?: ");
				All = MyUtils.MyScanner.in.nextByte();
			} while(All != 0 && All != 1);
			System.out.println("�������� ������ " + fileList.remove(extension, (byte)0, All) + " �����;");
			break;
		}
		case 4:{
			System.out.println("��������� �� �������;");
			System.out.print("������ ����� ����� (���������: 32.67KB):");
			String StringSize;
			try {
				StringSize = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
			} catch (IOException e) {
				StringSize = null;
			}
			
			byte All;
			do {
				System.out.print("��������� ������� �����[0] �� ��� �����[1] �� ������� �������?: ");
				All = MyUtils.MyScanner.in.nextByte();
			} while(All != 0 && All != 1);
			System.out.println("�������� ������ " + fileList.remove(StringSize, All) + " �����;");
			break;
		}
		case 5:{
			System.out.println("��������� �� ����� ���������;");
			System.out.print("������ ���� ��������� (yyyy-mm-dd):");
			LocalDate Date;
			try {
				Date = LocalDate.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
			} catch (Exception e) {
				Date = null;
			}
			
			byte All;
			do {
				System.out.print("��������� ������� �����[0] �� ��� �����[1] �� ������� ����� ���������?: ");
				All = MyUtils.MyScanner.in.nextByte();
			} while(All != 0 && All != 1);
			System.out.println("�������� ������ " + fileList.remove(Date, All) + " �����;");
			break;
		}
		case 6:{
			System.out.println("��������� �� ����������;");
			System.out.print("������ �������� [(R/-)(W/-)(X/-)]: ");
			FileAttribute FileAtt;
			try {
				FileAtt = FileAttribute.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
			} catch (Exception e) {
				FileAtt = null;
			}
			
			byte All;
			do {
				System.out.print("��������� ������� �����[0] �� ��� �����[1] �� �������� ����������?: ");
				All = MyUtils.MyScanner.in.nextByte();
			} while(All != 0 && All != 1);
			System.out.println("�������� ������ " + fileList.remove(FileAtt, All) + " �����;");
			break;
		}
		default: return;
		}
	}
	
	private static void CallMenuForSolve2(FileList fileList) throws IOException {
		while(true) {
			byte n;
			do {
				System.out.println("������ ��������:");
				System.out.println("1 - ��������� ���������� ��� ����� �� �����;");
				System.out.println("2 - ������ ����� �����;");
				System.out.println("3 - �������� �����;");
				System.out.println("4 - ����� ������(�� ����� ���������);");
				System.out.println("5 - ���������� ������(�� �����������);");
				System.out.println("6 - ����������� ������;");
				System.out.println("0 - �����;");
				n = MyUtils.MyScanner.in.nextByte();
			}	while(n < 0 || n > 6);
			
			switch(n) {
			case 1: {
				System.out.println("���������� ��� �� ����� ���� �����:");
				fileList.print();
				break;
			}
			case 2:{
				System.out.println("��������� ������ ������:");
				System.out.println("������ ����� ����� (����� ���� ������ ������� ����������� ������� �� �����):");
				String name = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
				System.out.println("������ ���������� ����� (���������� ���� ������ ����� ������� ����������� ������� �������� �������):");
				String extension = MyUtils.MyConsoleBufferedReader.buffReader.readLine();
				System.out.println("������ ����� ����� (� ������):");
				long size = MyUtils.MyScanner.in.nextLong();
				System.out.println("������ ���� ��������� ����� (yyyy-mm-dd):");
				LocalDate date;
				try {
					date = LocalDate.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
				}
				catch (DateTimeParseException e) {
					date = null;
				}
				System.out.println("������ �������� ����� (R/-)(W/-)(X/-):");
				FileAttribute FileAtt = FileAttribute.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
				
				File newFile;
				try {
					newFile = new File(name,extension,size,date,FileAtt);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				
				fileList.add(newFile);
				fileList.WriteToFile();
				break;
			}
			case 3:{
				System.out.println("��������� ������:");
				CallRemoveMenu(fileList);
				fileList.WriteToFile();
				break;
			}
			case 4:{
				System.out.println("����� ������ �� ����� ���������:");
				System.out.print("������ ���� ��������� (yyyy-mm-dd): ");
				LocalDate date;
				try {
					date = LocalDate.parse(MyUtils.MyConsoleBufferedReader.buffReader.readLine());
				}
				catch(Exception ex) {
					date = null;
				}
				LinkedList<File> result = fileList.search(date);
				System.out.println("��������� ������:");
				if (result.isEmpty() == true) {
					System.out.println("�� �������� ������� ����� �� ������� ����� ���������;");
					continue;
				}
				
				for(var i : result) {
					System.out.println(i.toString());
				}
				break;
			}
			case 5:{
				System.out.println("���������� ������ �� �����������:");
				fileList.sort();
				fileList.WriteToFile();
				System.out.println("������ ������ �����������;");
				break;
			}
			case 6:{
				byte id;
				System.out.println("����������� ������:");
				System.out.println("���������� ����� �� ������� ���� ������ �� 1 ��� ������ �� �������� ������� ������;");
				
				do {
					System.out.print("������ ���������� �����: ");
					id = MyUtils.MyScanner.in.nextByte();
				} while(id < 1 || id > fileList.size());
				
				// ����������� id-�� ������ �� ������ �����������;
				// 
				try {
					CallEditMenu(fileList,id);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				break;
			}
			default: return;
			}
		}
	}
	
	private static void Solve1() {
		System.out.println("--== �������� �1 ==--");
		System.out.println("������ � ����������� ��� �������� ����� ��������;"); 
		System.out.println("����������� ���������� ���������� ������ ���� ��������:"); 
		System.out.println("�) ���������� ������ � ������� ��������� ��������� ���������� ���� �� �������� �� �� ����� � ���� �������;\n"
				+ "�) ������� ������� ��������, �� ����� ���������� ������;");
		
		byte n;
		do {
			System.out.println("������ ����� ������� ������ ��������:");
			System.out.println("1 - � ���������");
			System.out.println("2 - ������ � ���������� ����� Students.txt");
			System.out.println("0 - �����");
			n = MyScanner.in.nextByte();
		} while(n < 0 || n > 2);
		
		
		LinkedList<Student> StudentList;
		switch(n) {
		case 1: {
			System.out.println("�������� ���������� ��� �������� � ���������:");
			if (MyConsoleBufferedReader.buffReader == null)
				MyConsoleBufferedReader.buffReader = new BufferedReader(new InputStreamReader(System.in));
			StudentList = new LinkedList<Student>();
			
			
			String answer = null;
			do {
				try {
					StudentList.add(inputStudent());
				} catch (Exception e1) {
					System.out.print("���������� ��� �������� ���� ������ ����������.");
					System.out.print(e1.getMessage());
					MyScanner.in.skip(".*");
				}
				
				System.out.print("�� ���� ������ ���������� ��� ���������� ��������? (Y - ���, N - �): ");
				try {
					answer = MyConsoleBufferedReader.buffReader.readLine();
				} catch (IOException e) {
					System.out.print(e.getMessage());
					return;
				}
			} while(answer.equals("y") || answer.equals("Y"));
			// while(answer == "y" || answer == "Y"); // WTF ???!!!
			break;
		}
		case 2: {
			System.out.println("������ ���������� ��� �������� � ����� Students.txt:");
			try {
				StudentList = readFile("D:/eclipse-workspace/L1/Students.txt");
				
			} catch (Exception e) {
				System.out.println("�� ������� ������� ���������� ��� ��������� � �����.");
				e.printStackTrace();
				return;
			}
			break;
		}
		default: return;
		}
		
		
		System.out.println("--== ���������� ��� �������� ==--");
		if (StudentList.size() == 0) {
			System.out.println("--== ������� �������� �������! ==--");
			return;
		}
		
		System.out.println("��'�\t\t�������\t� �������\t����������\t�������\t�������� ����\t���������\tԳ����\t\t������� ���");
		for(Student i : StudentList) {
			i.printInfo();
		}
		
		Collections.sort(StudentList);
		
		byte count = 0;
		System.out.println("--== ���������� ��� �������� ==--");
		System.out.println("��'�\t\t�������\t� �������\t����������\t�������\t�������� ����\t���������\tԳ����\t\t������� ���");
		for(Student i : StudentList) {
			i.printInfo();
			if (i.hasLowMarks())
				++count;
		}
		
		float precent = count * 1.0f / StudentList.size() * 100.f;
		System.out.printf("³������ ��������, �� ����� ���������� ������: %.3f%%",precent);
		System.out.println("\n������������ ��������� ������, ��� � ������ �� 20;");
	}
	
	private static void Solve2() {
		final String FilePath = "D:/eclipse-workspace/L1/FileList.txt";
		System.out.println("--== �������� �2 ==--");
		System.out.println("���������� ���������� ��� ������ � ����� �����, �� ���������� � ���������� ����");
		System.out.println("���� ����� ������ ���������� ��� �����(�����, ����������, ���� ���������, �����, ��������)");
		System.out.println("���������� ���������� � ���� " + FilePath);
	
		
		FileList fileList = null;
		try {
			fileList = new FileList(FilePath);
		} catch (Exception e) {
			System.out.print("�� ������� ������� ���������� � �����;");
			e.printStackTrace();
			return;
		}
	
		try {
			CallMenuForSolve2(fileList);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static void CallMenu() {
		byte n = -1;
		while (true) {
			do {
			System.out.println("\n-----===== ����������� ������ �6 =====-----");
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
		case 1: SolutionL6.Solve1();
		break;
		case 2: SolutionL6.Solve2();
		break;
		default: return;
		}
		}
	}
}