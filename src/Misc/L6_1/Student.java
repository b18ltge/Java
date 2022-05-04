package Misc.L6_1;

public class Student implements Comparable<Student>{
	private String name;
	private String surname;
	private short number;
	private byte Marks[];
	
	public Student(String newName, String newSurname, short newNumber, byte newMarks[]) throws IllegalArgumentException{
		this.setName(newName);
		this.setSurname(newSurname);
		this.setNumber(newNumber);
		this.setMarks(newMarks);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String newName) throws IllegalArgumentException{
		if(!newName.matches("[a-zA-Z]+"))	//regex :)
			throw new IllegalArgumentException("Некоректне ім'я студента!\n");
		name = newName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String newSurname) throws IllegalArgumentException{
		if(!newSurname.matches("[a-zA-Z]+"))	//regex :)
			throw new IllegalArgumentException("Некоректне прізвище студента!\n");
		surname = newSurname;
	}
	public short getNumber() {
		return number;
	}
	public void setNumber(short newNumber) throws IllegalArgumentException{
		if (newNumber < 0)
			throw new IllegalArgumentException("Некоректний № залікової книжки студента!\n");
		number = newNumber;
	}
	public byte[] getMarks() {
		return Marks.clone();
	}
	public void setMarks(byte newMarks[]) throws IllegalArgumentException {
		if (newMarks == null)
			throw new IllegalArgumentException("Некоректна інформація про оцінки студента!\n");
		if (newMarks.length != 5)
			throw new IllegalArgumentException("Некоректна інформація про оцінки студента!\n");
		for(byte i : newMarks) {
			if (i < 0 || i > 100)
				throw new IllegalArgumentException("Некоректна інформація про оцінки студента!\n");
		}
		
		Marks = newMarks.clone();
	}
	public float getAverage() {
		float result = 0;
		for(byte i : Marks) {
			result += i;
		}
		return result / Marks.length;
	}
	// 
	public boolean hasLowMarks() {
		for(byte i : Marks) {
			if (i < 20)
				return true;
		}
		return false;
	}
	//
	
	public void printInfo() {
		System.out.println(name + "\t\t" + surname + "\t\t" + number + "\t\t" + Marks[0] + 
				"\t\t" + Marks[1] + "\t\t" + Marks[2] + "\t\t" + Marks[3] + "\t\t" + Marks[4] + "\t\t" + getAverage());
	}
	
	// operator>(Student, Student); 
	public int compareTo(Student other) {
		// if first's average mark > second's average mark returns true;
		// else returns false;
		int sum1 = 0;
		int sum2 = 0;
		byte OtherMarks[] = other.getMarks();
		
		for(byte i = 0; i < Marks.length; ++i) {
			sum1 +=  Marks[i];
			sum2 +=  OtherMarks[i];
		}
		
		return sum1 - sum2;
	}
}
