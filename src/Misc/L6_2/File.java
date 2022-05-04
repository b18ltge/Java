package Misc.L6_2;

import java.time.LocalDate;

public class File implements Comparable<File>{
	private String name;
	private String extension;
	private long size;	//bytes
	private LocalDate date;
	private FileAttribute attribute;
	
	public String SizeToString() {
		if (size < 1024)	// < 1KB
			return size + "B";
		else {
			String str;
			String app;
			if (size < 1024 * 1024) {	// < 1MB
				str = (size / 1024.0f) + "";
				app = "KB";
			}
			else if (size < 1024 * 1024 * 1024) {	// < 1GB
				str = (size / 1024.0f / 1024.0f) + "";
				app = "MB";
			}
			else {	// > 1GB
				str = (size / 1024.0f / 1024.0f / 1024.0f) + "";
				app = "GB";
			}
			return str.substring(0, str.indexOf(".") + 3) + app;
		}
	}
	
	public File(String newName, String newExtension, long newSize, LocalDate date, FileAttribute newAttribute) throws Exception {
		setName(newName);
		setExtension(newExtension);
		setSize(newSize);
		setDate(date);
		setAttributes(newAttribute);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String newName) throws Exception {
		if (newName == null || !newName.matches("[a-zA-Z0-9_]+"))
			throw new Exception("Некоректне ім'я файлу;\n");
		name = newName;
	}

	public String getExtension() {
		return extension;
	}
	public void setExtension(String newExtension) throws Exception {
		if (newExtension == null || !newExtension.matches("[a-z]+"))
			throw new Exception("Некоректне розширення файлу;\n");
		extension = newExtension;
	}
	
	public long getSize() {
		return size;
	}
	public void setSize(long newSize) throws Exception {
		if (newSize < 0)
			throw new Exception("Некоректний розмір файлу;\n");
		size = newSize;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate newDate) throws Exception {
		if (newDate.isAfter(LocalDate.now()) || newDate.isBefore(LocalDate.of(1960,1,1)))
			throw new Exception("Некоректна дата створення файлу;\n");
		date = newDate;
	}

	public FileAttribute getAttributes() {
		return attribute.clone();
	}
	public void setAttributes(FileAttribute newAttribute) {
		attribute = newAttribute.clone();
	}

	
	public int compareTo(File other) {
		int result = extension.compareTo(other.extension);
		if (result == 0) {
			result = name.compareTo(other.name);
			if (result == 0) {
				result = date.compareTo(other.date);
				if (result == 0) {
					result = (size == other.size) ? 0 : (size < other.size) ? -1 : 1;
				}
			}
		}
		return result;
	}
	public boolean equals(File other) {
		return date.equals(other.date);
	}
	
	public String toString() {
		String SpaceAfterExtension;
		String SpaceAfterSize;
		String FileSize = SizeToString();
		SpaceAfterSize = (FileSize.length() > 7) ? "\t" : "\t\t";
		SpaceAfterExtension = (name.length() + extension.length() < 7) ? "\t\t" : "\t";
		
		return name + "." + extension + SpaceAfterExtension + FileSize + SpaceAfterSize
				+ date.toString() + "\t" + attribute.toString();
	}
	public File clone() {
		try {
			return new File(name,extension,size,date,attribute);
		} catch (Exception e) {
			return null;
		}
	}
}
