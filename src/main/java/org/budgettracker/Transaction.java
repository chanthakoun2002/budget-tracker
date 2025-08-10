package org.budgettracker;

// This class is for the creation of tranaction objs and should all be filled
// and stored in expense class array as single object
// each obj is given a unique ID that increments from the last id

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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
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
