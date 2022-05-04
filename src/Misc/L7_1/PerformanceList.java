package Misc.L7_1;

import Misc.L7_1.Poet.Language;
import MyUtils.MyList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class PerformanceList extends MyList<Performance> {
	protected Performance readInfo(String str) throws Exception {
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

	public void WriteToFile() throws IOException {
		if (buffWriter == null)
			buffWriter = new BufferedWriter(new FileWriter(FileName));
		for(var i : list) {
			buffWriter.write(i.getSurname() + " " + i.getLanguageString() + " " + i.getCount() + " " + i.getDate().toString() + " " + i.getLocation() + " " + i.getListeners() + "\n");
		}
		buffWriter.flush();
	}
	
	public PerformanceList(String newFileName) throws Exception {
		super(newFileName);
	}
	
	public byte remove(LocalDate Date, byte All) {
		byte PrevSize = (byte) list.size();
		if (Date == null) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.getDate().equals(Date)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.getDate().equals(Date)) {
				list.remove(i);	
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
		byte PrevSize = (byte) list.size();
		
		if (SurnameOrLocation == null) return 0;
		
		if (All > 0) {
			if (flag > 0) list.removeIf(n -> (n.getSurname().equals(SurnameOrLocation)));
			else list.removeIf(n -> (n.getLocation().equals(SurnameOrLocation)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (flag > 0 && i.getSurname().equals(SurnameOrLocation)) {
				list.remove(i);
				try {
					WriteToFile();
				} catch (IOException e) {
					System.out.println("Не вдалось вилучити запис з файлу;");
				}
				return 1;
			}
			else if (flag <= 0 && i.getLocation().equals(SurnameOrLocation)) {
				list.remove(i);
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
		byte PrevSize = (byte) list.size();
		if (Listeners < 1) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.getListeners() == Listeners));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.getListeners() == Listeners) {
				list.remove(i);	
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
		byte PrevSize = (byte) list.size();
		if (Count < 1) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.getCount() == Count));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.getCount() == Count) {
				list.remove(i);	
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
		byte PrevSize = (byte) list.size();
		if (lang == null) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.getLanguage().equals(lang)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.getLanguage().equals(lang)) {
				list.remove(i);	
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

	// if NameOrExtension >= 0, edits Performance Name, else edits Performance Extension;
	public boolean edit(int id, String newSurnameOrLocation, byte SurnameOrLocation) {
		if (id < 0 || id >= list.size())
			return false;
		try {
			if (SurnameOrLocation >= 0)
				list.get(id).setSurname(newSurnameOrLocation);
			else 
				list.get(id).setLocation(newSurnameOrLocation);
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
		if (id < 0 || id >= list.size())
			return false;
		try {
			list.get(id).setListeners(newListeners);
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
		if (id < 0 || id >= list.size())
			return false;
		try {
			list.get(id).setCount(newCount);
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
		if (id < 0 || id >= list.size())
			return false;
		try {
			list.get(id).setDate(newDate);
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
		if (id < 0 || id >= list.size())
			return false;
		try {
			list.get(id).setLanguage(newLanguage);
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
		for(var i : list) {
			System.out.println(++id + "\t" + i.toString());
		}
	}
	public short getTotalListeners() {
		short result = 0;
		for(var i : list) {
			result += i.getListeners();
		}
		return result;
	}
	public LocalDate getMaxListenersDate() {
		if (list == null || list.isEmpty())
			return null;
		
		int MaxID = 0;
		for(int i = 1; i < list.size(); ++i) {
			if (list.get(i).getListeners() > list.get(MaxID).getListeners()) {
				MaxID = i;
			}
		}
		return list.get(MaxID).getDate();
	}
}
