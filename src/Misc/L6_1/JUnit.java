/**
 * 
 */
package Misc.L6_1;

import static org.junit.Assert.*;

import org.junit.Test;
import MyLabs.SolutionP1;
import MyUtils.SomeUtils;

public class JUnit {
	// ïðèïóñòèìà òî÷í³ñòü ïîð³âíÿíü
	public static final double EPS = 1e-6;

	@Test
	public void test() {
		//TestP1_1();
		//TestP1_2();
		//TestP1_3();
		TestL6();
	}
	
	public static void TestP1_1() {
		// Test #1
		System.out.println("Test ¹1.1");
	{
		byte expResult = 3;
		Double[] arr = {6.0, 5.0, 1.0, 4.0, 6.0, 4.0};
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
		Double[] arr = {-5.0, 0.0, 0.0, -0.445, 5.355, 4.0, 0.0};
		byte result = SolutionP1.Task1(arr);
						
		System.out.println("\nTest #2");
		System.out.println("Array: " + SomeUtils.ArrayToString(arr));
		System.out.println("Excepted: " + expResult);
		System.out.println("Output: " + result);
		assertEquals(expResult, result);		
	}
			
	}
	
	public static void TestP1_2() {
		System.out.println("\nTest ¹1.2");
		{
			// Test #1
			double expResult = 0.0;
			Double[] arr = {-5.0, 0.0, 0.0, -0.445, 5.355, 4.0, 0.0};
			double result;
			try {
				result = SolutionP1.Task2(arr);
			} 
			catch (Exception ex) {
				result = 0.0;
			} 
			System.out.println("Test #1");
			System.out.println("Array: " + SomeUtils.ArrayToString(arr));
			System.out.println("Excepted: " + (expResult == 0.0 ? "Íåìîæëèâî îá÷èñëèòè äîáóòîê åëåìåíò³â ìàñèâó;" : expResult));
			System.out.println("Output: " + (result == 0.0 ? "Íåìîæëèâî îá÷èñëèòè äîáóòîê åëåìåíò³â ìàñèâó;" : expResult));
			assertEquals(expResult, result, EPS);	
		}
		
		{
			// Test #2
			double expResult = -108.0;
			Double[] arr = {4.0, 0.0, 6.0, -3.0, 6.0, 0.0, 6.0};
			double result;
			try {
				result = SolutionP1.Task2(arr);
			}
			catch (Exception ex) {
				result = 0.0;
			} 
			System.out.println("\nTest #2");
			System.out.println("Array: " + SomeUtils.ArrayToString(arr));
			System.out.println("Excepted: " + (expResult == 0.0 ? "Íåìîæëèâî îá÷èñëèòè äîáóòîê åëåìåíò³â ìàñèâó;" : expResult));
			System.out.println("Output: " + (result == 0.0 ? "Íåìîæëèâî îá÷èñëèòè äîáóòîê åëåìåíò³â ìàñèâó;" : expResult));
				assertEquals(expResult, result, EPS);
		}
	}

	public static void TestP1_3() {
		System.out.println("\nTest ¹1.3");
		{
			// Test #1
			Double[] expResult = {82.77, -23.8, 16.45, -43.87, -46.75, 32.28, 7.86, 25.71};
			Double[] arr = {-46.75, 32.28, 7.86, 25.71, 82.77, -23.8, 16.45, -43.87};
			Double[] result = SolutionP1.Task3(arr);;
			
			System.out.println("Test #1");
			System.out.println("Array: " + SomeUtils.ArrayToString(arr));
			System.out.println("Excepted: " + SomeUtils.ArrayToString(expResult) );
			System.out.println("Output: " + SomeUtils.ArrayToString(result));
			assertEquals(SomeUtils.ArrayToString(expResult), SomeUtils.ArrayToString(result));
		}
		
		{
			// Test #2
			Double[] expResult = {4.12, -23.8, 4.44, 4.44, 0.0};
			Double[] arr = {4.44, 4.44, 4.12, -23.8};
			Double[] result = SolutionP1.Task3(arr);;
						
			System.out.println("\nTest #2");
			System.out.println("Array: " + SomeUtils.ArrayToString(arr));
			System.out.println("Excepted: " + SomeUtils.ArrayToString(expResult) );
			System.out.println("Output: " + SomeUtils.ArrayToString(result));
			assertEquals(SomeUtils.ArrayToString(expResult), SomeUtils.ArrayToString(result));
		}
}

	public static void TestL6() {
		// Test #1
				System.out.println("\nTest L6");
			{
				boolean expResult = true;
				byte marks[] = {5,43,56,42,12};
				Student student = new Student("TestName", "TestSurname", (short) 1000, marks);
				boolean result = student.hasLowMarks();
				
				System.out.println("Test #1");
				System.out.println("Marks: " + SomeUtils.ArrayToString(new Byte[]{5,43,56,42,12}));
				System.out.println("Excepted: " + expResult);
				System.out.println("Output: " + result);
				assertEquals(expResult, result);
			}
				
			{
				boolean expResult = false;
				byte marks[] = {51,91,56,42,21};
				Student student = new Student("TestName", "TestSurname", (short) 1000, marks);
				boolean result = !student.hasLowMarks();
				
				System.out.println("\nTest #2");
				System.out.println("Marks: " + SomeUtils.ArrayToString(new Byte[]{51,91,56,42,21}));
				System.out.println("Excepted: " + expResult);
				System.out.println("Output: " + result);
				assertEquals(expResult, result);
			}
	}
}
