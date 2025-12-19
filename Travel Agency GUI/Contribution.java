import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Contribution extends JFrame {

    private final Container c;
    private final ImageIcon icon;
    private final Font f1;
    private final JButton btn1;
    private final Cursor cursor;

    Contribution() {
        this.setTitle("Travel Agency");
        this.setSize(540, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.decode("#F2F2F2"));

        icon = new ImageIcon(getClass().getResource("/images/Icon.png"));
        this.setIconImage(icon.getImage());

        ImageIcon logo = new ImageIcon(getClass().getResource("/images/Contribution.png"));
        JLabel imgLabel = new JLabel(logo);
        imgLabel.setBounds(45, 20, logo.getIconWidth(), logo.getIconHeight());
        c.add(imgLabel);
        
        f1 = new Font("Segoe UI Black", Font.BOLD, 25);

        cursor = new Cursor(Cursor.HAND_CURSOR);

        btn1 = new JButton("OK");
        btn1.setBounds(175, 580, 180, 50);
        btn1.setFont(f1);
        btn1.setCursor(cursor);
        btn1.setForeground(Color.WHITE);
        btn1.setBackground(Color.decode("#2E75B6"));
        c.add(btn1);

        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {

        Contribution frame = new Contribution();
        frame.setVisible(true);
    }
}
