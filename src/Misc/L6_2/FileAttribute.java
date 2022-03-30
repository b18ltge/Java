package Misc.L6_2;

public class FileAttribute {
	private boolean Attributes[] = new boolean[3];
	
	public FileAttribute(boolean R, boolean W, boolean X) {
		setAttributes(R,W,X);
	}
	public FileAttribute(boolean att[]) throws Exception {
		setAttributes(att);
	}

	public void setAttributes(boolean R, boolean W, boolean X) {
		Attributes[0] = R;
		Attributes[1] = W;
		Attributes[2] = X;
	}
	public void setAttributes(boolean att[]) throws Exception {
		if (att == null || att.length != 3)
			throw new Exception("Incorrect File Attribute");
		Attributes[0] = att[0];
		Attributes[1] = att[1];
		Attributes[2] = att[2];
	}
	public boolean[] getAttributes() {
		return Attributes.clone();
	}
	
	public String toString() {
		return (Attributes[0] ? "R" : "-") + (Attributes[1] ? "W" : "-") + (Attributes[2] ? "X" : "-");
	}
	public FileAttribute clone() {
		try {
			return new FileAttribute(Attributes);
		} catch (Exception e) {
			return null;
		}
	}
	public boolean equals(FileAttribute other) {
		return Attributes[0] == other.Attributes[0] && Attributes[1] == other.Attributes[1] && Attributes[2] == other.Attributes[2];
	}
	public static FileAttribute parse(String str) {
		if (str == null || str.length() != 3)
			return null;
		if (str.charAt(0) != 'R' && str.charAt(0) != '-')
			return null;
		if (str.charAt(1) != 'W' && str.charAt(1) != '-')
			return null;
		if (str.charAt(2) != 'X' && str.charAt(2) != '-')
			return null;
		
		boolean Att[] = new boolean[3];
		Att[0] = str.charAt(0) == 'R';
		Att[1] = str.charAt(1) == 'W';
		Att[2] = str.charAt(2) == 'X';
		
		try {
			return new FileAttribute(Att);
		} catch (Exception e) {
			return null;
		}
	}
}
