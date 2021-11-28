package com.knu.vlada.readerwritertask;
import com.knu.vlada.readerwritertask.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("database.txt");
        file.createNewFile();

        Database database = new Database(file);
        database.write(new Data("name_1", 1111));
        database.write(new Data("name_2", 2222));
        database.write(new Data("name_3", 1111));
        Thread phoneReader = new Thread(new PhonesReader(database), "Phone Reader");
        Thread nameReader = new Thread(new NamesReader(database), "Name Reader");
        Thread writer = new Thread(new Writer(database), "Writer");
        phoneReader.start();
        nameReader.start();
        writer.start();
    }
}

