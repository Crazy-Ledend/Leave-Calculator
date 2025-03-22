import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaveCalculatorGoogleUI extends JFrame {
    private JTextField totalClassesField, attendedClassesField;
    private JLabel resultLabel;

    public LeaveCalculatorGoogleUI() {
        setTitle("Leave Calculator");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main Panel (Google-style white card)
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Leave Calculator");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);

        JLabel totalLabel = new JLabel("Total Classes Conducted:");
        totalClassesField = createTextField();

        gbc.gridy = 1; gbc.gridwidth = 1; gbc.gridx = 0;
        panel.add(totalLabel, gbc);
        gbc.gridx = 1;
        panel.add(totalClassesField, gbc);

        JLabel attendedLabel = new JLabel("Classes Attended:");
        attendedClassesField = createTextField();

        gbc.gridy = 2; gbc.gridx = 0;
        panel.add(attendedLabel, gbc);
        gbc.gridx = 1;
        panel.add(attendedClassesField, gbc);

        JButton calculateButton = createButton("Calculate");
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(calculateButton, gbc);

        resultLabel = new JLabel("Enter details and press Calculate", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        resultLabel.setForeground(Color.DARK_GRAY);

        gbc.gridy = 4;
        panel.add(resultLabel, gbc);

        add(panel, BorderLayout.CENTER);
        getContentPane().setBackground(new Color(240, 240, 240)); // Light grey background

        setVisible(true);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateBunkableClasses();
            }
        });
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(10);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(66, 133, 244)); // Google Blue
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        return button;
    }

    private void calculateBunkableClasses() {
        try {
            int totalClasses = Integer.parseInt(totalClassesField.getText());
            int attendedClasses = Integer.parseInt(attendedClassesField.getText());
            final double REQUIRED_ATTENDANCE = 75.0;

            if (totalClasses == 0) {
                resultLabel.setText("⚠ No classes conducted yet!");
                return;
            }

            double currentAttendance = (attendedClasses * 100.0) / totalClasses;

            if (currentAttendance < REQUIRED_ATTENDANCE) {
                resultLabel.setText("❌ Dei parama, padi daa");
                return;
            }

            // Calculate how many classes can be bunked
            int bunkableClasses = 0;
            while (((double) attendedClasses / (totalClasses + bunkableClasses)) * 100 >= REQUIRED_ATTENDANCE) {
                bunkableClasses++;
            }

            resultLabel.setText("✅ You can bunk " + (bunkableClasses - 1) + " classes!");
        } catch (NumberFormatException ex) {
            resultLabel.setText("❌ Please enter valid numbers!");
        }
    }

    public static void main(String[] args) {
        new LeaveCalculatorGoogleUI();
    }
}
