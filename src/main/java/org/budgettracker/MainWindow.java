package org.budgettracker;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class MainWindow {
    private JFrame window;
    private JPanel expensesList;
    private JPanel incomeList;
    private JLabel incomeLabel, expenseLabel, balanceLabel;
    private ExpenseTracker tracker;

    public MainWindow() {
        window = new JFrame();
        window.setTitle("Budget And Expense Tracker");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(900, 560);
        window.setLocationRelativeTo(null); // centers items to screen
        window.setResizable(false);

        window.setLayout(new BorderLayout(10, 5));

        window.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainPanel.add(buildSummaryPanel(), BorderLayout.NORTH);
        mainPanel.add(buildListsPanel(),   BorderLayout.CENTER);
        mainPanel.add(buildFormPanel(),    BorderLayout.SOUTH);

        window.add(mainPanel, BorderLayout.CENTER);

        tracker = new ExpenseTracker();
    }

    public void Show() {
        window.setVisible(true);
    }

    private JPanel buildSummaryPanel() {
        //top labels for income, expense and balance overview
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        incomeLabel  = new JLabel("Income: $0.00");
        expenseLabel = new JLabel("Expenses: $0.00");
        balanceLabel = new JLabel("Remaining Balance: $0.00");
        p.add(incomeLabel);
        p.add(expenseLabel);
        p.add(balanceLabel);
        return p;
    }

    private JPanel buildListsPanel() {
        JPanel p = new JPanel(new GridLayout(1, 2, 10, 0));

        // left panel for expenses
        expensesList = new JPanel();
        expensesList.setLayout(new BoxLayout(expensesList, BoxLayout.Y_AXIS));
        JScrollPane expensesScroll = new JScrollPane(expensesList);
        expensesScroll.setBorder(BorderFactory.createTitledBorder("Expenses"));

        // right panel for income
        incomeList = new JPanel();
        incomeList.setLayout(new BoxLayout(incomeList, BoxLayout.Y_AXIS));
        JScrollPane incomeScroll = new JScrollPane(incomeList);
        incomeScroll.setBorder(BorderFactory.createTitledBorder("Income"));

        p.add(expensesScroll);
        p.add(incomeScroll);
        return p;
    }

    private JPanel buildFormPanel() {
        //bottom section for entering information
        JPanel p = new JPanel(new BorderLayout());

        JPanel fields = new JPanel(new GridLayout(2, 4, 8, 6));
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Income", "Expense"});

        JTextField categoryField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField descField = new JTextField();

        fields.add(new JLabel("Type:"));
        fields.add(typeCombo);
        fields.add(new JLabel("Category:"));
        fields.add(categoryField);
        fields.add(new JLabel("Amount:"));
        fields.add(amountField);
        fields.add(new JLabel("Description:"));
        fields.add(descField);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addBtn = new JButton("Add");
        buttons.add(addBtn);

        // upon button click items from fields will be added to arraylist
        addBtn.addActionListener(e -> {
            try {
                String typeStr = (String) typeCombo.getSelectedItem(); // "Income" or "Expense"
                String category = categoryField.getText().trim();
                String desc = descField.getText().trim();
                double amount = Double.parseDouble(amountField.getText().trim());

                if (category.isEmpty() || amount <= 0) {
                    // user needs to enter amount and category to be able to add item
                    // type will be defaulted
                    JOptionPane.showMessageDialog(window, "Enter a category and amount > 0");
                    return;
                }
                // income == 1 otherwise expense == 2
                short type = (short) ("Income".equalsIgnoreCase(typeStr) ? 1 : 2);

                Transaction t = new Transaction(type, category, amount, desc);
                tracker.addTransaction(t);

                String rowText = String.format("%s - %s  $%.2f  |  %s",
                        //t.getId(),
                        (type == 1 ? "Income" : "Expense"),
                        t.getCategory(),
                        t.getAmount(),
                        t.getDescription());

                JPanel target = (type == 1) ? incomeList : expensesList;
                addEntryRow(target, rowText, t.getId());

                refreshSummary(tracker.getMonthIncome(), tracker.getTotalExpenses());

                categoryField.setText("");
                amountField.setText("");
                descField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(window, "Amount must be a number.");
            }
        });

        p.add(fields,  BorderLayout.CENTER);
        p.add(buttons, BorderLayout.SOUTH);
        return p;
    }

    private void refreshSummary(double income, double expenses) {
        // this method updates the summary, should be called each time panels are updated
        incomeLabel.setText(String.format("Income: $%.2f", income));
        expenseLabel.setText(String.format("Expenses: $%.2f", expenses));
        balanceLabel.setText(String.format("Balance: $%.2f", income - expenses));
    }

    private void addEntryRow(JPanel targetList, String labelText, int txId) {
        // this method created rows to be inserted into panels with deletion button and edit
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        row.putClientProperty("txId", txId);
        JLabel text = new JLabel(labelText);
        JButton edit = new JButton("Edit");
        JButton del  = new JButton("Delete");

        del.addActionListener(e -> {
            int id = (int) row.getClientProperty("txId");
            if (tracker.deleteTransactionById(id)) {
                targetList.remove(row);
                targetList.revalidate();
                targetList.repaint();
                refreshSummary(tracker.getMonthIncome(), tracker.getTotalExpenses());
            }
        });


        edit.addActionListener(e -> {
            // TODO should be able to update rows from the gui, add later
        });

        row.add(text);
        row.add(edit);
        row.add(del);
        targetList.add(row);
        targetList.revalidate();
        targetList.repaint();
    }

}
