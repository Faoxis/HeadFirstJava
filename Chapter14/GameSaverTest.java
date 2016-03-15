package Lesson14;

import java.io.*;

/**
 * Created by Sergei on 02.02.2016.
 */

import org.omg.CosNaming.NamingContextPackage.NotFound;



public class GameSaverTest {
    public static void main(String[] args) {
        GameCharacter one =  new GameCharacter(50, "Эльф", new String[] {"лук", "меч", "кастет"});
        GameCharacter two =  new GameCharacter(200, "Троль", new String[] {"голые руки", "большой тапор"});
        GameCharacter three =  new GameCharacter(120, "Маг", new String[] {"заклинания", "невидимость"});

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser")); // Сериализация
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        one   = null;
        two   = null;
        three = null;

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Game.ser")); // Десериализация
            GameCharacter newOne = (GameCharacter) is.readObject();
            GameCharacter newTwo = (GameCharacter) is.readObject();
            GameCharacter newThree = (GameCharacter) is.readObject();

            is.close();

            System.out.println("Первый: " + newOne.getType());
            System.out.println("Второй: " + newTwo.getType());
            System.out.println("Третий: " + newThree.getType());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

