package Lesson14;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Sergei on 05.02.2016.
 */
public class Pond implements Serializable {

    private Duck duck = new Duck();

    public static void main (String[] args) {
        Pond myPond = new Pond();

        try {

            FileOutputStream fs = new FileOutputStream("Pond.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(myPond); // ошибка при запуске. Класс Pond содержит объект бех сериализации
            os.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class Duck {

    }
}


