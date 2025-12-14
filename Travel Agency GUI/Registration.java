import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Objects;
import java.io.IOException;

public class Registration extends JFrame {

    private final JTextField userNameField;
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JComboBox<String> securityQuestionComboBox;
    private final JTextField answerField;
    private final JTextField captchaField;

    private final int captchaA;
    private final int captchaB;

    private static final Color BACKGROUND_COLOR = Color.decode("#F2F2F2");
    private static final Color BUTTON_COLOR = Color.decode("#2E75B6");
    private static final Color EXIT_BUTTON_COLOR = Color.decode("#C00000");
    private static final Color CAPTCHA_BG_COLOR = Color.decode("#FFD3D3");

    private static final Font TITLE_FONT = new Font("Segoe UI Black", Font.PLAIN, 35);
    private static final Font BUTTON_FONT = new Font("Segoe UI Black", Font.PLAIN, 25);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 25);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 19);
    private static final Font CAPTCHA_FONT = new Font("Segoe UI", Font.PLAIN, 25);

    Registration() {
        // Frame Layout
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Travel Agency");
        this.setSize(900, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        Container c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(BACKGROUND_COLOR);

        // Icon
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Icon.png")));
        this.setIconImage(icon.getImage());

        // Logo
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/LogoBlue.png")));
        JLabel imgLabel = new JLabel(logo);
        imgLabel.setBounds(30, 50, logo.getIconWidth(), logo.getIconHeight());
        c.add(imgLabel);

        // Title
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Enter Your Information");
        titleLabel.setBounds(430, 25, 500, 50);
        titleLabel.setFont(TITLE_FONT);
        c.add(titleLabel);

        // User Name
        JLabel userNameLabel = new JLabel();
        userNameLabel.setText("User Name");
        userNameLabel.setBounds(430, 75, 500, 50);
        userNameLabel.setFont(LABEL_FONT);
        c.add(userNameLabel);

        userNameField = new JTextField();
        userNameField.setBounds(570, 85, 260, 30);
        userNameField.setFont(FIELD_FONT);
        c.add(userNameField);

        // Email
        JLabel emailLabel = new JLabel();
        emailLabel.setText("Email");
        emailLabel.setBounds(430, 110, 500, 50);
        emailLabel.setFont(LABEL_FONT);
        c.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(570, 120, 260, 30);
        emailField.setFont(FIELD_FONT);
        c.add(emailField);

        // Password
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(430, 145, 500, 50);
        passwordLabel.setFont(LABEL_FONT);
        c.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(570, 155, 260, 30);
        passwordField.setFont(FIELD_FONT);
        passwordField.setEchoChar('*');
        c.add(passwordField);

        // Question
        JLabel questionLabel = new JLabel();
        questionLabel.setText("Question");
        questionLabel.setBounds(430, 180, 500, 50);
        questionLabel.setFont(LABEL_FONT);
        c.add(questionLabel);

        String[] secQsn = { "Choose a Security Question...", "Your dream job?", "Your favorite song?",
                "First pet's name?", "Your favorite hobby?" };
        securityQuestionComboBox = new JComboBox<>(secQsn);
        securityQuestionComboBox.setBounds(570, 190, 259, 30);
        securityQuestionComboBox.setSelectedIndex(0);
        securityQuestionComboBox.setFont(FIELD_FONT);
        securityQuestionComboBox.setBackground(Color.white);
        c.add(securityQuestionComboBox);

        // Answer
        JLabel answerLabel = new JLabel();
        answerLabel.setText("Answer");
        answerLabel.setBounds(430, 215, 500, 50);
        answerLabel.setFont(LABEL_FONT);
        c.add(answerLabel);

        answerField = new JTextField();
        answerField.setBounds(570, 225, 260, 30);
        answerField.setFont(FIELD_FONT);
        c.add(answerField);

        // Captcha Label and Text Field
        JLabel captchaLabel = new JLabel();
        captchaLabel.setText("Captcha");
        captchaLabel.setBounds(430, 250, 500, 50);
        captchaLabel.setFont(LABEL_FONT);
        c.add(captchaLabel);

        captchaField = new JTextField();
        captchaField.setBounds(615, 260, 215, 30);
        captchaField.setFont(FIELD_FONT);
        c.add(captchaField);

        // To get a random number for captcha
        Random rand = new Random();
        captchaA = rand.nextInt(10);
        captchaB = rand.nextInt(10);

        // Captcha
        JLabel captchaValueLabel = new JLabel();
        captchaValueLabel.setText(" " + captchaA + " + " + captchaB + " ");
        captchaValueLabel.setBounds(530, 260, 75, 30);
        captchaValueLabel.setFont(CAPTCHA_FONT);
        captchaValueLabel.setForeground(Color.red);
        captchaValueLabel.setBackground(CAPTCHA_BG_COLOR);
        captchaValueLabel.setOpaque(true);
        c.add(captchaValueLabel);

        // Cursor for JButtons
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        // JButtons
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(53, 325, 183, 50);
        exitButton.setFont(BUTTON_FONT);
        exitButton.setCursor(cursor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(EXIT_BUTTON_COLOR);
        c.add(exitButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(251, 325, 183, 50);
        backButton.setFont(BUTTON_FONT);
        backButton.setCursor(cursor);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(BUTTON_COLOR);
        c.add(backButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(450, 325, 183, 50);
        resetButton.setFont(BUTTON_FONT);
        resetButton.setCursor(cursor);
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(BUTTON_COLOR);
        c.add(resetButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(649, 325, 183, 50);
        registerButton.setFont(BUTTON_FONT);
        registerButton.setCursor(cursor);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(BUTTON_COLOR);
        c.add(registerButton);

        // Exit Button
        exitButton.addActionListener(ae -> System.exit(0));

        // Back Button
        backButton.addActionListener(ae -> {
            setVisible(false);
            new Home().setVisible(true);
            dispose();
        });

        // Reset Button
        resetButton.addActionListener(ae -> {
            userNameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            answerField.setText("");
            captchaField.setText("");
            securityQuestionComboBox.setSelectedIndex(0);
        });

        // Register Button
        registerButton.addActionListener(ae -> registerUser());
    }

    private void registerUser() {
        String userName = userNameField.getText().toLowerCase();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String answer = answerField.getText();
        String captchaText = captchaField.getText();
        String question = String.valueOf(securityQuestionComboBox.getSelectedItem());

        if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || answer.isEmpty() || captchaText.isEmpty()
                || (securityQuestionComboBox.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(this, "Please fill all of the fields.", "Warning!",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int captchaResult = Integer.parseInt(captchaText);
            if (captchaResult != (captchaA + captchaB)) {
                JOptionPane.showMessageDialog(this, "Wrong Captcha.", "Warning!", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Captcha. Please enter a number.", "Warning!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            AuthService.registerUser(userName, password, email, question, answer);
            JOptionPane.showMessageDialog(this, "Registration Successfully Completed.",
                    "Registration Complete", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            new Home().setVisible(true);
            dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save registration data.", "File Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Registration().setVisible(true));
    }
}
