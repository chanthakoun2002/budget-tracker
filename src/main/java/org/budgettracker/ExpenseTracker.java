package org.budgettracker;
import java.util.ArrayList;

public class ExpenseTracker {
    private ArrayList<Transaction> transactions;

    public ExpenseTracker() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public double getMonthIncome() {
        double sum = 0;
        for (Transaction t : transactions) {
            // if 1 flag as income
            if (t.getType() == 1) {
                sum += t.getAmount();
            }
        }
        return sum;
    }

    public double getTotalExpenses() {
        double sum = 0;
        for (Transaction t : transactions) {
            // 2 flagged as expense
            if (t.getType() == 2) {
                sum += t.getAmount();
            }
        }
        return sum;
    }

    public double getRemainingBalance() {
        return getMonthIncome() - getTotalExpenses();
    }
}
