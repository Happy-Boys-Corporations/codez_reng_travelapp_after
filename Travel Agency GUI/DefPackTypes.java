import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DefPackTypes extends JFrame {

    private final JRadioButton international;
    private final JRadioButton domestic;

    private enum TourType {
        NONE,
        INTERNATIONAL,
        DOMESTIC
    }

    private TourType selectedTourType = TourType.NONE;

    DefPackTypes() {
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
        label1.setText("Choose Tour Type");
        label1.setBounds(460, 70, 500, 50);
        label1.setFont(f1);
        c.add(label1);

        international = new JRadioButton("International");
        international.setBounds(480, 140, 300, 50);
        international.setFont(f2);
        international.setCursor(cursor);
        international.setBackground(Color.decode("#F2F2F2"));
        c.add(international);

        domestic = new JRadioButton("Domestic");
        domestic.setBounds(480, 200, 300, 50);
        domestic.setFont(f2);
        domestic.setCursor(cursor);
        domestic.setBackground(Color.decode("#F2F2F2"));
        c.add(domestic);

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(international);
        radioButtonGroup.add(domestic);

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
        international.addActionListener(handler);
        domestic.addActionListener(handler);

        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Packs frame = new Packs();
                frame.setVisible(true);
                setVisible(false);
            }
        });

        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (selectedTourType == TourType.INTERNATIONAL) {
                    IntCountries frame = new IntCountries();
                    frame.setVisible(true);
                    setVisible(false);
                    dispose();
                } else if (selectedTourType == TourType.DOMESTIC) {
                    DomPlaces frame = new DomPlaces();
                    frame.setVisible(true);
                    setVisible(false);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select tour type.", "Warning!",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == international) {
                selectedTourType = TourType.INTERNATIONAL;
            } else if (e.getSource() == domestic) {
                selectedTourType = TourType.DOMESTIC;
            }
        }
    }

    public static void main(String[] args) {

        DefPackTypes frame = new DefPackTypes();
        frame.setVisible(true);
    }
}