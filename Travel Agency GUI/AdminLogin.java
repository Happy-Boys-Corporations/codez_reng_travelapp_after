import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;

public class AdminLogin extends JFrame {

    private final JTextField adminNameField;
    private final JPasswordField passwordField;

    AdminLogin() {
        // Frame Layout
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Travel Agency");
        this.setSize(900, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        Container c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.decode("#F2F2F2"));

        // Icon
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Icon.png")));
        this.setIconImage(icon.getImage());

        // Logo
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/LogoBlue.png")));
        JLabel imgLabel = new JLabel(logo);
        imgLabel.setBounds(30, 50, logo.getIconWidth(), logo.getIconHeight());
        c.add(imgLabel);

        // Fonts
        Font titleFont = new Font("Segoe UI Black", Font.BOLD, 60);
        Font buttonFont = new Font("Segoe UI Black", Font.PLAIN, 25);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 30);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 22);

        // Title
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Admin Login");
        titleLabel.setBounds(420, 50, 500, 90);
        titleLabel.setFont(titleFont);
        c.add(titleLabel);

        // User Name
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name");
        nameLabel.setBounds(430, 145, 500, 50);
        nameLabel.setFont(labelFont);
        c.add(nameLabel);

        adminNameField = new JTextField();
        adminNameField.setBounds(590, 155, 210, 35);
        adminNameField.setFont(fieldFont);
        c.add(adminNameField);

        // Password
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(430, 205, 500, 50);
        passwordLabel.setFont(labelFont);
        c.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(590, 215, 210, 35);
        passwordField.setFont(buttonFont);
        passwordField.setEchoChar('*');
        c.add(passwordField);

        // Cursor for JButtons
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        // JButtons
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(90, 325, 215, 50);
        exitButton.setFont(buttonFont);
        exitButton.setCursor(cursor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.decode("#C00000"));
        c.add(exitButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(340, 325, 215, 50);
        backButton.setFont(buttonFont);
        backButton.setCursor(cursor);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.decode("#2E75B6"));
        c.add(backButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(590, 325, 215, 50);
        loginButton.setFont(buttonFont);
        loginButton.setCursor(cursor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.decode("#2E75B6"));
        c.add(loginButton);

        // Exit Button
        exitButton.addActionListener(ae -> System.exit(0));

        // Back Button
        backButton.addActionListener(ae -> {
            setVisible(false);
            new Home().setVisible(true);
            dispose();
        });

        // Login Button
        loginButton.addActionListener(ae -> {
            String adminName = adminNameField.getText().toLowerCase();
            String password = new String(passwordField.getPassword());

            if (adminName.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all of the fields.", "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    if (AuthService.authenticateAdmin(adminName, password)) {
                        JOptionPane.showMessageDialog(null, "Admin Login Successful.", "Login Success",
                                JOptionPane.INFORMATION_MESSAGE);

                        setVisible(false);
                        new Admin().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Name or Password!", "Warning!",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Could not read admin data file.", "File Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminLogin().setVisible(true));
    }
}
