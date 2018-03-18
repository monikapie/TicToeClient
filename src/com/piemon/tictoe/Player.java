package com.piemon.tictoe;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1;
    private String name;
    private int number;

    public Player(String name) {
        this.name = name;
    }

    public Sign getSign() {
        if(number == 1)
            return Sign.O;
        else
            return Sign.X;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
