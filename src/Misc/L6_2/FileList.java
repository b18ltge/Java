package Misc.L6_2;

import java.util.Collections;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class FileList {
	private BufferedReader buffReader;
	private BufferedWriter buffWriter;
	private String FileName;
	private LinkedList<File> fileList;
	
	private File readFileInfo(String str) throws Exception {
		if(str == null)
			return null;
		
		String[] words = new String[5];
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
		
		boolean R = words[4].charAt(0) == 'R';
		boolean W = words[4].charAt(1) == 'W';
		boolean X = words[4].charAt(2) == 'X';
		FileAttribute FileAtt = new FileAttribute(R,W,X);
		return new File(words[0], words[1], Long.parseLong(words[2]), LocalDate.parse(words[3]), FileAtt);
	}
	private void ReadFromFile() throws Exception {
		if (!fileList.isEmpty())
			fileList.clear();
		
		String str;
		while((str = buffReader.readLine()) != null) 
		{  
			File file = readFileInfo(str);
			if (file != null)
			fileList.add(file);	// порядкове читання текстового файлу
		}
	}
	public void WriteToFile() throws IOException {
		buffWriter = new BufferedWriter(new FileWriter(FileName));
		for(var i : fileList) {
			buffWriter.write(i.getName() + " " + i.getExtension() + " " + i.getSize() + " " + i.getDate().toString() + " " + i.getAttributes().toString() + "\n");
		}
		buffWriter.close();
	}
	
	public FileList(String newFileName) throws Exception {
		if (newFileName == null)
			throw new Exception("Invalid File Path;\n");
		buffReader = new BufferedReader(new FileReader(newFileName));
		fileList = new LinkedList<File>();
		FileName = newFileName;
		ReadFromFile();
	}
	protected void finalize() throws Throwable
	{
		buffReader.close();
		buffWriter.close();
	}
	public int size() {
		return fileList.size();
	}
	public void add(File newFile) throws IOException {
		if (newFile == null)
			return;
		fileList.add(newFile);
	}
	public void sort() {
		Collections.sort(fileList);
	}
	public File at(byte id) {
		if (id < 0 || id >= fileList.size())
			return null;
		byte it = 0;
		for(var file : fileList) {
			if (it++ == id)
				return file.clone();
		}
		return null;
	}
	
	// flag > 0 - name | flag <= 0 - extension
	// All > 0 - all records | All <= 0 - only first
	public byte remove(String NameOrExtension, byte flag, byte All) {
		byte PrevSize = (byte) fileList.size();
		
		if (NameOrExtension == null) return 0;
		
		if (All > 0) {
			if (flag > 0) fileList.removeIf(n -> (n.getName().equals(NameOrExtension)));
			else fileList.removeIf(n -> (n.getExtension().equals(NameOrExtension)));
			return (byte) (PrevSize - fileList.size());
		}

		for(var i : fileList) {
			if (flag > 0 && i.getName().equals(NameOrExtension)) {
				fileList.remove(i);	
				return 1;
			}
			else if (flag <= 0 && i.getExtension().equals(NameOrExtension)) {
				fileList.remove(i);
				return 1;
			}
		}
		
		return 0;
	}
	public byte remove(String StirngSize, byte All) {
		byte PrevSize = (byte) fileList.size();
		if (StirngSize == null) return 0;
		
		if (All > 0) {
			fileList.removeIf(n -> (n.SizeToString().equals(StirngSize)));
			return (byte) (PrevSize - fileList.size());
		}

		for(var i : fileList) {
			if (i.SizeToString().equals(StirngSize)) {
				fileList.remove(i);	
				return 1;
			}
		}
		
		return 0;
	}
	public byte remove(LocalDate Date, byte All) {
		byte PrevSize = (byte) fileList.size();
		if (Date == null) return 0;
		if (Date.isAfter(LocalDate.now()) || Date.isBefore(LocalDate.of(1960, 1, 1))) return 0;
		
		if (All > 0) {
			fileList.removeIf(n -> (n.getDate().equals(Date)));
			return (byte) (PrevSize - fileList.size());
		}

		for(var i : fileList) {
			if (i.getDate().equals(Date)) {
				fileList.remove(i);	
				return 1;
			}
		}
		
		return 0;
	}
	public byte remove(FileAttribute FileAtt, byte All) {
		byte PrevSize = (byte) fileList.size();
		if (FileAtt == null) return 0;
		
		if (All > 0) {
			fileList.removeIf(n -> (n.getAttributes().equals(FileAtt)));
			return (byte) (PrevSize - fileList.size());
		}

		for(var i : fileList) {
			if (i.getAttributes().equals(FileAtt)) {
				fileList.remove(i);	
				return 1;
			}
		}
		
		return 0;
	}
	public void remove(byte id) {
		fileList.remove(id);
	}
	
	public LinkedList<File> search(LocalDate key) {
		LinkedList<File> result = new LinkedList<File>();
		for(var i : fileList) {
			if (i.getDate().equals(key))
				result.add(i.clone());
		}
		return result;
	}
	// if NameOrExtension >= 0, edits File Name, else edits File Extension;
	public boolean edit(int id, String newNameOrExtension, byte NameOrExtension) {
		if (id < 0 || id >= fileList.size())
			return false;
		try {
			if (NameOrExtension >= 0)
				fileList.get(id).setName(newNameOrExtension);
			else 
				fileList.get(id).setExtension(newNameOrExtension);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		return true;
	}
	public boolean edit(int id, long newSize) {
		if (id < 0 || id >= fileList.size())
			return false;
		try {
			fileList.get(id).setSize(newSize);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		return true;
	}
	public boolean edit(int id, LocalDate newDate) {
		if (id < 0 || id >= fileList.size())
			return false;
		try {
			fileList.get(id).setDate(newDate);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false; 
		}
		return true;
	}
	public boolean edit(int id, FileAttribute newAttribute) {
		if (id < 0 || id >= fileList.size())
			return false;
		try {
			fileList.get(id).setAttributes(newAttribute);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		return true;
	}
	public void print() {
		byte id = 0;
		System.out.println("Id:\tFile Name:\tFile Size:\tCreated:\tAttributes:");
		for(var i : fileList) {
			System.out.println(++id + "\t" + i.toString());
		}
	}
}
