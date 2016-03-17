package Lesson13;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sergei on 03.02.2016.
 */
public class Panel1 {

    public static void main (String[] args) {
        Panel1 gui = new Panel1();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);


        // Изменение диспечера компоновки на новый экземпляр BoxLaout
        // Конктруктору диспетчера нужно знать какие компоненты он размещает (panel)
        // и какую ось использовать (BoxLayout.Y_AXIS).
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton button1 = new JButton("shock me");
        panel.add(button1);

        JButton button2 = new JButton("bliss");
        panel.add(button2);


        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(250, 200);
        frame.setVisible(true);
    }

}
