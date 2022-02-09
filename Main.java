import java.util.Scanner;

//L1
//09.02.2021
class SolutionL1{
	// #1
	public static void Solve1() {
		Scanner in = new Scanner(System.in);
		System.out.println("--== Expression calculator ==--");
		System.out.println("e = 3f + 2r, f = 5");
		System.out.println("e = √(8r) / (2f - 4), f = 0");
		System.out.println("e = fr - 9, f = -5");
		System.out.println("f is Integer, e is Decimal");
		System.out.print("Enter f: ");
		byte f = in.nextByte();
		System.out.print("Enter r: ");
		double r = in.nextDouble();
		in.close();
		
		double e = .0;
		switch(f) {
			case 5:{
				e = 15.0 + 2 * r;
				break;
			}
			case 0:{
				if (r < 0) {
					System.out.print("Result: Unable to calculate expression √(8r) (r < 0)");
					return;
				}
				e = Math.sqrt(8 * r) / -4.0;
				break;
			}
			case -5:{
				e = -5.0 * r - 9.0;
				break;
			}
			default:{
				System.out.print("Result: Unable to calculate expression");
				return;
			}
		}
		
		/*if (f == 5) {
			e = 15.0 + 2 * r;
		}
		else if (f == 0) {
			if (r < 0) {
				System.out.print("Result: Unable to calculate expression √(8r) (r < 0)");
				return;
			}
			e = Math.sqrt(8 * r) / -4.0;
		}
		else if (f == -5) {
			e = -5.0 * r - 9.0;
		}
		else {
			System.out.print("Result: Unable to calculate expression");
		}*/
		
		System.out.printf("Result: e = %f",e);
	}

	// #2
	public static void Solve2() {
		double x1,y1,x2,y2,x3,y3;
		Scanner in = new Scanner(System.in);
		
		System.out.println("\n--==Are 3 points on one straight line ? ==--");
		System.out.print("Enter first point (x1,y1): ");
		x1 = in.nextFloat();
		y1 = in.nextFloat();
		System.out.print("Enter second point (x2,y2): ");
		x2 = in.nextFloat();
		y2 = in.nextFloat();
		System.out.print("Enter third point (x3,y3): ");
		x3 = in.nextFloat();
		y3 = in.nextFloat();
		in.close();
		
		double expr = (x3 - x1) / (x2 - x1) - (y3 - y1) / (y2 - y1);
		if ( Math.pow(expr,2) <= 1e-8 ) {
			System.out.println("3 points are on one straight line;");
		}
		else {
			System.out.println("3 points aren't on one straight line;");
		}
		
	}

	// #3
	public static void Solve3() {
		System.out.println("--== Time: ==--");
		System.out.print("Enter n (n >= 0 and n < 24): ");
		Scanner in = new Scanner(System.in);
		byte n = in.nextByte();
		
		if (n < 0 || n > 23) {
			System.out.println("n > 23 or n < 0");
			return;
		}
		
		String str = " AM";
		if (n == 0) {
			n = 12;
		}
		else if (n == 12) {
			str = " PM";
		}
		else if (n > 12) {
			n %= 12;
			str = " PM";
		}
		
		System.out.println("It's " + n + str);
		}

	// #4
	public static void Solve4() {
		Scanner in = new Scanner(System.in);
		System.out.println("--== Expression calculator: ==--");
		System.out.println("e = √(3x / (z - 2y)) - yz + sin(x^2)");
		System.out.print("Enter x:");
		double x = in.nextDouble();
		System.out.print("Enter y:");
		double y = in.nextDouble();
		System.out.print("Enter z:");
		double z = in.nextDouble();
		in.close();
		
		if (z - 2 * y == 0)
			throw new ArithmeticException("z - 2y = 0"); 
		else if (3 * x / (z - 2 * y) < 0)
			throw new ArithmeticException("3x / (z - 2y) < 0"); 
		
		double t = Math.sqrt(3 * x / (z - 2 * y)) - y * z + Math.sin(x * x);
		System.out.println("Result: t = " + t);
	}
}


class Main {
	public static void main(String[] args) {
		//SolutionL1.Solve1();
		//SolutionL1.Solve2();
		//SolutionL1.Solve3();
		try{
			SolutionL1.Solve4();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
