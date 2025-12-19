import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DomPlaces extends JFrame {

    private enum DomesticDestination {
        NONE,
        COXS_BAZAR,
        SAJEK_VALLEY,
        SREEMANGAL,
        BANDARBAN,
        RANGAMATI
    }

    private DomesticDestination selectedPlace = DomesticDestination.NONE;

    DomPlaces() {
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

        Font f1 = new Font("Segoe UI Black", Font.PLAIN, 35);
        Font f2 = new Font("Segoe UI Semibold", Font.PLAIN, 20);
        Font f3 = new Font("Segoe UI Black", Font.PLAIN, 25);

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        JLabel label1 = new JLabel();
        label1.setText("Which Place Would You");
        label1.setBounds(430, 25, 500, 50);
        label1.setFont(f1);
        c.add(label1);

        label1 = new JLabel();
        label1.setText("Like to Visit?");
        label1.setBounds(430, 60, 500, 50);
        label1.setFont(f1);
        c.add(label1);

        JRadioButton place1 = new JRadioButton("Cox's Bazar");
        place1.setBounds(460, 100, 200, 50);
        place1.setFont(f2);
        place1.setBackground(Color.decode("#F2F2F2"));
        place1.setCursor(cursor);
        c.add(place1);

        JRadioButton place2 = new JRadioButton("Sajek Valley");
        place2.setBounds(460, 140, 200, 50);
        place2.setFont(f2);
        place2.setBackground(Color.decode("#F2F2F2"));
        place2.setCursor(cursor);
        c.add(place2);

        JRadioButton place3 = new JRadioButton("Sreemangal");
        place3.setBounds(460, 180, 200, 50);
        place3.setFont(f2);
        place3.setBackground(Color.decode("#F2F2F2"));
        place3.setCursor(cursor);
        c.add(place3);

        JRadioButton place4 = new JRadioButton("Bandarban");
        place4.setBounds(460, 220, 200, 50);
        place4.setFont(f2);
        place4.setBackground(Color.decode("#F2F2F2"));
        place4.setCursor(cursor);
        c.add(place4);

        JRadioButton place5 = new JRadioButton("Rangamati");
        place5.setBounds(460, 260, 200, 50);
        place5.setFont(f2);
        place5.setBackground(Color.decode("#F2F2F2"));
        place5.setCursor(cursor);
        c.add(place5);

        ButtonGroup jButtonGroup = new ButtonGroup();
        jButtonGroup.add(place1);
        jButtonGroup.add(place2);
        jButtonGroup.add(place3);
        jButtonGroup.add(place4);
        jButtonGroup.add(place5);

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
        place1.addActionListener(handler);
        place2.addActionListener(handler);
        place3.addActionListener(handler);
        place4.addActionListener(handler);
        place5.addActionListener(handler);

        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                DefPackTypes frame = new DefPackTypes();
                frame.setVisible(true);
                setVisible(false);
            }
        });

        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Destination dest = null;
                switch (selectedPlace) {
                    case COXS_BAZAR:
                        dest = DestinationFactory.createCoxsBazar();
                        break;
                    case SAJEK_VALLEY:
                        dest = DestinationFactory.createSajekValley();
                        break;
                    case SREEMANGAL:
                        dest = DestinationFactory.createSreemangal();
                        break;
                    case BANDARBAN:
                        dest = DestinationFactory.createBandarban();
                        break;
                    case RANGAMATI:
                        dest = DestinationFactory.createRangamati();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Please select a place.", "Warning!", JOptionPane.WARNING_MESSAGE);
                        return;
                }
                setVisible(false);
                new PackageDetailsFrame(dest, DomPlaces.this).setVisible(true);
                dispose();
            }
        });
    }

    class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("Cox's Bazar".equals(command)) selectedPlace = DomesticDestination.COXS_BAZAR;
            else if ("Sajek Valley".equals(command)) selectedPlace = DomesticDestination.SAJEK_VALLEY;
            else if ("Sreemangal".equals(command)) selectedPlace = DomesticDestination.SREEMANGAL;
            else if ("Bandarban".equals(command)) selectedPlace = DomesticDestination.BANDARBAN;
            else if ("Rangamati".equals(command)) selectedPlace = DomesticDestination.RANGAMATI;
        }
    }

    public static void main(String[] args) {

        DomPlaces frame = new DomPlaces();
        frame.setVisible(true);
    }
}
