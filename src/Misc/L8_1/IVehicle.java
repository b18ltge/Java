package Misc.L8_1;

public interface IVehicle {
	public enum Brand {Toyota, Honda, Chevrolet, Ford, MercedesBenz, Jeep, BMW, Porsche, Subaru, Nissan}
	public enum Color {Red, Green, Blue, Yellow, White, Black, Gray, Purple, Orange, Aqua}
	
	public abstract Brand getBrand();
	public abstract Color getColor();
	public abstract int getNumber();
	public abstract short getYear();
	public abstract String getOwnerInfo();
	
	public abstract boolean setBrand(Brand newBrand);
	public abstract boolean setColor(Color newColor);
	public abstract boolean setNumber(int newNumber);
	public abstract boolean setYear(short newYear);
	public abstract boolean setOwnerInfo(String newOwnerInfo);
}
