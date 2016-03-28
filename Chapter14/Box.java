package Lesson14;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Sergei on 05.02.2016.
 */
public class Box implements Serializable {

    private int width;
    private int height;

    public void setWidth (int w) {
        width = w;
    }

    public void setHeight (int h) {
        height = h;
    }

    public static void main (String[] args) {

        Box myBox = new Box();
        myBox.setWidth(50);
        myBox.setHeight(20);

        try {
            // Объект FileOutputStream знает как подключиться к файлу (и как создать его).
            // С помощью контруктора объект записан в байтовом виде
            FileOutputStream fs = new FileOutputStream("foo.ser");
            // Сериализация объекта
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(myBox);
            os.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


}
