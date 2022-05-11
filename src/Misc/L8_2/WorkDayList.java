package Misc.L8_2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import Misc.L8_2.IDoctor.Type;
import MyUtils.MyList;

public class WorkDayList extends MyList<WorkDay>{
	@Override
	protected WorkDay readInfo(String str) throws Exception {
		if(str == null)
			return null;
		
		String[] words = new String[7];
		byte wordsPos = 0;
		
		for(int i = 0, startPos = 0; i < str.length(); ++i) {
			if(str.charAt(i) == ' ') {
				words[wordsPos] = str.substring(startPos, i);
				startPos = i + 1;
				if (++wordsPos == words.length - 1) {
					words[wordsPos] = str.substring(startPos, str.length());
					break;
				}
			}
		}
		
		Doctor.Type type = Doctor.Type.valueOf(words[3]);
		
		return new WorkDay(words[0] + " " + words[1] + " " + words[2], type, 
				LocalDate.parse(words[4]), Byte.parseByte(words[5]), 
				LocalTime.parse(words[6]));
	}
	
	public WorkDayList(String newFileName) throws Exception {
		super(newFileName);
	}

	@Override
	public void WriteToFile() throws IOException {
		if (buffWriter == null)
			buffWriter = new BufferedWriter(new FileWriter(FileName));
		for(var i : list) {
			buffWriter.write(i.name + " " + i.type.toString() + " " + i.date.toString() + " " + i.count + " " + i.time.toString() + "\n");
		}
		buffWriter.flush();
	}

	@Override
	public void print() {
		byte id = 0;
		System.out.println("Порядковий №:\tПІБ Лікаря:\t\t\tСпеціальність:\tДата робочого дня:\tПацієнти:\tЧас початку роботи:");
		for(var i : list) {
			String str = "\t";
			str = str.repeat(4 - Math.min(i.name.length(),32) / 8);
			String str2 = (i.type.toString().length() > 8) ? "\t" : "\t\t";
			System.out.println(++id + "\t\t" + i.name + str + i.type.toString() + str2 + i.date + "\t\t" + i.count + "\t\t" + i.time);
		}
		System.out.println();
	}

	public byte remove(String name, byte All) {
		byte PrevSize = (byte) list.size();
		if (name == null) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.name.equals(name)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.name.equals(name)) {
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
	public byte remove(Type type, byte All) {
		byte PrevSize = (byte) list.size();
		if (type == null) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.type.equals(type)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.type.equals(type)) {
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
	public byte remove(LocalDate date, byte All) {
		byte PrevSize = (byte) list.size();
		if (date == null) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.date.equals(date)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.date.equals(date)) {
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

	public byte remove(byte count, byte All) {
		byte PrevSize = (byte) list.size();
		if (count < 0) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.count == count));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.count == count) {
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
	public byte remove(LocalTime time, byte All) {
		byte PrevSize = (byte) list.size();
		if (time == null) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.time.equals(time)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.time.equals(time)) {
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

	public boolean edit(int id, String newName) {
		if (id < 0 || id >= list.size())
			return false;
		if (list.get(id).setName(newName) == false)
			return false;
		
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось змінити запис в файлі;");
			return false;
		}
		return true;
	}
	public boolean edit(int id, Type newType) {
		if (id < 0 || id >= list.size())
			return false;
		if (list.get(id).setType(newType) == false)
			return false;
		
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось змінити запис в файлі;");
			return false;
		}
		return true;
	}
	public boolean edit(int id, LocalDate newDate) {
		if (id < 0 || id >= list.size())
			return false;
		if (list.get(id).setDate(newDate) == false)
			return false;
		
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось змінити запис в файлі;");
			return false;
		}
		return true;
	}
	public boolean edit(int id, byte newCount) {
		if (id < 0 || id >= list.size())
			return false;
		if (list.get(id).setCount(newCount) == false)
			return false;
		
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось змінити запис в файлі;");
			return false;
		}
		return true;
	}
	public boolean edit(int id, LocalTime newTime) {
		if (id < 0 || id >= list.size())
			return false;
		if (list.get(id).setTime(newTime) == false)
			return false;
		
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось змінити запис в файлі;");
			return false;
		}
		return true;
	}

	// середня к-сть пацієнтів в день за період
	public byte getAverageCount(LocalDate startDate, LocalDate endDate) {
		endDate = endDate.plusDays(1);
		int count = 0;
		int sum = 0;
		for(final var i : list) {
			if (i.date.isAfter(endDate) || i.date.isBefore(startDate))
				continue;
			sum += i.count;
			count++;
		}
		
		return (byte) Math.round(1.0f * sum / Math.max(count,1));
	}
	
	// список днів, робочий час яких почався після вказаного часу
	public LinkedList<LocalDate> getDays(LocalTime time) {
		if (time == null)
			return null;
		
		var result = new LinkedList<LocalDate>();
		
		for(int i = 0; i < list.size(); ++i) {
			if (list.get(i).time.isAfter(time))
				result.add(list.get(i).date);
		}
		
		return result;
	}
	
	// к-сть днів з максимальним навантаженням (макс. к-сть пацієнтів)
	// дні, де сумарна к-сть пацієнтів максимальна
	// soon...
	public int getMaxCount() {
		byte maxCount = 0;
		var DaysSet = new HashSet<LocalDate>();
		var MyMap = new HashMap<LocalDate, Byte>(); 
		
		for(var workDay : list) {
			LocalDate date = workDay.date;
			Byte count = workDay.count;
			if (!MyMap.containsKey(date)) {
				MyMap.put(date, count);
			}
			else MyMap.put(date, (byte) (MyMap.get(date) + count));
		}
		
		var MySet = MyMap.entrySet();
		for(var i : MySet) {
			if (i.getValue() > maxCount) {
				maxCount = i.getValue();
				DaysSet.clear();
				DaysSet.add(i.getKey());
			}
			else if (i.getValue() == maxCount) {
				DaysSet.add(i.getKey());
			}
			
			System.out.println("Date: " + i.getKey() + "\tTotal Count: " + i.getValue());
		}
		
		return DaysSet.size();
	}
}
