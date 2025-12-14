import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;

public class AdminAdd extends JFrame {

    private final JTextField userNameField;
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JComboBox<String> securityQuestionComboBox;
    private final JTextField answerField;

    private static final Color BACKGROUND_COLOR = Color.decode("#F2F2F2");
    private static final Color BUTTON_COLOR = Color.decode("#2E75B6");
    private static final Font TITLE_FONT = new Font("Segoe UI Black", Font.PLAIN, 35);
    private static final Font BUTTON_FONT = new Font("Segoe UI Black", Font.PLAIN, 25);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 25);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 19);

    AdminAdd() {
        // Frame Layout
        // This frame should not exit the app, but rather be disposed on close.
        // The UserData frame will handle closing or going back.
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Travel Agency");
        this.setSize(510, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        Container c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(BACKGROUND_COLOR);

        // Icon
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Icon.png")));
        this.setIconImage(icon.getImage());

        // Title
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Enter Information");
        titleLabel.setBounds(100, 25, 500, 50);
        titleLabel.setFont(TITLE_FONT);
        c.add(titleLabel);

        // User Name
        JLabel userNameLabel = new JLabel();
        userNameLabel.setText("User Name");
        userNameLabel.setBounds(45, 75, 500, 50);
        userNameLabel.setFont(LABEL_FONT);
        c.add(userNameLabel);

        userNameField = new JTextField();
        userNameField.setBounds(185, 85, 260, 30);
        userNameField.setFont(FIELD_FONT);
        c.add(userNameField);

        // Email
        JLabel emailLabel = new JLabel();
        emailLabel.setText("Email");
        emailLabel.setBounds(45, 110, 500, 50);
        emailLabel.setFont(LABEL_FONT);
        c.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(185, 120, 260, 30);
        emailField.setFont(FIELD_FONT);
        c.add(emailField);

        // Password
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(45, 145, 500, 50);
        passwordLabel.setFont(LABEL_FONT);
        c.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(185, 155, 260, 30);
        passwordField.setFont(FIELD_FONT);
        passwordField.setEchoChar('*');
        c.add(passwordField);

        // Question
        JLabel questionLabel = new JLabel();
        questionLabel.setText("Question");
        questionLabel.setBounds(45, 180, 500, 50);
        questionLabel.setFont(LABEL_FONT);
        c.add(questionLabel);

        String[] secQsn = { "Choose a Security Question...", "Your dream job?", "Your favorite song?",
                "First pet's name?", "Your favorite hobby?" };
        securityQuestionComboBox = new JComboBox<>(secQsn);
        securityQuestionComboBox.setBounds(185, 190, 259, 30);
        securityQuestionComboBox.setSelectedIndex(0);
        securityQuestionComboBox.setFont(FIELD_FONT);
        securityQuestionComboBox.setBackground(Color.white);
        c.add(securityQuestionComboBox);

        // Answer
        JLabel answerLabel = new JLabel();
        answerLabel.setText("Answer");
        answerLabel.setBounds(45, 215, 500, 50);
        answerLabel.setFont(LABEL_FONT);
        c.add(answerLabel);

        answerField = new JTextField();
        answerField.setBounds(185, 225, 260, 30);
        answerField.setFont(FIELD_FONT);
        c.add(answerField);

        // Cursor for JButtons
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        // JButtons
        JButton backButton = new JButton("Back");
        backButton.setBounds(51, 280, 183, 50);
        backButton.setFont(BUTTON_FONT);
        backButton.setCursor(cursor);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(BUTTON_COLOR);
        c.add(backButton);

        JButton addButton = new JButton("Add");
        addButton.setBounds(260, 280, 183, 50);
        addButton.setFont(BUTTON_FONT);
        addButton.setCursor(cursor);
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(BUTTON_COLOR);
        c.add(addButton);

        // Back Button
        backButton.addActionListener(ae -> {
            setVisible(false);
            new UserData().setVisible(true);
            dispose();
        });

        // Add Button
        addButton.addActionListener(ae -> {
            String userName = userNameField.getText().toLowerCase();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String answer = answerField.getText();
            String question = String.valueOf(securityQuestionComboBox.getSelectedItem());

            if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || answer.isEmpty()
                    || (securityQuestionComboBox.getSelectedIndex() == 0)) {
                JOptionPane.showMessageDialog(null, "Please fill all of the fields.", "Warning!",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                AuthService.addUserByAdmin(userName, password, email, question, answer);
                JOptionPane.showMessageDialog(null, "User has been added.", "User Added",
                        JOptionPane.INFORMATION_MESSAGE);
                // Go back to the user list, which should be refreshed.
                setVisible(false);
                new UserData().setVisible(true);
                dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not write to user data file.", "File Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminAdd().setVisible(true));
    }
}
