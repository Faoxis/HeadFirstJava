package Chapter14.myquizcard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Sergei on 25.03.2016.
 */
public class QuizCardReader {

    private JTextArea display;
    private JTextArea answer;
    private ArrayList cardList;
    private QuizCard currentCard;
    private JFrame frame;
    JButton nextButton;
    private boolean isAnswer;

    public static void main(String[] args) {
        QuizCardReader gui = new QuizCardReader();
        gui.go();
    }

    public void go() {
        frame = new JFrame("Card Reader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();

        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        display = new JTextArea(9, 20);
        display.setFont(bigFont); // метод установки шрифта
        display.setLineWrap(true); // переносит текст на следующую строку, если он дошел до края
        display.setWrapStyleWord(true); // Перенос слова на новую строку, если он не вмещается в линию
        display.setEnabled(false); // запрет на изменение элемента

        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        nextButton = new JButton("Show Question");

        mainPanel.add(qScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem loadMenuItem = new JMenuItem("Load card set");
        loadMenuItem.addActionListener(new OpenMenuListener());

        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 500);
        frame.setVisible(true);

    }

    class NextCardListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (isAnswer) {
                display.setText(currentCard.getAnswer());
                nextButton.setText("Next card");
                isAnswer = false;
            } else {
//                if (currentCardIndex < cardList.size()) {
//                    showNextcard();
//                } else {
//                    display.setText("That was last card");
//                    nextButton.setEnabled(false);
//                }
            }
        }
    }

    class OpenMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

        }
    }

}
