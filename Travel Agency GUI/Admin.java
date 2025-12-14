import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class Admin extends JFrame {

    private static final Color BACKGROUND_COLOR = Color.decode("#F2F2F2");
    private static final Color BUTTON_COLOR = Color.decode("#2E75B6");
    private static final Color EXIT_BUTTON_COLOR = Color.decode("#C00000");
    private static final Font TITLE_FONT = new Font("Tahoma", Font.BOLD, 60);
    private static final Font BUTTON_FONT = new Font("Segoe UI Black", Font.PLAIN, 25);

    Admin() {
        setupFrame();
        initComponents();
        layoutComponents();
        addListeners();
    }

    private void setupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Travel Agency");
        this.setSize(650, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Icon.png")));
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(BACKGROUND_COLOR);
    }

    private void initComponents() {
        // Cursor for JButtons
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        // JButtons
        userDataButton = new JButton("User Data");
        userDataButton.setFont(BUTTON_FONT);
        userDataButton.setCursor(cursor);
        userDataButton.setForeground(Color.WHITE);
        userDataButton.setBackground(BUTTON_COLOR);

        adminPasswordButton = new JButton("Admin Password");
        adminPasswordButton.setFont(BUTTON_FONT);
        adminPasswordButton.setCursor(cursor);
        adminPasswordButton.setForeground(Color.WHITE);
        adminPasswordButton.setBackground(BUTTON_COLOR);

        exitButton = new JButton("Exit");
        exitButton.setFont(BUTTON_FONT);
        exitButton.setCursor(cursor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(EXIT_BUTTON_COLOR);

        backButton = new JButton("Back");
        backButton.setFont(BUTTON_FONT);
        backButton.setCursor(cursor);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(BUTTON_COLOR);
    }

    private void layoutComponents() {
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Insets for padding
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Logo
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Admin.png")));
        JLabel imgLabel = new JLabel(logo);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        c.add(imgLabel);

        // Title
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Admin Panel");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        c.add(titleLabel, gbc);

        // User Data Button
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        c.add(userDataButton, gbc);

        // Admin Password Button
        gbc.gridx = 1;
        c.add(adminPasswordButton, gbc);

        // Exit Button
        gbc.gridy = 3;
        gbc.gridx = 0;
        c.add(exitButton, gbc);

        // Back Button
        gbc.gridx = 1;
        c.add(backButton, gbc);
    }

    private void addListeners() {
        // User Data
        userDataButton.addActionListener(ae -> {
            new UserData().setVisible(true);
            setVisible(false);
            dispose();
        });

        exitButton.addActionListener(ae -> System.exit(0));

        backButton.addActionListener(ae -> {
            new Home().setVisible(true);
            setVisible(false);
            dispose();
        });

        adminPasswordButton.addActionListener(ae -> new AdminPassword().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Admin().setVisible(true));
    }
}
