package Chapter13.my;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Sergei on 18.03.2016.
 */
public class Graphic {

    JFrame frame;
    JButton button;
    JTextArea textArea;
    GraphPanel graphPanel;
    GraphCoord coords;

    public static void main(String[] args) {
        Graphic gui = new Graphic();
        gui.go();
    }

    public void go() {
        coords = new GraphCoord(10, 600);
        frame = new JFrame("Графичек");

        button = new JButton("Построить график");
        button.addActionListener(new GraphBuilder());
        frame.getContentPane().add(button, BorderLayout.SOUTH);

        JScrollPane scroller = new JScrollPane();
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        textArea = new JTextArea(1, 20);
        textArea.add(scroller);
        frame.getContentPane().add(textArea, BorderLayout.EAST);

        graphPanel = new GraphPanel();
        frame.getContentPane().add(graphPanel, BorderLayout.CENTER);


        frame.setSize(800, 800);
        frame.setVisible(true);

    }


    class GraphBuilder implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String str = textArea.getText();
            String[] test = str.split(" ");
            int[] numbs = new int[test.length];

            for (int i = 0; i < test.length; ++i) {
                numbs[i] = Integer.parseInt(test[i]);
                System.out.print(test[i] + " ");
            }
            System.out.println();

            coords.setX(10);
            int abs = numbs.length;

            for (int i = 0; i < numbs.length; ++i) {
                coords.setX(coords.getX() + 1);
                coords.setY(800 - numbs[i]);
                graphPanel.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }


    class GraphPanel extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            //g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Color.black);
            g.fillOval(coords.getX(), coords.getY(), 10, 10);
        }
    }

    class GraphCoord {
        private int x;
        private int y;

        GraphCoord() {
            x = 0;
            y = 0;
        }

        GraphCoord(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }


}
