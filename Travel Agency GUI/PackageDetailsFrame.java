import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * A reusable frame to display package details for any destination.
 * This single class replaces the 10+ duplicated Domestic... and International... classes.
 */
public class PackageDetailsFrame extends JFrame {

    // --- UI Constants ---
    private static final Color BACKGROUND_COLOR = Color.decode("#F2F2F2");
    private static final Color BUTTON_COLOR = Color.decode("#2E75B6");
    private static final Color EXIT_BUTTON_COLOR = Color.decode("#C00000");
    private static final Font TITLE_FONT = new Font("Segoe UI Black", Font.PLAIN, 30);
    private static final Font RADIO_BUTTON_FONT = new Font("Segoe UI", Font.PLAIN, 25);
    private static final Font DETAIL_FONT = new Font("Segoe UI", Font.PLAIN, 20);
    private static final Font ACTION_BUTTON_FONT = new Font("Segoe UI Black", Font.PLAIN, 25);

    private enum PackageSelection {
        NONE, PACK_1, PACK_2, PACK_3
    }

    private PackageSelection selectedPackage = PackageSelection.NONE;

    /**
     * Constructs the details frame based on the provided Destination data.
     * @param destination The data object containing all info about the destination and its packages.
     * @param previousFrame The frame to return to when 'Back' is clicked.
     */
    public PackageDetailsFrame(Destination destination, JFrame previousFrame) {
        // --- Frame Setup ---
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Travel Agency - " + destination.getName());
        this.setSize(1000, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        Container c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(BACKGROUND_COLOR);

        // --- Common UI Elements ---
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Icon.png")));
        this.setIconImage(icon.getImage());

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/LogoBlue.png")));
        JLabel imgLabel = new JLabel(logo);
        imgLabel.setBounds(30, 82, logo.getIconWidth(), logo.getIconHeight());
        c.add(imgLabel);

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        // --- Dynamic Title ---
        JLabel titleLabel = new JLabel("3 Packs Available for " + destination.getName());
        titleLabel.setBounds(430, 35, 600, 50);
        titleLabel.setFont(TITLE_FONT);
        c.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Tour! Select One:");
        subtitleLabel.setBounds(430, 70, 500, 50);
        subtitleLabel.setFont(TITLE_FONT);
        c.add(subtitleLabel);

        // --- Dynamic Package Details ---
        ButtonGroup radioButtonGroup = new ButtonGroup();
        List<PackageInfo> packages = destination.getPackages();

        // We assume there are always 3 packages.
        // The starting X coordinates for each package column.
        int[] xCoordinates = {430, 610, 790};

        for (int i = 0; i < packages.size(); i++) {
            PackageInfo pkg = packages.get(i);
            int x = xCoordinates[i];

            // Radio Button
            JRadioButton packRadio = new JRadioButton(pkg.getName());
            packRadio.setBounds(x, 120, 150, 50); // Increased width for longer names
            packRadio.setFont(RADIO_BUTTON_FONT);
            packRadio.setBackground(BACKGROUND_COLOR);
            packRadio.setCursor(cursor);
            c.add(packRadio);
            radioButtonGroup.add(packRadio);

            // Add action listener to update selection
            final int packageIndex = i;
            packRadio.addActionListener(e -> selectedPackage = PackageSelection.values()[packageIndex + 1]);

            // Feature Labels
            int y = 150;
            for (String feature : pkg.getFeatures()) {
                addDetailLabel(c, feature, x, y);
                y += 30; // Increment y for the next label
            }

            // Cost Label
            String costText = "* Cost : " + destination.getCurrencySymbol() + pkg.getCost();
            addDetailLabel(c, costText, x, y);
        }

        // --- Action Buttons ---
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(148, 375, 215, 50);
        exitButton.setFont(ACTION_BUTTON_FONT);
        exitButton.setCursor(cursor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(EXIT_BUTTON_COLOR);
        c.add(exitButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(384, 375, 215, 50);
        backButton.setFont(ACTION_BUTTON_FONT);
        backButton.setCursor(cursor);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(BUTTON_COLOR);
        c.add(backButton);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(617, 375, 215, 50);
        nextButton.setFont(ACTION_BUTTON_FONT);
        nextButton.setCursor(cursor);
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(BUTTON_COLOR);
        c.add(nextButton);

        // --- Button Actions ---
        exitButton.addActionListener(ae -> System.exit(0));

        backButton.addActionListener(ae -> {
            setVisible(false);
            previousFrame.setVisible(true);
            dispose();
        });

        nextButton.addActionListener(ae -> {
            if (selectedPackage == PackageSelection.NONE) {
                JOptionPane.showMessageDialog(this, "You did not select any package.", "Warning!", JOptionPane.WARNING_MESSAGE);
            } else {
                setVisible(false);
                new Payment().setVisible(true); // Assuming Payment is the next step for all
                dispose();
            }
        });
    }

    private void addDetailLabel(Container container, String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 520, 50);
        label.setFont(DETAIL_FONT);
        container.add(label);
    }
}
