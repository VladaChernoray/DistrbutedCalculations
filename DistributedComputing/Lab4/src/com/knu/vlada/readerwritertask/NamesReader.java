package com.knu.vlada.readerwritertask;

public class NamesReader implements Runnable {
    private Database database;

    public NamesReader(Database database) {
        this.database = database;
    }
    @Override
    public void run() {
        for (int i = 0; i < 7 && !Thread.currentThread().isInterrupted(); ++i) {
            try {
                database.readName(3333);
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
