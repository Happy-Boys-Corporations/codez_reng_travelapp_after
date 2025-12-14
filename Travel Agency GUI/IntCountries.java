import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class IntCountries extends JFrame {

    private enum InternationalDestination {
        NONE,
        FRANCE,
        ITALY,
        GREECE,
        SOUTH_AFRICA,
        INDONESIA
    }

    private InternationalDestination selectedCountry = InternationalDestination.NONE;

    IntCountries() {
        // Frame Layout
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

        // Logo
        ImageIcon logo = new ImageIcon(getClass().getResource("/images/LogoBlue.png"));
        JLabel imgLabel = new JLabel(logo);
        imgLabel.setBounds(30, 50, logo.getIconWidth(), logo.getIconHeight());
        c.add(imgLabel);

        Font f1 = new Font("Segoe UI Black", Font.PLAIN, 35);
        Font f2 = new Font("Segoe UI Semibold", Font.PLAIN, 20);
        Font f3 = new Font("Segoe UI Black", Font.PLAIN, 25);

        // Cursor for JButtons and Radio Buttons
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        // Title
        JLabel label1 = new JLabel();
        label1.setText("Which Country Would");
        label1.setBounds(430, 25, 500, 50);
        label1.setFont(f1);
        c.add(label1);
        
        label1 = new JLabel();
        label1.setText("You Like to Visit?");
        label1.setBounds(430, 60, 500, 50);
        label1.setFont(f1);
        c.add(label1);

        // Country JButtons
        JRadioButton country1 = new JRadioButton("France");
        country1.setBounds(460, 100, 100, 50);
        country1.setFont(f2);
        country1.setBackground(Color.decode("#F2F2F2"));
        country1.setCursor(cursor);
        c.add(country1);

        JRadioButton country2 = new JRadioButton("Italy");
        country2.setBounds(460, 140, 200, 50);
        country2.setFont(f2);
        country2.setBackground(Color.decode("#F2F2F2"));
        country2.setCursor(cursor);
        c.add(country2);

        JRadioButton country3 = new JRadioButton("Greece");
        country3.setBounds(460, 180, 200, 50);
        country3.setFont(f2);
        country3.setBackground(Color.decode("#F2F2F2"));
        country3.setCursor(cursor);
        c.add(country3);

        JRadioButton country4 = new JRadioButton("South Africa");
        country4.setBounds(460, 220, 200, 50);
        country4.setFont(f2);
        country4.setBackground(Color.decode("#F2F2F2"));
        country4.setCursor(cursor);
        c.add(country4);

        JRadioButton country5 = new JRadioButton("Indonesia");
        country5.setBounds(460, 260, 200, 50);
        country5.setFont(f2);
        country5.setBackground(Color.decode("#F2F2F2"));
        country5.setCursor(cursor);
        c.add(country5);

        // To Group JButtons
        ButtonGroup jButtonGroup = new ButtonGroup();
        jButtonGroup.add(country1);
        jButtonGroup.add(country2);
        jButtonGroup.add(country3);
        jButtonGroup.add(country4);
        jButtonGroup.add(country5);

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
        country1.addActionListener(handler);
        country2.addActionListener(handler);
        country3.addActionListener(handler);
        country4.addActionListener(handler);
        country5.addActionListener(handler);

        // Action Listener for JButtons
        // Exit Button
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        // Back Button
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                DefPackTypes frame = new DefPackTypes();
                frame.setVisible(true);
                setVisible(false);
            }
        });

        // Next Button
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Destination dest = null;
                switch (selectedCountry) {
                    case FRANCE:
                        dest = DestinationFactory.createFrance();
                        break;
                    case ITALY:
                        dest = DestinationFactory.createItaly();
                        break;
                    case GREECE:
                        dest = DestinationFactory.createGreece();
                        break;
                    case SOUTH_AFRICA:
                        dest = DestinationFactory.createSouthAfrica();
                        break;
                    case INDONESIA:
                        dest = DestinationFactory.createIndonesia();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Please select a country.", "Warning!", JOptionPane.WARNING_MESSAGE);
                        return;
                }
                setVisible(false);
                new PackageDetailsFrame(dest, IntCountries.this).setVisible(true);
                dispose();
            }
        });
    }

    class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("France".equals(command)) selectedCountry = InternationalDestination.FRANCE;
            else if ("Italy".equals(command)) selectedCountry = InternationalDestination.ITALY;
            else if ("Greece".equals(command)) selectedCountry = InternationalDestination.GREECE;
            else if ("South Africa".equals(command)) selectedCountry = InternationalDestination.SOUTH_AFRICA;
            else if ("Indonesia".equals(command)) selectedCountry = InternationalDestination.INDONESIA;
        }
    }

    public static void main(String[] args) {

        IntCountries frame = new IntCountries();
        frame.setVisible(true);
    }
}
