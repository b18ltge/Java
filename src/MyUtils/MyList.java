package MyUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public abstract class MyList<MyType> {
	protected BufferedReader buffReader;
	protected BufferedWriter buffWriter;
	protected String FileName;
	protected LinkedList<MyType> list;
	
	// abstract methods:
	protected abstract MyType readInfo(String str) throws Exception;
	public abstract void WriteToFile() throws IOException;
	public abstract void print();
	
	protected void ReadFromFile() throws Exception {
		if (!list.isEmpty())
			list.clear();
		
		String str;
		while((str = buffReader.readLine()) != null) 
		{  
			MyType obj = readInfo(str);
			if (obj != null)
			list.add(obj);	// порядкове читання текстового файлу
		}
	}
	protected MyList(String newFileName) throws Exception {
		if (newFileName == null)
			throw new Exception("Invalid File Name;\n");
		buffReader = new BufferedReader(new FileReader(newFileName));
		list = new LinkedList<MyType>();
		FileName = newFileName;
		ReadFromFile();
	}
	protected void finalize() throws Throwable
	{
		buffReader.close();
		buffWriter.close();
	}
	public int size() {
		return list.size();
	}
	public void add(MyType newObj) throws IOException {
		if (newObj == null)
			return;
		list.add(newObj);
		WriteToFile();
	}
	public MyType at(int id) {
		if (id < 0 || id >= list.size())
			return null;
		byte it = 0;
		for(var obj : list) {
			if (it++ == id)
				return obj;
		}
		return null;
	}
	public void remove(int id) {
		list.remove(id);
		try {
			WriteToFile();
		} catch (IOException e) {
			System.out.println("Не вдалось вилучити запис з файлу;");
		}
	}
}
