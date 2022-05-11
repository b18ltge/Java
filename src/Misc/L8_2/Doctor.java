package Misc.L8_2;

public abstract class Doctor implements IDoctor{
	public static final String NAME_REGEX = "[a-zA-Z]{3,} [a-zA-Z]{3,} [a-zA-Z]{3,}";
	
	protected String name;
	protected Type type;
}
