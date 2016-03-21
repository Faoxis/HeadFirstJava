package Chapter13.my;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Sergei on 20.03.2016.
 */
public class CheckBox1 {

    JFrame frame;
    JButton button;
    JCheckBox checkBox;
    boolean wasCheckPush = false;

    public static void main(String[] args) {
        CheckBox1 gui = new CheckBox1();
        gui.go();
    }

    public void go() {
        frame = new JFrame();

        button = new JButton("Check");
        button.addActionListener(new ButtonListener());
        frame.getContentPane().add(button, BorderLayout.SOUTH);

        checkBox = new JCheckBox("ChcheckBox");
        checkBox.addItemListener(new CheckBoxListener());
        frame.getContentPane().add(checkBox, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public class ButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (checkBox.isSelected()) {
                System.out.println("Selected");
            } else {
                System.out.println("Not selected");
            }
        }
    }

    public class CheckBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            System.out.println("The button has been chenged");
        }
    }

}
