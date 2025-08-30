import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class task1 extends JFrame implements ActionListener {
    private JTextField inputField;
    private JComboBox<String> unitBox;
    private JTextArea resultArea;
    private JButton convertButton;

    public task1() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter Temperature:");
        inputField = new JTextField(10);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitBox = new JComboBox<>(units);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        resultArea = new JTextArea(6, 30);
        resultArea.setEditable(false);

        add(label);
        add(inputField);
        add(unitBox);
        add(convertButton);
        add(new JScrollPane(resultArea));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double value = Double.parseDouble(inputField.getText());
            String unit = (String) unitBox.getSelectedItem();

            StringBuilder result = new StringBuilder();

            if ("Celsius".equals(unit)) {
                result.append("Fahrenheit: ").append(String.format("%.2f °F", (value * 9 / 5) + 32)).append("\n");
                result.append("Kelvin: ").append(String.format("%.2f K", value + 273.15)).append("\n");
            } else if ("Fahrenheit".equals(unit)) {
                result.append("Celsius: ").append(String.format("%.2f °C", (value - 32) * 5 / 9)).append("\n");
                result.append("Kelvin: ").append(String.format("%.2f K", (value - 32) * 5 / 9 + 273.15)).append("\n");
            } else if ("Kelvin".equals(unit)) {
                result.append("Celsius: ").append(String.format("%.2f °C", value - 273.15)).append("\n");
                result.append("Fahrenheit: ").append(String.format("%.2f °F", (value - 273.15) * 9 / 5 + 32)).append("\n");
            }

            resultArea.setText(result.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new task1().setVisible(true));
    }
}
