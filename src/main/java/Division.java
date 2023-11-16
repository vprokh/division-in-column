import java.util.Scanner;

public class Division {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the first any integer: ");
		int dividend = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the second any integer: ");
		int divider = Integer.parseInt(scanner.nextLine());
		System.out.println(LogicDivision.makeDivision(dividend, divider));
	}
}