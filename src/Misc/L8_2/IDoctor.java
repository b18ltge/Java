package Misc.L8_2;

public interface IDoctor {
	public enum Type {Allergist, Cardiologist, Dentist, Immunologist, Neurologist, Surgeon};
	
	public abstract String getName();
	public abstract Type getType();
	
	public abstract boolean setName(String newName);
	public abstract boolean setType(Type newType);
}
