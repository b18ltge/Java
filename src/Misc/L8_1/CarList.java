package Misc.L8_1;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import Misc.L8_1.IVehicle.Brand;
import Misc.L8_1.IVehicle.Color;
import MyUtils.MyList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CarList extends MyList<Car>{
	public enum Fields {Brand, Color, Number, Year, OwnerInfo}
	
	@Override
	protected Car readInfo(String str) throws Exception {
		if(str == null)
			return null;
		
		String[] words = new String[5];
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
		
		Brand brand = Brand.valueOf(words[0]);
		Color color = Color.valueOf(words[1]);
		
		return new Car(brand,color, Integer.parseInt(words[2]), Short.parseShort(words[3]), words[4]);
	}
	
	public void WriteToFile() throws IOException {
		if (buffWriter == null)
			buffWriter = new BufferedWriter(new FileWriter(FileName));
		for(var i : list) {
			buffWriter.write(i.getBrand().toString() + " " + i.getColor().toString() + " " + i.getNumber() + " " + i.getYear() + " " + i.getOwnerInfo() + "\n");
		}
		buffWriter.flush();
	}
	
	public CarList(String newFileName) throws Exception {
		super(newFileName);
	}
	
	public byte remove(Brand brand, byte All) {
		byte PrevSize = (byte) list.size();
		if (brand == null) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.getBrand().equals(brand)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.getBrand().equals(brand)) {
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
	public byte remove(Color color, byte All) {
		byte PrevSize = (byte) list.size();
		if (color == null) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.getColor().equals(color)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.getColor().equals(color)) {
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
	public byte remove(String ownerInfo, byte All) {
		byte PrevSize = (byte) list.size();
		if (ownerInfo == null) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.getOwnerInfo().equals(ownerInfo)));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.getOwnerInfo().equals(ownerInfo)) {
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
	public byte remove(int number, byte All) {
		byte PrevSize = (byte) list.size();
		if (number < 0) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.getNumber() == number));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.getNumber() == number) {
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
	public byte remove(short year, byte All) {
		byte PrevSize = (byte) list.size();
		if (year < 0) return 0;
		
		if (All > 0) {
			list.removeIf(n -> (n.getYear() == year));
			try {
				WriteToFile();
			} catch (IOException e) {
				System.out.println("Не вдалось вилучити запис з файлу;");
			}
			return (byte) (PrevSize - list.size());
		}

		for(var i : list) {
			if (i.getYear() == year) {
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
	
	public boolean edit(int id, Brand newBrand) {
		if (id < 0 || id >= list.size())
			return false;
		if (list.get(id).setBrand(newBrand) == false)
			return false;
		
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось змінити запис в файлі;");
			return false;
		}
		return true;
	}
	public boolean edit(int id, Color newColor) {
		if (id < 0 || id >= list.size())
			return false;
		if (list.get(id).setColor(newColor) == false)
			return false;
		
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось змінити запис в файлі;");
			return false;
		}
		return true;
	}
	public boolean edit(int id, int newNumber) {
		if (id < 0 || id >= list.size())
			return false;
		if (list.get(id).setNumber(newNumber) == false)
			return false;
		
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось змінити запис в файлі;");
			return false;
		}
		return true;
	}
	public boolean edit(int id, short newYear) {
		if (id < 0 || id >= list.size())
			return false;
		if (list.get(id).setYear(newYear) == false)
			return false;
		
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось змінити запис в файлі;");
			return false;
		}
		return true;
	}
	public boolean edit(int id, String newOwnerInfo) {
		if (id < 0 || id >= list.size())
			return false;
		if (list.get(id).setOwnerInfo(newOwnerInfo) == false)
			return false;
		
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось змінити запис в файлі;");
			return false;
		}
		return true;
	}
	
	public void sort(Fields field, byte order) {
		switch (field) {
		case Brand: {
			Collections.sort(list, new Comparator<Car>() {
				  @Override
				  public int compare(Car first, Car second) {
					  int result = (order == 1) ? first.getBrand().toString().compareTo(second.getBrand().toString())
							  : second.getBrand().toString().compareTo(first.getBrand().toString());
					  return result;
				 }
			});
			break;
		}
		case Color: {
			Collections.sort(list, new Comparator<Car>() {
				@Override
				  public int compare(Car first, Car second) {
				    return (order == 1) ? first.getColor().toString().compareTo(second.getColor().toString())
				    		: second.getColor().toString().compareTo(first.getColor().toString());
				 }
			});
			break;
		}
		case Number: {
			Collections.sort(list, new Comparator<Car>() {
				@Override
				  public int compare(Car first, Car second) {
				    return (order == 1) ? first.getNumber() - second.getNumber()
				    		: second.getNumber() - first.getNumber();
				 }
			});
			break;
		}
		case Year: {
			Collections.sort(list, new Comparator<Car>() {
				@Override
				  public int compare(Car first, Car second) {
				    return (order == 1) ? first.getYear() - second.getYear()
				    		: second.getYear() - first.getYear();
				 }
			});
			break;
		}
		case OwnerInfo: {
			Collections.sort(list, new Comparator<Car>() {
				@Override
				  public int compare(Car first, Car second) {
				    return (order == 1) ? first.getOwnerInfo().compareTo(second.getOwnerInfo())
				    		: second.getOwnerInfo().compareTo(first.getOwnerInfo());
				 }
			});
			break;
		}
		default: {
			System.out.print("Не вдалось відсортувати список;");
			return;
		}
		}
	}
	
	public void print() {
		byte id = 0;
		System.out.println("Порядковий №:\tМарка:\t\tКолір:\tНомер:\t\tРік випуску:\tІнформація про власника:");
		for(var i : list) {
			String brandStr = i.getBrand().toString();
			String str = (brandStr.length() > 8) ? "\t" : "\t\t";
			System.out.println(++id + "\t\t" + brandStr + str + i.getColor().toString() 
					+ "\t" + i.getNumber() + "\t\t" + i.getYear() + "\t\t" + i.getOwnerInfo());
		}
		System.out.println();
	}

	public void printInfo() {
		var MyMap = new HashMap<Brand, HashSet<Color>>(); 
		
		for(var car : list) {
			Brand carBrand = car.getBrand();
			Color carColor = car.getColor();
			HashSet<Color> colors = MyMap.get(carBrand);
			if (colors == null) {
				colors = new HashSet<Color>();
				colors.add(carColor);
			}
			else if (!colors.contains(carColor)) {
				colors.add(carColor);
			}
			MyMap.put(carBrand, colors);
		}
		
		var MySet = MyMap.entrySet();
		for(var i : MySet) {
			String keyString = i.getKey().toString();
			String str = (keyString.length() > 8) ? "\t" : "\t\t";
			System.out.println(keyString + ":" + str + i.getValue().size());
		}
	}
}
