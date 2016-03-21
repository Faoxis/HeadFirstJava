package Chapter13.my;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sergei on 20.03.2016.
 */
public class List1 {

    String[] listEnties = {"alpha", "beta", "gama", "delta", "epsilon", "zeta", "eta", "theta"};

    JFrame frame;
    JButton button;
    JList list;
    JPanel panelForList;

    public static void main(String[] args) {
        List1 gui = new List1();
        gui.go();
    }

    public void go() {
        frame = new JFrame();

        JScrollPane scroller = new JScrollPane();
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        list = new JList(listEnties);

        panelForList = new JPanel();
        panelForList.add(list);
        panelForList.add(scroller);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panelForList, BorderLayout.CENTER);
        frame.setSize(300, 300);
        frame.setVisible(true);

    }


}
