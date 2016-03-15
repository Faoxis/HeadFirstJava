package Chapter12.my;

import Chapter12.MyDrawPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;

/**
 * Created by Sergei on 15.03.2016.
 */

public class Acceleration {

    JFrame frame;

    JButton button1;
    JButton button2;

    MyDrawPanel myPanel;
    JPanel panel;

    Coord obj1;
    Coord obj2;

    private double v;
    private double a;

    private boolean start;

    public static void main(String[] args) {
        Acceleration gui = new Acceleration();
        gui.go();
    }

    public void go() {

        myPanel = new MyDrawPanel();

        button1 = new JButton("Start!");
        button1.addActionListener(new StartButton());

        button2 = new JButton("Stop!");
        button2.addActionListener(new StopButton());

        panel = new JPanel();
        panel.add(button1);
        panel.add(button2);

        frame = new JFrame("WOW! This is real physics!");
        frame.setSize(900, 950);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myPanel);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);

        v = 0.001;
        a = 0.001;

        obj1 = new Coord(50, 50);
        obj2 = new Coord(800, 800);

        while (true) {

            if (obj1.y < 800 && obj2.y > 50) {
                if (start) {
                    obj1.y = (int) (obj1.y + v * Coord.t + a * Math.pow(Coord.t, 2) / 2.0);
                    obj2.y = (int) (obj2.y + v * Coord.t - a * Math.pow(Coord.t, 2) / 2.0);
                    myPanel.repaint();
                    Coord.t++;
                }
            }

            try {
                Thread.sleep(100);
            } catch (Exception ex) {
            }
        }
    }


    class MyDrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Color.black);
            g.fillOval(obj1.x, obj1.y, 20, 20);

            g.setColor(Color.green);
            g.fillOval(obj2.x, obj2.y, 20, 20);
        }
    }

    class StartButton implements ActionListener {
        public void actionPerformed (ActionEvent event) {
                start = true;
        }
    }

    class StopButton implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            start = false;

            obj1.y = 50;
            obj2.y = 800;
            Coord.t = 0;

            myPanel.repaint();
        }
    }


    private static class Coord {
        int x;
        int y;

        public static int t;

        {
            t = 0;
        }

        Coord () {
            x = 0;
            y = 0;
        }

        Coord (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
