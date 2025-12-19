import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class Login extends JFrame {

    private final JTextField userNameField;
    private final JPasswordField passwordField;

    private static final Color BACKGROUND_COLOR = Color.decode("#F2F2F2");
    private static final Color BUTTON_COLOR = Color.decode("#2E75B6");
    private static final Color EXIT_BUTTON_COLOR = Color.decode("#C00000");
    private static final Font TITLE_FONT = new Font("Segoe UI Black", Font.BOLD, 60);
    private static final Font BUTTON_FONT = new Font("Segoe UI Black", Font.PLAIN, 25);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 30);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 22);

    Login() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Travel Agency");
        this.setSize(900, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        Container c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(BACKGROUND_COLOR);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Icon.png")));
        this.setIconImage(icon.getImage());

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/LogoBlue.png")));
        JLabel imgLabel = new JLabel(logo);
        imgLabel.setBounds(30, 50, logo.getIconWidth(), logo.getIconHeight());
        c.add(imgLabel);

        JLabel titleLabel = new JLabel();
        titleLabel.setText("User Login");
        titleLabel.setBounds(450, 50, 500, 90);
        titleLabel.setFont(TITLE_FONT);
        c.add(titleLabel);

        JLabel userNameLabel = new JLabel();
        userNameLabel.setText("User Name");
        userNameLabel.setBounds(430, 145, 500, 50);
        userNameLabel.setFont(LABEL_FONT);
        c.add(userNameLabel);

        userNameField = new JTextField();
        userNameField.setBounds(600, 155, 200, 35);
        userNameField.setFont(FIELD_FONT);
        c.add(userNameField);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(430, 205, 500, 50);
        passwordLabel.setFont(LABEL_FONT);
        c.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(600, 215, 200, 35);
        passwordField.setFont(FIELD_FONT);
        passwordField.setEchoChar('*');
        c.add(passwordField);

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(90, 325, 215, 50);
        exitButton.setFont(BUTTON_FONT);
        exitButton.setCursor(cursor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(EXIT_BUTTON_COLOR);
        c.add(exitButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(340, 325, 215, 50);
        backButton.setFont(BUTTON_FONT);
        backButton.setCursor(cursor);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(BUTTON_COLOR);
        c.add(backButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(590, 325, 215, 50);
        loginButton.setFont(BUTTON_FONT);
        loginButton.setCursor(cursor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(BUTTON_COLOR);
        c.add(loginButton);

        exitButton.addActionListener(ae -> System.exit(0));

        backButton.addActionListener(ae -> {
            setVisible(false);
            new Home().setVisible(true);
            dispose();
        });

        loginButton.addActionListener(ae -> {
            String userName = userNameField.getText().toLowerCase();
            String password = new String(passwordField.getPassword());

            if (userName.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all of the fields.", "Warning!",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                if (AuthService.authenticateUser(userName, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful.", "Login Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    setVisible(false);
                    new Packs().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid User Name or Password!", "Warning!",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not read user data file.", "File Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
