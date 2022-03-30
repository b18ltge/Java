package MyUtils;

public class SomeUtils {
	// sets 2 spaces between array elements;
	public static <MyType> String ArrayToString(MyType[] MyArray) {
		String result = "";
		for(var i : MyArray) {
			result += i.toString() + "  ";
		}
		return result;
	}
}
