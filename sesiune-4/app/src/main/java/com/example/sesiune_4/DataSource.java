package com.example.sesiune_4;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    static List<Item> getItems(int itemCount) {
        List<Item> list = new ArrayList<>(itemCount);
        for (int i = 0; i < itemCount; i++) {
            list.add(new Item("FirstName " + i, "LastName " + i));
        }
        return list;
    }

}
