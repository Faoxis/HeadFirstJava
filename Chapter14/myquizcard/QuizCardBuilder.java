package Chapter14.myquizcard;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sergei on 25.03.2016.
 */
public class QuizCardBuilder {

    private JFrame frame;
    private JTextArea question;
    private JTextArea answer;
    private JPanel mainPanel;
    private ArrayList cardList;
    private JLabel qLabel;
    //    JButton


    public static void main(String[] args) {
        QuizCardBuilder gui = new QuizCardBuilder();
        gui.go();
    }


    public void go() {
        //Тут понятно. Инициализируем фрайм
        frame = new JFrame("CardBuilder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем основную панель, которую помести в центр фрейма
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Формируем область для ввода текста вопроса
        Font bigFont = new Font("sanserif", Font.BOLD, 20);
        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);

        // Делаем для этого создаем скроллер
        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Формируем область для ввода текста ответа
        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);

        // Угадай, что мы делаем для это области ?
        JScrollPane aScroller = new JScrollPane(answer);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Тут все понятно, создаем кнопку
        JButton nextButton = new JButton("Next Card");

        cardList = new ArrayList();

        // Создаем две надписи на экране
        qLabel = new JLabel("Question ");
        JLabel aLabel = new JLabel("Answer");

        // Доабавляем на паналь:
        mainPanel.add(qLabel); // Вначале надпись Question
        mainPanel.add(qScroller); // Поле ввода
        mainPanel.add(aLabel); // Надпись Answer
        mainPanel.add(aScroller); // поле ввода
        mainPanel.add(nextButton); // Кнопочку в конец
        nextButton.addActionListener(new NextCardListener());


        // Создаем менюшку сверху. Для этого создается "матрешка":
        // JMenuBar (сама панель сверху) -> JMenu (один пункт этого меню) -> JMenuItem (вариант выбора из всплывающего списка)
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        JMenuBar menuBar = new JMenuBar();  // Верхний уровень матрешки
        /**************************************************************************************************************/
        JMenu fileMenu = new JMenu("File");

        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(new NewMenuListener());
        fileMenu.add(newMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new SaveMenuListener());
        fileMenu.add(saveMenuItem);
        /**************************************************************************************************************/
        menuBar.add(fileMenu);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        frame.setJMenuBar(menuBar);

        frame.add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }


    public class NextCardListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Тут все просто. При нажатии кнопки Next считываются значения с текстовых полей и добавляются в List.
            // После чего карта очищается и каретка переходит к текстовому полю question.
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();
        }
    }

    public class NewMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Ну тут все понятно. Начинаем сначала, для этого очищаем список и чистим карту
            cardList.clear();
            clearCard();

        }
    }

    public class SaveMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);

            JFileChooser fileSave = new JFileChooser(); //! Специальный компонент для менюшки ds,jhf afqkf
            fileSave.showSaveDialog(frame); // file
            saveFile(fileSave.getSelectedFile());

        }
    }


    private void saveFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            Iterator cardIterator = cardList.iterator();

            while (cardIterator.hasNext()) {
                QuizCard card = (QuizCard) cardIterator.next();
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Couln't write the cardList out");
            e.printStackTrace();
        }
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus(); //! Так делается смена фокуса
    }


}




