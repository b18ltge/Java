/**
 * 
 */
package MyUtils;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import Misc.L6_1.Student;
import Misc.L7_1.Performance;
import Misc.L7_1.Poet.Language;
import MyLabs.SolutionP1;

public class JUnit {
	// припустима точність порівнянь
	public static final double EPS = 1e-6;

	@Test
	public void test() throws Exception {
		// TestP1_1();
		// TestP1_2();
		// TestP1_3();
		//TestL6();
		TestL7();
	}

	public static void TestP1_1() {
		// Test #1
		System.out.println("Test №1.1");
		{
			byte expResult = 3;
			Double[] arr = { 6.0, 5.0, 1.0, 4.0, 6.0, 4.0 };
			byte result = SolutionP1.Task1(arr);

			System.out.println("Test #1");
			System.out.println("Array: " + SomeUtils.ArrayToString(arr));
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}

		{
			// Test #2
			byte expResult = 2;
			Double[] arr = { -5.0, 0.0, 0.0, -0.445, 5.355, 4.0, 0.0 };
			byte result = SolutionP1.Task1(arr);

			System.out.println("\nTest #2");
			System.out.println("Array: " + SomeUtils.ArrayToString(arr));
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}

	}

	public static void TestP1_2() {
		System.out.println("\nTest №1.2");
		{
			// Test #1
			double expResult = 0.0;
			Double[] arr = { -5.0, 0.0, 0.0, -0.445, 5.355, 4.0, 0.0 };
			double result;
			try {
				result = SolutionP1.Task2(arr);
			} catch (Exception ex) {
				result = 0.0;
			}
			System.out.println("Test #1");
			System.out.println("Array: " + SomeUtils.ArrayToString(arr));
			System.out.println(
					"Excepted: " + (expResult == 0.0 ? "Неможливо обчислити добуток елементів масиву;" : expResult));
			System.out.println(
					"Output: " + (result == 0.0 ? "Неможливо обчислити добуток елементів масиву;" : expResult));
			assertEquals(expResult, result, EPS);
		}

		{
			// Test #2
			double expResult = -108.0;
			Double[] arr = { 4.0, 0.0, 6.0, -3.0, 6.0, 0.0, 6.0 };
			double result;
			try {
				result = SolutionP1.Task2(arr);
			} catch (Exception ex) {
				result = 0.0;
			}
			System.out.println("\nTest #2");
			System.out.println("Array: " + SomeUtils.ArrayToString(arr));
			System.out.println(
					"Excepted: " + (expResult == 0.0 ? "Неможливо обчислити добуток елементів масиву;" : expResult));
			System.out.println(
					"Output: " + (result == 0.0 ? "Неможливо обчислити добуток елементів масиву;" : expResult));
			assertEquals(expResult, result, EPS);
		}
	}

	public static void TestP1_3() {
		System.out.println("\nTest №1.3");
		{
			// Test #1
			Double[] expResult = { 82.77, -23.8, 16.45, -43.87, -46.75, 32.28, 7.86, 25.71 };
			Double[] arr = { -46.75, 32.28, 7.86, 25.71, 82.77, -23.8, 16.45, -43.87 };
			Double[] result = SolutionP1.Task3(arr);
			;

			System.out.println("Test #1");
			System.out.println("Array: " + SomeUtils.ArrayToString(arr));
			System.out.println("Excepted: " + SomeUtils.ArrayToString(expResult));
			System.out.println("Output: " + SomeUtils.ArrayToString(result));
			assertEquals(SomeUtils.ArrayToString(expResult), SomeUtils.ArrayToString(result));
		}

		{
			// Test #2
			Double[] expResult = { 4.12, -23.8, 4.44, 4.44};
			Double[] arr = { 4.44, 4.44, 4.12, -23.8 };
			Double[] result = SolutionP1.Task3(arr);
			

			System.out.println("\nTest #2");
			System.out.println("Array: " + SomeUtils.ArrayToString(arr));
			System.out.println("Excepted: " + SomeUtils.ArrayToString(expResult));
			System.out.println("Output: " + SomeUtils.ArrayToString(result));
			assertEquals(SomeUtils.ArrayToString(expResult), SomeUtils.ArrayToString(result));
		}
	}

