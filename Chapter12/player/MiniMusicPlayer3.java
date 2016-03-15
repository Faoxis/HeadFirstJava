package player;

import javax.sound.midi.*; // Библиотека звуков
import javax.swing.*; // Библиотека графического интерфейса
import java.awt.*; // Библиотека событий

/**
 * Created by Sergei on 28.01.2016.
 */
public class MiniMusicPlayer3 {

    static JFrame f = new JFrame("Психоделия"); // Создание формы
    static MyDrowPanel m1; // Создание переменную типа собственного объекта

    public static void main (String[] agrs) {
        MiniMusicPlayer3 mini = new MiniMusicPlayer3();
        mini.go();
    }


    /******************************* Функция создает форму и добавляет на нее объект  *********************************/
    public void setUpGui() {
        m1 = new MyDrowPanel();
        f.setContentPane(m1);
        f.setBounds(30, 30, 300, 300);
        f.setVisible(true);
    }

    /**************************************** Запуск событий на форме *************************************************/
    public void go() {
        setUpGui(); // Создание формы

        try {
            Sequencer sequencer = MidiSystem.getSequencer(); // Создание ситезатора
            sequencer.open(); // Откроем его

            //Функция регистрации события синтезатором.
            sequencer.addControllerEventListener(m1, new int[] {127}); //Метод принимает объект слушателя и целочисленный массив,
            // который представляет собой список событий ControllerEvent.
            // Здесь используется только одно событие - №127


            Sequence seq = new Sequence(Sequence.PPQ, 4); // Создание последовательности
            Track track = seq.createTrack();              // и дорожки

            int r = 0;
            for (int i = 0; i < 60; i += 4) {

                r = (int) ((Math.random() * 50) + 1);
                track.add(makeEvent(144, 1, r, 100, i)); // 144 - запускаем мелодию
                track.add(makeEvent(176, 1, 127, 0, i)); // 176 - запускаем событие
                track.add(makeEvent(128, 1, r, 100, i+2)); // 128 - останавливаем ноту
            }

            sequencer.setSequence(seq);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception ex) { ex.printStackTrace(); }
    }


    public MidiEvent makeEvent (int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception e) {        }
        return event;
    }


    class MyDrowPanel extends JPanel implements ControllerEventListener {
        boolean msg = false; // Флаг события

        public void controlChange (ShortMessage event) {
            msg = true;
            repaint();
        }

        public void paintComponent (Graphics g) {
            if (msg) {

                Graphics2D g2 = (Graphics2D) g;

                int r = (int) (Math.random() * 250);
                int gr = (int) (Math.random() * 250);
                int b = (int) (Math.random() * 250);
                g.setColor (new Color(r, gr, b));

                int ht = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);
                int x = (int) ((Math.random() * 120) + 10);
                int y = (int) ((Math.random()* 40) + 10);
                g.fillRect (x, y, ht, width);

                msg = false;
            }
        }
    }


}
