package com.isthifa.serialization_deserialization.entity;

import java.io.Serializable;

public class Book implements Serializable {
    int bcode;
    String bname;

    public Book(int bcode, String bname) {
        this.bcode = bcode;
        this.bname = bname;
    }

    public int getBcode() {
        return bcode;
    }

    public String getBname() {
        return bname;
    }
}
