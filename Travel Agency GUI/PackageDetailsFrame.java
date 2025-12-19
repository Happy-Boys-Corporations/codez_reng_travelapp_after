import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class PackageDetailsFrame extends JFrame {

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

    public PackageDetailsFrame(Destination destination, JFrame previousFrame) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Travel Agency - " + destination.getName());
        this.setSize(1000, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        Container c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(BACKGROUND_COLOR);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Icon.png")));
        this.setIconImage(icon.getImage());

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/LogoBlue.png")));
        JLabel imgLabel = new JLabel(logo);
        imgLabel.setBounds(30, 82, logo.getIconWidth(), logo.getIconHeight());
        c.add(imgLabel);

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        JLabel titleLabel = new JLabel("3 Packs Available for " + destination.getName());
        titleLabel.setBounds(430, 35, 600, 50);
        titleLabel.setFont(TITLE_FONT);
        c.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Tour! Select One:");
        subtitleLabel.setBounds(430, 70, 500, 50);
        subtitleLabel.setFont(TITLE_FONT);
        c.add(subtitleLabel);

        ButtonGroup radioButtonGroup = new ButtonGroup();
        List<PackageInfo> packages = destination.getPackages();

        int[] xCoordinates = {430, 610, 790};

        for (int i = 0; i < packages.size(); i++) {
            PackageInfo pkg = packages.get(i);
            int x = xCoordinates[i];

            JRadioButton packRadio = new JRadioButton(pkg.getName());
            packRadio.setBounds(x, 120, 150, 50);
            packRadio.setFont(RADIO_BUTTON_FONT);
            packRadio.setBackground(BACKGROUND_COLOR);
            packRadio.setCursor(cursor);
            c.add(packRadio);
            radioButtonGroup.add(packRadio);

            final int packageIndex = i;
            packRadio.addActionListener(e -> selectedPackage = PackageSelection.values()[packageIndex + 1]);

            int y = 150;
            for (String feature : pkg.getFeatures()) {
                addDetailLabel(c, feature, x, y);
                y += 30;
            }

            String costText = "* Cost : " + destination.getCurrencySymbol() + pkg.getCost();
            addDetailLabel(c, costText, x, y);
        }

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
                new Payment().setVisible(true);
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
