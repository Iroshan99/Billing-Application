//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillingApplication extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField itemField, quantityField, priceField, totalField;

    public BillingApplication() {
        // Set up the frame
        setTitle("Billing Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table for bill items
        tableModel = new DefaultTableModel(new String[]{"Item", "Quantity", "Price", "Total"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for input fields and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 5));

        itemField = new JTextField();
        quantityField = new JTextField();
        priceField = new JTextField();
        totalField = new JTextField();
        totalField.setEditable(false);  // total field is not editable

        inputPanel.add(new JLabel("Item:"));
        inputPanel.add(itemField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Total:"));
        inputPanel.add(totalField);

        JButton addButton = new JButton("Add");
        JButton clearButton = new JButton("Clear");
        JButton calculateButton = new JButton("Calculate Total");

        inputPanel.add(addButton);
        inputPanel.add(clearButton);
        inputPanel.add(calculateButton);

        add(inputPanel, BorderLayout.NORTH);

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToBill();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });
    }

    private void addItemToBill() {
        String item = itemField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        double total = quantity * price;

        tableModel.addRow(new Object[]{item, quantity, price, total});

        clearFields();
    }

    private void clearFields() {
        itemField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    private void calculateTotal() {
        double grandTotal = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            grandTotal += (double) tableModel.getValueAt(i, 3);
        }
        totalField.setText(String.valueOf(grandTotal));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BillingApplication().setVisible(true);
            }
        });
    }
}
