package Lesson13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sergei on 03.02.2016.
 */
public class TextArea1 implements ActionListener {

    JTextArea text;

    public static void main (String[] args) {
        TextArea1 gui = new TextArea1();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame(); // Создаем фрейм
        JPanel panel = new JPanel(); // Создаем панель
        JButton button = new JButton("Just Click It"); // Создаем кнопку
        button.addActionListener(this); // Ставим "прерывание" при нажатии кнопки
        text = new JTextArea(10, 20); // инициализируем объект text
        text.setLineWrap(true); // Вкючаем перенос текста

        JScrollPane scroller = new JScrollPane(text); // Создаем скроллер  и присваиваем ему область прокручивания (text)
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);   // использовать вертикальноую полосу прокрутки
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // и не использовать горизонтальную

        panel.add(scroller); // !!!!!!!!!!!!!!!!!!!!!!!! Добавляем скроллер на панель

        frame.getContentPane().add(BorderLayout.CENTER, panel); // Добавляем панель по центру
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        frame.setBounds(30, 30, 350, 300);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        System.out.println(text.getText());
        text.append("The button has been clicked\n");
    }
}
