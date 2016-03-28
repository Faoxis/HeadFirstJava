package Lesson14;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Sergei on 05.02.2016.
 */
public class WriteAFile {

    public static void main (String[] args) {

        try {
            FileWriter writer = new FileWriter("D:\\Foo.txt"); // Файл в поток! Его его нет - создадим!
            writer.write("Привет, Фу!"); // Написать сообщение в поток!

            writer.close(); // Закрыть файл!

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
