package Misc.L7_1;

public abstract class Poet {
	public enum Language{EN, DE, FR, UKR};
	public String getLanguageString() {
		switch(language) {
		case EN:
			return "EN";
		case DE:
			return "DE";
		case FR:
			return "FR";
		case UKR:
			return "UKR";
		default: return "";
		}
	}
	
	protected String surname;
	protected Language language;
	protected byte count;
	
	protected Poet(String newSurname, Language newLanguage, byte newCount) throws Exception {
		if (setSurname(newSurname) == false)
			throw new Exception("Некоректно задано прізвище поета. Прізвище може містити тільки символи латинського алфавіту;");
		if (setLanguage(newLanguage) == false)
			throw new Exception("Некореткно задано мову поета;");
		if (setCount(newCount) == false)
			throw new Exception("Некореткно кількість збірок поета; Кількість має бути більшою за 0;");
	}
	
	public abstract boolean setSurname(String newSurname);
	public abstract boolean setLanguage(Language newLanguage);
	public abstract boolean setCount(byte newCount);
	
	public abstract String getSurname();
	public abstract Language getLanguage();
	public abstract byte getCount();

	public abstract int getSurnameLength();
	
	public static Language getLanguageFromInt(int id) {
		switch(id) {
			case 1: return Language.EN;
			case 2: return Language.DE;
			case 3: return Language.FR;
			case 4: return Language.UKR;
			default: return null;
		}
	}
}
