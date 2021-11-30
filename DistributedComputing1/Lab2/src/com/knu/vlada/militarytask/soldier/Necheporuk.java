package com.knu.vlada.militarytask.soldier;

import com.knu.vlada.militarytask.Good;
import com.knu.vlada.militarytask.Storage;

public class Necheporuk implements Runnable {
    private Storage van;
    public Necheporuk(Storage van) {
        this.van = van;
    }

    @Override
    public void run() {
        while (!van.isFinished() || !van.isEmpty()) {
            Good good = van.get();
        }
    }
}
