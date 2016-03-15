package player;

import javax.sound.midi.*;

/**
 * Created by Sergei on 28.01.2016.
 */
public class MiniMusicPlayer2 implements ControllerEventListener{ // интерфейс ControllerEventListener позволяет отслеживать события ControllerEvent
    public static void main (String[] args) {
        MiniMusicPlayer2 mini = new MiniMusicPlayer2();
        mini.go();
    }

    public void go() {

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] eventsWant = {127}; // Массив событий. В нашем случае это событие №127
            sequencer.addControllerEventListener(this, eventsWant); // Функция принимает на вход источник события
            // и массив событий

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            for (int i = 5; i < 60; i +=4) {
                track.add(makeEvent(144, 1, i, 100, i));

                track.add(makeEvent(176, 1, 127, 0, i)); // 176 означает, что типа события - ControllerEvent,
                // третий аргумент номер события. В нашем случае это #127
                // !!! событие мы добавляем в трек, а не вызываем немедленно!

                track.add(makeEvent(128, 1, i, 100, i + 2));
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();

        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public void controlChange (ShortMessage event) {
        System.out.println ("ля");
    }

    public MidiEvent makeEvent (int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage (comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception e) { }

        return event;
    }

}
