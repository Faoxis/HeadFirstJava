package Chapter12;

/**
 * Created by Sergei on 15.03.2016.
 */

import javax.swing.*;
import java.awt.event.*; // Новый оператор импорта для пакета в котором хранится ActionListener и ActionEvent



public class SimpleGui1B implements ActionListener { // Обязательное добавление реализцаии интерфейса слушателя
                                    // Кнопка будет передавать события только тем, у кого реализован ActionListener.
    JButton button;

    public static void main(String[] args) {
        SimpleGui1B gui = new SimpleGui1B();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        button = new JButton("click me");

        button.addActionListener(this); // Регистрация заинтересованность в кнопке.
        // Передоваемый объект ОБЯЗАН быть объектом класса реализуемого ActionListener.

        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    // Реализация метода из метода из интерфейса ActionListener.
    // Фактически, тело этого метода и является программа, которая выполняется при нажатии на кнопку.
    public void actionPerformed(ActionEvent event) {
        button.setText("I've been clicked!");
    }

}
