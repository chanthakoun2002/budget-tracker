package org.budgettracker;

public class Transaction  {
    private int id;
    private short  type; // income or expense, 1 for income 2 for expenses
    private String category; //types of expenses, essential, recreation, ect.
    private double amount;
    private String description;
    private static int nextId = 1;

    //constructor
    public Transaction (short type, String category, double amount, String description) {
        this.id = nextId++;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.description = description;

    }

    public int getId() {
        return id;
    }
    public int getType() {
        return type;
    }
    public String getCategory() {
        return category;
    }
    public double getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }

}
