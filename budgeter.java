// This program tells the user their net monthly savings 
// or expenses for given income and expense values.

import java.util.*;

public class Budgeter {
	public static final int DAYS_IN_MONTH = 31;

    public static void main (String[] args) {
		Scanner console = new Scanner(System.in);
	
	    intro();
	    double income = categories(console, "income");
	    int monOrDay = monthlyOrDaily(console);
	    double expense = categories(console, "expense");
	    totals(income, monOrDay, expense);
        result(monOrDay, income, expense);
   
    }
   
    // Prints the intro statement. 
	public static void intro() {
    	System.out.println("This program asks for your monthly income and");
    	System.out.println("expenses, then tells you your net monthly income.");
    	System.out.println();
	}

    // Reads in a Scanner and monetary type parameter, computes, and 
    // returns total value of income and expense.
	public static double categories(Scanner console, String type) {
		System.out.print("How many categories of " + type + "? ");
		int numCat = console.nextInt();
   		double total = 0;
   		for (int i = 1; i <= numCat; i++) {
   			System.out.print("    Next " + type + " amount? $");
   			double amount = console.nextDouble();
   			total +=amount;
   		}
   		System.out.println();
   		return total;
	}
   
    // Reads in a Scanner and returns key for monthly or daily expenses.
	public static int monthlyOrDaily(Scanner console) {
    	System.out.print("Enter 1) monthly or 2) daily expenses? ");
        int monthlyOrDaily = console.nextInt();
        return monthlyOrDaily;
    }
   
    // Reads in income, key for month or day, and expense parameters.
    // Computes and prints total income and expenses.
	public static void totals(double income, int monOrDay, double expense) {
   		System.out.print("Total income = ");
    	System.out.println("$" + round2(income) + 
			" ($" + round2(income / DAYS_IN_MONTH) + "/day)");
        System.out.print("Total expenses = ");
        if (monOrDay == 1) {
           	System.out.println("$" + round2(expense) + 
			   " ($" + round2(expense / DAYS_IN_MONTH) + "/day)");
        } else {
           	System.out.println("$" + round2(expense * DAYS_IN_MONTH) + 
			   " ($" + round2(expense) + "/day)");
        }
        System.out.println();                          
	}
   
    // Reads in key for month or day, income, and expense parameters.
    // Prints net savings, savings category, and custom message.
	public static void result(int monOrDay, double income, double expense) {
    	if (monOrDay == 2) {
        	expense = expense * DAYS_IN_MONTH;
    	}
	    double total = income - expense;
	    if (total > 0) {
	   		System.out.println("You earned $" + round2(total) + 
			   " more than you spent this month.");
	   	if (total <= 250) {
	   		System.out.println("You're a saver.");
	   		System.out.println("Go treat yourself! (But not too much.)");
	   	} else {
	   		System.out.println("You're a big saver.");
	   		System.out.println("Go treat yourself!");
	   	}
	    } else {
	   		System.out.println("You spent $" + round2(Math.abs(total)) + 
			   " more than you earned this month.");
	   	if (total > -250) {
	   		System.out.println("You're a spender.");
	   		System.out.println("No more shopping for you.");
	   	} else {
	   		System.out.println("You're a big spender.");
	   		System.out.println("Stop that.");
	    }
      }
	}
   
	// Returns numbers that are rounded to two decimal places.
    public static double round2(double num) {
    	return Math.round(num * 100.0) / 100.0;
    }
}
