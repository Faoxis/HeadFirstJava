package Chapter14.quizcard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by Sergei on 05.02.2016.
 */

public class QuizCardPlayer {

    private JTextArea display;
    private JTextArea answer;
    private ArrayList cardList;
    private QuizCard currentCard;
    private int currentCardIndex;
    private Iterator cardIterator;
    private JFrame frame;
    private JButton nextButton;
    private boolean isShowAnswer;

    public static void main (String[] args) {
        QuizCardPlayer reader = new QuizCardPlayer();
        reader.go();
    }

    // Сформируем и выведем на экран GUI
    public void go() {
        frame = new JFrame("Quiz Card Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        display = new JTextArea(9, 20);
        display.setFont(bigFont);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        display.setEditable(false);

        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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
        frame.setSize(500, 600);
        frame.setVisible(true);

    } // Закрытие функции go

    class NextCardListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            // Если этот вопрос, показыаем ответ, иначе показываем слудющий вопрос
            // Установим флаг для того, что мы видим, - вопрос это или ответ
            if (isShowAnswer) {
                // Показываем ответ, так как вопрос уже был увиден
                display.setText(currentCard.getAnswer());
                nextButton.setText("Next Card");
                isShowAnswer = false;
            } else {
                // Показывать следующий вопрос
                if (currentCardIndex < cardList.size()) {
                    showNextCard();
                } else {
                    // Больше карточек нет!
                    display.setText("That was last card");
                    nextButton.setEnabled(false);
                }
            }
        }
    }


    class OpenMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            // Вызываем диалоговое окно, позволяющее пользователю выбирать, какой набор карточек открыть
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }

    private void loadFile(File file) {
        // Нужно создать ArrayList с карточками, считывая их в из текстового файла,
        // вызванного из обработчика событий класса OpenMenuListener,
        // прочитать файл по одной строке за один раз и вызвать метод makeCard() для создания новой карточки из строки
        // (одна строка в файле содержит вопрос и ответ, разделенные символом /)
        cardList = new ArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;

            // Создаем BufferedReader, связанный с новым FileReader.
            // Предоставляем объекту FileReader бъект File, выбранный пользователем в окне открытия файла.

            // Читаем по одной строке за один раз передавая результат в метод makeCard(),
            // который разделяет его и преобразует в настоящий объект QuizCard, а затем добавляет в ArrayList.
            while ((line = reader.readLine()) != null) {
                makeCard(line);
            }
            reader.close();

        } catch (Exception ex) {
            System.out.println("couldn't read the card file");
            ex.printStackTrace();
        }

        // Пришло время показать первую карточку
        cardIterator = cardList.iterator();
        showNextCard();
    }

    // Вызыается методом loadFile, берет строку из текстового файла, делит ее на две части -
    // вопрос и ответ - и создает новый объект QuizCard, а затем добавляет его в ArrayList с помощью CardList
    private  void makeCard(String lineToParse) {
//        // Каждая строка текста соответствует одной флешкарте, но нам нужно разделить вопрос и ответ.
//        // Для этого мы используем метод split() из класса String, который разбивает строку на две лексемы (одна для вопрос и одна для ответа).
//        String[] result = lineToParse.split("/");
//        QuizCard card = new QuizCard(result[0], result[1]);
//        cardList.add(card);
//        System.out.println("made a card");
        StringTokenizer parser = new StringTokenizer(lineToParse, "/");
        if (parser.hasMoreTokens()) {
            QuizCard card = new QuizCard(parser.nextToken(), parser.nextToken());
        }
    }

    private void showNextCard() {
        currentCard = (QuizCard) cardIterator.next();
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer = true;
    }
}
