package Chapter13;

/**
 * Created by Sergei on 17.03.2016.
 */

import javax.swing.*;
import java.awt.*; // Здесь находится BorderLayout

public class Button1 {

    public static void main(String[] args) {
        Button1 gui = new Button1();
        gui.go();
    }

    //------------------------ Версия метода go() номер 1 ------------------------//
    /*
    public void go() {
        JFrame frame = new JFrame();
        JButton button = new JButton("click me like you mean it");
        Font bitFont = new Font("serif", Font.BOLD, 28);
        button.setFont(bitFont);
        frame.getContentPane().add(BorderLayout.NORTH, button);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
    */

    //------------------------ Версия метода go() номер 2 ------------------------//
    public void go() {
        JFrame frame = new JFrame();

        JButton east = new JButton("East");
        JButton west = new JButton("West");
        JButton north = new JButton("North");
        JButton south = new JButton("South");
        JButton center = new JButton("Center");

        frame.getContentPane().add(BorderLayout.EAST, east);
        frame.getContentPane().add(BorderLayout.WEST, west);
        frame.getContentPane().add(BorderLayout.NORTH, north);
        frame.getContentPane().add(BorderLayout.SOUTH, south);
        frame.getContentPane().add(BorderLayout.CENTER, center);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
