package com.knu.vlada.militarytask.soldier;

import com.knu.vlada.militarytask.Good;
import com.knu.vlada.militarytask.Storage;

import java.util.ArrayList;

public class Ivanov implements Runnable {
    private ArrayList<Good> warehouse;
    private Storage outer;

    public Ivanov(ArrayList<Good> warehouse, Storage outer) {
        this.warehouse = warehouse;
        this.outer = outer;
    }

    @Override
    public void run() {
        while (warehouse.size() > 0) {
            Good good = warehouse.get(warehouse.size()-1);
            warehouse.remove(warehouse.size()-1);
            outer.put(good);
        }
        outer.setFinish();
    }
}
