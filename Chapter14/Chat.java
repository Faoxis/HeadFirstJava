package Lesson14;

import java.io.Serializable;

/**
 * Created by Sergei on 05.02.2016.
 */
public class Chat implements Serializable {
    transient String currentID; // переменная не сохранится при сериализации
    String userName; // переменная сохранится при сериализации
}
