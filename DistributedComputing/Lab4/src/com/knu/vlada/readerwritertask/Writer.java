package com.knu.vlada.readerwritertask;

public class Writer implements Runnable {
    private Database database;

    public Writer(Database database) {
        this.database = database;
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; ++i) {
            try {
                Thread.sleep(3000);
                db.write(new Data("name3", 3333));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
