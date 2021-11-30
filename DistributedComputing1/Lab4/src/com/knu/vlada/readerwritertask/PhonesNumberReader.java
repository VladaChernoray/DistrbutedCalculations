package com.knu.vlada.readerwritertask;

public class PhonesNumberReader implements Runnable {
    private Database database;

    public PhonesNumberReader(Database database) {
        this.database = database;
    }
    @Override
    public void run() {
        for (int i = 0; i < 12 && !Thread.currentThread().isInterrupted(); ++i) {
            try {
                database.readPhone("name1");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
