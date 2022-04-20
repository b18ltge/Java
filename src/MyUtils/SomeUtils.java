package MyUtils;

import java.time.LocalDate;

public class SomeUtils {
	// sets 2 spaces between array elements;
	public static <MyType> String ArrayToString(MyType[] MyArray) {
		String result = "";
		for(var i : MyArray) {
			result += i.toString() + "  ";
		}
		return result;
	}
	
	public static byte enterByte(final String message, int minValue, int maxValue) {
		return (byte)enterLong(message,minValue,maxValue);
	}
	
	public static short enterShort(final String message, int minValue, int maxValue) {
		return (short)enterLong(message,minValue,maxValue);
	}
	
	public static int enterInt(final String message, int minValue, int maxValue) {
		return (int)enterLong(message,minValue,maxValue);
	}
	
	public static long enterLong(final String message, long minValue, long maxValue) {
		if (minValue > maxValue) {
			long tmp = minValue;
			minValue = maxValue;
			maxValue = tmp;
		}	
		
		long result;
		while(true) {
			System.out.print(message);
			try {
				result = MyScanner.in.nextLong();
				if (result >= minValue && result <= maxValue)
					return result;
			}
			catch(Exception ex) {
				MyScanner.in.skip(".*");
				continue;
			}
		}
	}
	
	public static float enterFloat(final String message, float minValue, float maxValue) {
		return (float)enterDouble(message,minValue,maxValue);
	}

	public static double enterDouble(final String message, double minValue, double maxValue) {
		if (minValue > maxValue) {
			double tmp = minValue;
			minValue = maxValue;
			maxValue = tmp;
		}	
		
		double result;
		while(true) {
			System.out.print(message);
			try {
				result = MyScanner.in.nextDouble();
				if (result >= minValue && result <= maxValue)
					return result;
			}
			catch(Exception ex) {
				MyScanner.in.skip(".*");
				continue;
			}
		}
	}
	
	public static String enterString(final String message, final String regex) {
		String result;
		while(true) {
			System.out.print(message);
			try {
				result = MyConsoleBufferedReader.buffReader.readLine();
				if (result.matches(regex))
					return result;
			}
			catch(Exception ex) {
				MyScanner.in.skip(".*");
				continue;
			}
		}
	}

	public static LocalDate enterDate(final String message, LocalDate minValue, LocalDate maxValue) {
		if (minValue.isAfter(maxValue)) {
			LocalDate tmp = minValue;
			minValue = maxValue;
			maxValue = tmp;
		}	
		
		LocalDate result;
		while(true) {
			System.out.print(message);
			try {
				result = LocalDate.parse(MyConsoleBufferedReader.buffReader.readLine());
				if (result.isAfter(minValue.minusDays(1)) && result.isBefore(maxValue.plusDays(1)))
					return result;
			}
			catch(Exception ex) {
				MyScanner.in.skip(".*");
				continue;
			}
		}
	}
}
