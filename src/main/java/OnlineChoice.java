import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class OnlineChoice extends JFrame {

    private final JButton hostButton = new JButton("ХОСТ");
    private final JButton clientButton = new JButton("КЛИЕНТ");

    public OnlineChoice() {
        super("Спички");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(375, 200);
        setVisible(true);
        setLocationRelativeTo (null);
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
        hostButton.setLocation(50, 50);
        hostButton.setSize(100, 50);
        hostButton.setBackground(new Color(0x6DC911));
        hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter("saves\\totalQuantity.txt");
                    FileWriter writer2 = new FileWriter("saves\\queue.txt");
                    writer2.write("1");
                    writer2.flush();
                    writer.write("100");
                    writer.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                new HostOnlineGame();
                OnlineChoice.super.setVisible(false);
            }
        });

        hostButton.setActionCommand("Open");
        menu.add(hostButton);
        clientButton.setLocation(200, 50);
        clientButton.setSize(100, 50);
        clientButton.setBackground(new Color(0x157AA1));
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientOnlineGame();
                OnlineChoice.super.setVisible(false);

            }
        });

        clientButton.setActionCommand("Open");
        menu.add(clientButton);

        menu.setOpaque(true);
        return menu;
    }

}