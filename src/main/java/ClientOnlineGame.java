import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

public class ClientOnlineGame extends JFrame {
    JButton updateButton = new JButton("Обновить");
    JTextField quantity = new JTextField();
    JTextField totalQuantity = new JTextField();
    public String port = "4321";
    public String ip = "192.168.0.5";
    int queue = 0;

    public ClientOnlineGame() {
        super("Спички");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 600);
        try {
            setContentPane(panel());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    public JPanel panel() throws URISyntaxException {
        JPanel menu = new JPanel();
        menu.setLayout(null);

        quantity.setLocation(50, 120);
        quantity.setSize(100, 80);
        quantity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (queue % 2 == 0) {
                    String str = quantity.getText();
                    if (Integer.parseInt(str) < 10 || Integer.parseInt(str) > 20) {
                        JOptionPane.showMessageDialog(new JDialog(), "Введите корректно число спичек");
                    } else {
                        String q = "";
                        try (FileReader fr = new FileReader("saved\\totalQuantity.txt")) {
                            BufferedReader reader = new BufferedReader(fr);
                            q = String.valueOf(Integer.parseInt(reader.readLine()) - Integer.parseInt(str));
                            if(Integer.parseInt(q)<10){
                                JOptionPane.showMessageDialog(new JDialog(), "Вы выиграли)");
                            }
                            totalQuantity.setText(q);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                        try {
                            FileWriter writer = new FileWriter("saved\\totalQuantity.txt");
                            writer.write(q);
                            writer.flush();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try {
                            FileWriter writer = new FileWriter("saved\\queue.txt");
                            writer.write(String.valueOf(queue+1));
                            writer.flush();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(new JDialog(), "Не ваш ход!");
                }
            }
        });

        totalQuantity.setLocation(50, 20);
        totalQuantity.setSize(100, 80);
        totalQuantity.setEnabled(false);

        updateButton.setLocation(50, 300);
        updateButton.setSize(200, 50);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URL url = new URL("http://" + ip + ":" + port + "/queue.txt");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

                    File f1 = new File("saved\\queue.txt");
                    FileOutputStream fw = new FileOutputStream(f1);

                    byte[] b = new byte[1024];
                    int count = 0;

                    while ((count = bis.read(b)) != -1)
                        fw.write(b, 0, count);

                    fw.close();
                } catch (IOException ex) {
                }
                try (FileReader fr = new FileReader("saved\\queue.txt")) {
                    BufferedReader reader = new BufferedReader(fr);
                    queue = Integer.parseInt(reader.readLine());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                try {
                    URL url = new URL("http://" + ip + ":" + port + "/totalQuantity.txt");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

                    File f1 = new File("saved\\totalQuantity.txt");
                    FileOutputStream fw = new FileOutputStream(f1);

                    byte[] b = new byte[1024];
                    int count = 0;

                    while ((count = bis.read(b)) != -1)
                        fw.write(b, 0, count);

                    fw.close();
                } catch (IOException ex) {
                }
                String q;
                try (FileReader fr = new FileReader("saved\\totalQuantity.txt")) {
                    BufferedReader reader = new BufferedReader(fr);
                    q = reader.readLine();
                    totalQuantity.setText(q);
                    if (Integer.parseInt(q) < 10) {
                        JOptionPane.showMessageDialog(new JDialog(), "Вы проиграли(");
                        ClientOnlineGame.super.setVisible(false);
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        quantity.setActionCommand("Open");
        menu.add(quantity);
        totalQuantity.setActionCommand("Open");
        menu.add(totalQuantity);
        updateButton.setActionCommand("Open");
        menu.add(updateButton);
        updateButton.setActionCommand("Open");
        menu.add(updateButton);

        menu.setOpaque(true);
        return menu;
    }
}