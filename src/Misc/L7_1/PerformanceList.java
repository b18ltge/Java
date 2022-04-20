package Misc.L7_1;

import java.util.LinkedList;

import Misc.L7_1.Poet.Language;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class PerformanceList {
	private BufferedReader buffReader;
	private BufferedWriter buffWriter;
	private String PerformanceName;
	private LinkedList<Performance> performanceList;
	
	private Performance readPerformanceInfo(String str) throws Exception {
		if(str == null)
			return null;
		
		String[] words = new String[6];
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
		
		Language lang;
		switch (words[1]) {
		case "EN": {
			lang = Language.EN;
			break;
		}
		case "DE": {
			lang = Language.DE;
			break;
		}
		case "FR": {
			lang = Language.FR;
			break;
		}
		case "UKR": {
			lang = Language.UKR;
			break;
		}
		default: throw new Exception("Помилка зчинання мови поета з файлу;");
		}
		
		return new Performance(words[0], lang, Byte.parseByte(words[2]), 
				LocalDate.parse(words[3]), words[4], Short.parseShort(words[5]));
	}
	private void ReadFromFile() throws Exception {
		if (!performanceList.isEmpty())
			performanceList.clear();
		
		String str;
		while((str = buffReader.readLine()) != null) 
		{  
			Performance file = readPerformanceInfo(str);
			if (file != null)
			performanceList.add(file);	// порядкове читання текстового файлу
		}
	}
	public void WriteToFile() throws IOException {
		buffWriter = new BufferedWriter(new FileWriter(PerformanceName));
		for(var i : performanceList) {
			buffWriter.write(i.getSurname() + " " + i.getLanguageString() + " " + i.getCount() + " " + i.getDate().toString() + " " + i.getLocation() + " " + i.getListeners() + "\n");
		}
		buffWriter.close();
	}
	
	public PerformanceList(String newPerformanceName) throws Exception {
		if (newPerformanceName == null)
			throw new Exception("Invalid Performance Path;\n");
		buffReader = new BufferedReader(new FileReader(newPerformanceName));
		performanceList = new LinkedList<Performance>();
		PerformanceName = newPerformanceName;
		ReadFromFile();
	}
	protected void finalize() throws Throwable
	{
		buffReader.close();
		buffWriter.close();
	}
	public int size() {
		return performanceList.size();
	}
	public void add(Performance newPerformance) throws IOException {
		if (newPerformance == null)
			return;
		performanceList.add(newPerformance);
		WriteToFile();
	}
	public Performance at(byte id) {
		if (id < 0 || id >= performanceList.size())
			return null;
		byte it = 0;
		for(var file : performanceList) {
			if (it++ == id)
				return file.clone();
		}
		return null;
	}
	
	
	public byte remove(LocalDate Date, byte All) {
		byte PrevSize = (byte) performanceList.size();
		if (Date == null) return 0;
		
		if (All > 0) {
			performanceList.removeIf(n -> (n.getDate().equals(Date)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - performanceList.size());
		}

		for(var i : performanceList) {
			if (i.getDate().equals(Date)) {
				performanceList.remove(i);	
				try {
					WriteToFile();
				} catch (IOException e) {
					System.out.println("Не вдалось вилучити запис з файлу;");
				}
				return 1;
			}
		}
		
		return 0;
	}
	public byte remove(String SurnameOrLocation, byte flag, byte All) {
		byte PrevSize = (byte) performanceList.size();
		
		if (SurnameOrLocation == null) return 0;
		
		if (All > 0) {
			if (flag > 0) performanceList.removeIf(n -> (n.getSurname().equals(SurnameOrLocation)));
			else performanceList.removeIf(n -> (n.getLocation().equals(SurnameOrLocation)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - performanceList.size());
		}

		for(var i : performanceList) {
			if (flag > 0 && i.getSurname().equals(SurnameOrLocation)) {
				performanceList.remove(i);
				try {
					WriteToFile();
				} catch (IOException e) {
					System.out.println("Не вдалось вилучити запис з файлу;");
				}
				return 1;
			}
			else if (flag <= 0 && i.getLocation().equals(SurnameOrLocation)) {
				performanceList.remove(i);
				try {
					WriteToFile();
				} catch (IOException e) {
					System.out.println("Не вдалось вилучити запис з файлу;");
				}
				return 1;
			}
		}
		
		return 0;
	}
	public byte remove(short Listeners, byte All) {
		byte PrevSize = (byte) performanceList.size();
		if (Listeners < 1) return 0;
		
		if (All > 0) {
			performanceList.removeIf(n -> (n.getListeners() == Listeners));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - performanceList.size());
		}

		for(var i : performanceList) {
			if (i.getListeners() == Listeners) {
				performanceList.remove(i);	
				try {
					WriteToFile();
				} catch (IOException e) {
					System.out.println("Не вдалось вилучити запис з файлу;");
				}
				return 1;
			}
		}
		
		return 0;
	}
	public byte remove(byte Count, byte All) {
		byte PrevSize = (byte) performanceList.size();
		if (Count < 1) return 0;
		
		if (All > 0) {
			performanceList.removeIf(n -> (n.getCount() == Count));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - performanceList.size());
		}

		for(var i : performanceList) {
			if (i.getCount() == Count) {
				performanceList.remove(i);	
				try {
					WriteToFile();
				} catch (IOException e) {
					System.out.println("Не вдалось вилучити запис з файлу;");
				}
				return 1;
			}
		}
		
		return 0;
	}
	public byte remove(Language lang, byte All) {
		byte PrevSize = (byte) performanceList.size();
		if (lang == null) return 0;
		
		if (All > 0) {
			performanceList.removeIf(n -> (n.getLanguage().equals(lang)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - performanceList.size());
		}

		for(var i : performanceList) {
			if (i.getLanguage().equals(lang)) {
				performanceList.remove(i);	
				try {
					WriteToFile();
				} catch (IOException e) {
					System.out.println("Не вдалось вилучити запис з файлу;");
				}
				return 1;
			}
		}
		
		return 0;
	}
	public void remove(byte id) {
		performanceList.remove(id);
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось вилучити запис з файлу;");
		}
	}
	
	// if NameOrExtension >= 0, edits Performance Name, else edits Performance Extension;
	public boolean edit(int id, String newSurnameOrLocation, byte SurnameOrLocation) {
		if (id < 0 || id >= performanceList.size())
			return false;
		try {
			if (SurnameOrLocation >= 0)
				performanceList.get(id).setSurname(newSurnameOrLocation);
			else 
				performanceList.get(id).setLocation(newSurnameOrLocation);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не змінити запис в файлі;");
		}
		return true;
	}
	public boolean edit(int id, short newListeners) {
		if (id < 0 || id >= performanceList.size())
			return false;
		try {
			performanceList.get(id).setListeners(newListeners);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не змінити запис в файлі;");
		}
		return true;
	}
	public boolean edit(int id, byte newCount) {
		if (id < 0 || id >= performanceList.size())
			return false;
		try {
			performanceList.get(id).setCount(newCount);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не змінити запис в файлі;");
		}
		return true;
	}
	public boolean edit(int id, LocalDate newDate) {
		if (id < 0 || id >= performanceList.size())
			return false;
		try {
			performanceList.get(id).setDate(newDate);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false; 
		}
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не змінити запис в файлі;");
		}
		return true;
	}
	public boolean edit(int id, Language newLanguage) {
		if (id < 0 || id >= performanceList.size())
			return false;
		try {
			performanceList.get(id).setLanguage(newLanguage);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не змінити запис в файлі;");
		}
		return true;
	}
	public void print() {
		byte id = 0;
		System.out.println("Порядковий №:\tПрізвище:\tМова:\tК-сть збірок:\tДата виступу:\tМісце:\t\tК-сть слухачів:\tДовжина прізвища:");
		for(var i : performanceList) {
			System.out.println(++id + "\t" + i.toString());
		}
	}
	public short getTotalListeners() {
		short result = 0;
		for(var i : performanceList) {
			result += i.getListeners();
		}
		return result;
	}
	public LocalDate getMaxListenersDate() {
		if (performanceList == null || performanceList.isEmpty())
			return null;
		
		int MaxID = 0;
		for(int i = 1; i < performanceList.size(); ++i) {
			if (performanceList.get(i).getListeners() > performanceList.get(MaxID).getListeners()) {
				MaxID = i;
			}
		}
		return performanceList.get(MaxID).getDate();
	}
}
