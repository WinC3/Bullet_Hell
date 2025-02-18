import javax.swing.*;
import java.awt.*;

public class BoxLayoutExample extends JFrame {
    public BoxLayoutExample() {
        setTitle("BoxLayout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Create a panel with BoxLayout (vertical)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add buttons with spacing
        panel.add(Box.createVerticalStrut(10)); // Spacing
        panel.add(new JButton("Button 1"));
        panel.add(Box.createVerticalStrut(10)); // Spacing
        panel.add(new JButton("Button 2"));
        panel.add(Box.createVerticalStrut(10)); // Spacing
        panel.add(new JButton("Button 3"));

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BoxLayoutExample::new);
    }
}
