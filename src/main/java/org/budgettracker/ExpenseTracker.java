package org.budgettracker;

import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;

// This class helps with calculation of transactions and stores list of transaction objs
//

public class ExpenseTracker {
    private ArrayList<Transaction> transactions;
    //private double totalIncome; // i will do something more with this later
    //private double totalExpenses;

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

    public boolean deleteTransactionById(int id) {
        return transactions.removeIf(t -> t.getId() == id);
    }

    public Transaction getTransactionById(int id) {
        for (Transaction t : transactions) if (t.getId() == id) return t;
        return null;
    }

    public boolean updateTransaction(int id, double newAmount, String newDesc) {
        Transaction t = getTransactionById(id);
        if (t == null) return false;
        t.setAmount(newAmount);                 // add setters in Transaction
        if (newDesc != null) t.setDescription(newDesc);
        return true;
    }

}
