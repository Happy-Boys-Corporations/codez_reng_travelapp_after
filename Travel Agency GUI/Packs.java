import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Packs extends JFrame {

    private final JRadioButton defPacks;
    private final JRadioButton selfChosenPacks;

    private enum PackageType {
        NONE,
        DEFAULT,
        SELF_CHOSEN
    }

    private PackageType selectedPackageType = PackageType.NONE;

    Packs() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Travel Agency");
        this.setSize(900, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        Container c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.decode("#F2F2F2"));

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/Icon.png"));
        this.setIconImage(icon.getImage());

        ImageIcon logo = new ImageIcon(getClass().getResource("/images/LogoBlue.png"));
        JLabel imgLabel = new JLabel(logo);
        imgLabel.setBounds(30, 50, logo.getIconWidth(), logo.getIconHeight());
        c.add(imgLabel);

        Font f1 = new Font("Segoe UI Black", Font.PLAIN, 40);
        Font f2 = new Font("Segoe UI Semibold", Font.PLAIN, 30);
        Font f3 = new Font("Segoe UI Black", Font.PLAIN, 25);

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        JLabel label1 = new JLabel();
        label1.setText("Choose Your Package");
        label1.setBounds(425, 40, 500, 50);
        label1.setFont(f1);
        c.add(label1);

        label1 = new JLabel();
        label1.setText("Type");
        label1.setBounds(425, 90, 500, 50);
        label1.setFont(f1);
        c.add(label1);

        defPacks = new JRadioButton("Default Packs");
        defPacks.setBounds(445, 160, 300, 50);
        defPacks.setFont(f2);
        defPacks.setCursor(cursor);
        defPacks.setBackground(Color.decode("#F2F2F2"));
        c.add(defPacks);

        selfChosenPacks = new JRadioButton("Self-Chosen Packs");
        selfChosenPacks.setBounds(445, 220, 300, 50);
        selfChosenPacks.setFont(f2);
        selfChosenPacks.setCursor(cursor);
        selfChosenPacks.setBackground(Color.decode("#F2F2F2"));
        c.add(selfChosenPacks);

        ButtonGroup jButtonGroup = new ButtonGroup();
        jButtonGroup.add(defPacks);
        jButtonGroup.add(selfChosenPacks);

        JButton btn1 = new JButton("Exit");
        btn1.setBounds(90, 325, 215, 50);
        btn1.setFont(f3);
        btn1.setCursor(cursor);
        btn1.setForeground(Color.WHITE);
        btn1.setBackground(Color.decode("#C00000"));
        c.add(btn1);

        JButton btn2 = new JButton("Back");
        btn2.setBounds(340, 325, 215, 50);
        btn2.setFont(f3);
        btn2.setCursor(cursor);
        btn2.setForeground(Color.WHITE);
        btn2.setBackground(Color.decode("#2E75B6"));
        c.add(btn2);

        JButton btn3 = new JButton("Next");
        btn3.setBounds(590, 325, 215, 50);
        btn3.setFont(f3);
        btn3.setCursor(cursor);
        btn3.setForeground(Color.WHITE);
        btn3.setBackground(Color.decode("#2E75B6"));
        c.add(btn3);

        ActionListener handler = new Handler();
        defPacks.addActionListener(handler);
        selfChosenPacks.addActionListener(handler);

        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                Login frame = new Login();
                frame.setVisible(true);
            }
        });

        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (selectedPackageType == PackageType.DEFAULT) {
                    setVisible(false);
                    DefPackTypes frame = new DefPackTypes();
                    frame.setVisible(true);
                } else if (selectedPackageType == PackageType.SELF_CHOSEN) {
                    setVisible(false);
                    SelfChoosenPacks frame = new SelfChoosenPacks();
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "You forgot to select package type :(", "Warming!!!",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == defPacks) {
                selectedPackageType = PackageType.DEFAULT;
            } else if (e.getSource() == selfChosenPacks) {
                selectedPackageType = PackageType.SELF_CHOSEN;
            }
        }
    }

    public static void main(String[] args) {

        Packs frame = new Packs();
        frame.setVisible(true);
    }
}