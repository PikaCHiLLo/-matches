import javax.swing.*;
import java.awt.*;

public class Win extends JDialog {
    Label label=new Label("Win!!!");
    public Win(){
        super();
        setSize(500, 600);
        setLocationRelativeTo(null);
        JPanel menu = new JPanel();
        menu.setLayout(null);
        menu.add(label);
        menu.setOpaque(true);
    }
}
