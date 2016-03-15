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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton button1 = new JButton("Button 1");
        panel.add(button1);

        JButton button2 = new JButton("Button 2");
        panel.add(button2);


        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }

}