	public static void TestL6() {
		// Test #1
		System.out.println("\nTest L6");
		{
			boolean expResult = true;
			byte marks[] = { 5, 43, 56, 42, 12 };
			Student student = new Student("TestName", "TestSurname", (short) 1000, marks);
			boolean result = student.hasLowMarks();

			System.out.println("Test #1");
			System.out.println("Marks: " + SomeUtils.ArrayToString(new Byte[] { 5, 43, 56, 42, 12 }));
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}

		{
			boolean expResult = false;
			byte marks[] = { 51, 91, 56, 42, 21 };
			Student student = new Student("TestName", "TestSurname", (short) 1000, marks);
			boolean result = !student.hasLowMarks();

			System.out.println("\nTest #2");
			System.out.println("Marks: " + SomeUtils.ArrayToString(new Byte[] { 51, 91, 56, 42, 21 }));
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
	}

	public static void TestL7() throws Exception {
		System.out.println("\n--=== Test L7 ===--");
		Performance obj = new Performance("TestSurname", Language.EN, (byte)5, LocalDate.of(2022, 12, 12), "TestLocation", (short)15);
		{
			boolean expResult = true;
			String newSurname = "NewSurname";
			boolean result = obj.setSurname(newSurname);

			System.out.println("\nTest #1");
			System.out.println("new Surname: " + newSurname);
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		{
			boolean expResult = false;
			String newSurname = "44-12-15";
			boolean result = obj.setSurname(newSurname);

			System.out.println("\nTest #2");
			System.out.println("new Surname: " + newSurname);
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		{
			boolean expResult = true;
			Language newLanguage = Language.UKR;
			boolean result = obj.setLanguage(newLanguage);

			System.out.println("\nTest #3");
			System.out.println("new Language: " + "Language.UKR");
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		{
			boolean expResult = false;
			Language newLanguage = null;
			boolean result = obj.setLanguage(newLanguage);

			System.out.println("\nTest #4");
			System.out.println("new Language: " + "null");
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		{
			boolean expResult = false;
			byte newCount = -1;
			boolean result = obj.setCount(newCount);

			System.out.println("\nTest #5");
			System.out.println("new Count: " + newCount);
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		{
			boolean expResult = true;
			byte newCount = 6;
			boolean result = obj.setCount(newCount);

			System.out.println("\nTest #6");
			System.out.println("new Count: " + newCount);
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		{
			boolean expResult = true;
			LocalDate newDate = LocalDate.of(2019,05,03);
			boolean result = obj.setDate(newDate);

			System.out.println("\nTest #7");
			System.out.println("new Date: " + newDate);
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		{
			boolean expResult = false;
			LocalDate newDate = LocalDate.of(2049,11,12);
			boolean result = obj.setDate(newDate);

			System.out.println("\nTest #8");
			System.out.println("new Date: " + newDate);
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		{
			boolean expResult = false;
			String newLocation = "11";
			boolean result = obj.setLocation(newLocation);

			System.out.println("\nTest #9");
			System.out.println("new Location: " + newLocation);
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		{
			boolean expResult = true;
			String newLocation = "a small town";
			boolean result = obj.setLocation(newLocation);

			System.out.println("\nTest #10");
			System.out.println("new Location: " + newLocation);
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		{
			boolean expResult = false;
			short newListeners = 0;
			boolean result = obj.setListeners(newListeners);

			System.out.println("\nTest #11");
			System.out.println("new Listeners: " + newListeners);
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		{
			boolean expResult = true;
			short newListeners = 1500;
			boolean result = obj.setListeners(newListeners);

			System.out.println("\nTest #12");
			System.out.println("new Listeners: " + newListeners);
			System.out.println("Excepted: " + expResult);
			System.out.println("Output: " + result);
			assertEquals(expResult, result);
		}
		// протестити сеттери
	}
}
