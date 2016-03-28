package Chapter14.quizcard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sergei on 05.02.2016.
 */
public class QuizCardBuilder {

    private JTextArea question;
    private JTextArea answer;
    private ArrayList cardList;
    private JFrame frame;

    public static void main (String[] args) {
        QuizCardBuilder builder = new QuizCardBuilder();
        builder.go();
    }



    // Формируем и выводим на экран GUI, включая создание и регистрацию слушателей для событий
    public void go() {
        // Формируем и выводим на экран GUI

        frame = new JFrame("Quiz Card Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);

        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);

        JScrollPane aScroller = new JScrollPane(answer);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton = new JButton("Next Card");
        cardList = new ArrayList<QuizCard>();
        JLabel  qLabel = new JLabel("Question:");
        JLabel aLabel = new JLabel("Answer:");

        mainPanel.add(qLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(aScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());

        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

    // Срабатывает при нажатии пользователем кнопки Next Card
    private class NextCardListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Добавляем текущую карточку в список и очищаем текстовые области
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();
        }
    }

    // Запускается при выборе команды Save (Сохранить) из меню File (Файл). Означает, что пользователь хочет сохранить
    // все карточки в текущем списке в виде набора
    private class SaveMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            // Вызываем диалоговое окно, позволяющее пользователю называть и сохранять набор
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);

            // Вызывается диалоговое окно и программа останавливается на этой строке, пока пользователь не выберет меню Save.
            // Всю навигацию, выбор файла и т.д. выполняет класс JFileChooser.
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    // Запускается при выборе команды New (Создать) из меню File (Файл).
    // Означает, что пользователь хочет создать новый набор (очистив список карточек и текстовое поле )
    private class NewMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            // Очищаем список карточек и текстовые области
            cardList.clear();
            clearCard();
        }
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    // Вызывается классом SaveMenuListener. Непосредственно записывает данные в файл
    private void saveFile(File file) {
        // Проходим по списку карточек и записываем каждый элемент в текстовый файл,
        // который потом можно будет прочитать (то есть с чистыми разделителями между частями)

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            Iterator cardIterator = cardList.iterator();
            while (cardIterator.hasNext()) {
                QuizCard card = (QuizCard) cardIterator.next();
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
            writer.close();

        } catch (IOException ex) {
            System.out.println("couldn't write the cardList out");
            ex.printStackTrace();
        }
    }

}
