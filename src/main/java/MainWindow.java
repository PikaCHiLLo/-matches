import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

public class MainWindow extends JFrame {
    JButton button = new JButton("Начать игру!");

    public MainWindow() {
        super("Спички");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(375, 200);
        setLocationRelativeTo (null);
        setVisible(true);
        try {
            setContentPane(panelChoice());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    public JPanel panelChoice() throws URISyntaxException {
        JPanel menu = new JPanel();
        menu.setLayout(null);

        button.setLocation(50, 50);
        button.setSize(200, 50);
        button.setBackground(new Color(0x6DC911));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OnlineChoice();
                MainWindow.super.setVisible(false);
            }
        });

        button.setActionCommand("Open");
        menu.add(button);

        menu.setOpaque(true);
        return menu;
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}