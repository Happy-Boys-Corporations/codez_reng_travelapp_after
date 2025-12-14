import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;

public class AdminPassword extends JFrame {

    private final JTextField nameField;
    private final JPasswordField passwordField;

    AdminPassword() {
        // Frame Layout
        this.setTitle("Change Admin Password");
        this.setSize(520, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.decode("#F2F2F2"));

        // Icon
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Icon.png")));
        this.setIconImage(icon.getImage());

        // Fonts
        Font buttonFont = new Font("Segoe UI Black", Font.PLAIN, 25);
        Font titleFont = new Font("Segoe UI Black", Font.PLAIN, 40);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 30);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 22);

        // Title
        JLabel titleLabel1 = new JLabel();
        titleLabel1.setText("Change Admin Name");
        titleLabel1.setBounds(50, 5, 500, 90);
        titleLabel1.setFont(titleFont);
        c.add(titleLabel1);

        JLabel titleLabel2 = new JLabel();
        titleLabel2.setText("And Password");
        titleLabel2.setBounds(50, 50, 500, 90);
        titleLabel2.setFont(titleFont);
        c.add(titleLabel2);

        // User Name
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name");
        nameLabel.setBounds(50, 140, 500, 50);
        nameLabel.setFont(labelFont);
        c.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(210, 150, 250, 35);
        nameField.setFont(fieldFont);
        c.add(nameField);

        // Password
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(50, 200, 500, 50);
        passwordLabel.setFont(labelFont);
        c.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(210, 210, 250, 35);
        passwordField.setFont(buttonFont);
        passwordField.setEchoChar('*');
        c.add(passwordField);

        // Cursor for JButtons
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        // JButtons
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(31, 280, 208, 43);
        closeButton.setFont(buttonFont);
        closeButton.setCursor(cursor);
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.decode("#2E75B6"));
        c.add(closeButton);

        JButton changeButton = new JButton("Change");
        changeButton.setBounds(265, 280, 208, 43);
        changeButton.setFont(buttonFont);
        changeButton.setCursor(cursor);
        changeButton.setForeground(Color.WHITE);
        changeButton.setBackground(Color.decode("#2E75B6"));
        c.add(changeButton);

        // Close Button
        closeButton.addActionListener(ae -> {
            setVisible(false);
            dispose();
        });

        // Change Button
        changeButton.addActionListener(ae -> {
            String newName = nameField.getText().toLowerCase();
            String newPassword = new String(passwordField.getPassword());

            if (newName.isEmpty() || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all of the fields.", "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    AuthService.updateAdminCredentials(newName, newPassword);
                    JOptionPane.showMessageDialog(null, "Admin Name and Password has been changed.",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to update admin credentials.",
                            "File Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminPassword().setVisible(true));
    }
}
