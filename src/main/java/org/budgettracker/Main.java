package org.budgettracker;
//  This is temp main to test code functionality
public class Main {
    public static void main(String[] args) {

        ExpenseTracker tracker = new ExpenseTracker();

        //test insert data
        tracker.addTransaction(new Transaction((short) 1, "Job", 4500, "Total month income"));
        tracker.addTransaction(new Transaction((short) 2, "Rent", 1000, "Rent"));
        tracker.addTransaction(new Transaction((short) 2, "Food", 300, "Monthly grocery's"));
        tracker.addTransaction(new Transaction((short) 2, "Internet", 60, "internet bill"));

        System.out.printf("Monthly Income : %.2f\n", tracker.getMonthIncome());
        System.out.printf("Monthly Expenses : %.2f\n", tracker.getTotalExpenses());
        System.out.printf("Remaining : %.2f\n", tracker.getRemainingBalance());

        System.out.println("\n-------------Transactions----------------------------");
        // id, type, category, $$$, description
        for (Transaction t : tracker.getTransactions()) {
            System.out.println("[" + t.getId() + "] " + t.getType() + " - " + t.getCategory() + " - $" + t.getAmount() + " | " + t.getDescription()
            );
        }

        System.out.println("Remaining: " + tracker.getRemainingBalance());

    }
}
