package Lesson14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Sergei on 05.02.2016.
 */
public class ReadAFile {
    public static void main (String[] args) {

        try {
            File myFile = new File("MyText.txt");

            // FileReader - поток соединений дя символов, который подключается к текстовому файлу
            FileReader fileReader = new FileReader(myFile);

            // Для более эффективного чтения соединим FileReader с BufferedReader.
            // Тогда FileReader будет обращаться к файлу только в том случае, если буфер будет пуст.
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
