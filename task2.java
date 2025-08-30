import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class task2 extends JFrame implements ActionListener {
    private JTextField guessField;
    private JButton guessButton, resetButton;
    private JTextArea resultArea;
    private int randomNumber, attempts;

    public task2() {
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(10);

        guessButton = new JButton("Submit Guess");
        resetButton = new JButton("Reset Game");

        resultArea = new JTextArea(8, 30);
        resultArea.setEditable(false);

        add(instructionLabel);
        add(guessField);
        add(guessButton);
        add(resetButton);
        add(new JScrollPane(resultArea));

        guessButton.addActionListener(this);
        resetButton.addActionListener(this);

        startNewGame();
    }

    private void startNewGame() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1; // number between 1 and 100
        attempts = 0;
        resultArea.setText("Game started! Enter your guess.\n");
        guessField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int userGuess = Integer.parseInt(guessField.getText());
                attempts++;

                if (userGuess < randomNumber) {
                    resultArea.append("Attempt " + attempts + ": " + userGuess + " is too low!\n");
                } else if (userGuess > randomNumber) {
                    resultArea.append("Attempt " + attempts + ": " + userGuess + " is too high!\n");
                } else {
                    resultArea.append("🎉 Correct! The number was " + randomNumber +
                                      ". You guessed it in " + attempts + " attempts.\n");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == resetButton) {
            startNewGame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new task2().setVisible(true));
    }
}
