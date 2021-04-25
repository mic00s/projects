package com.sudoku.problemdomain;

import java.util.Objects;

public class Coordinates {
    private final int x;
    private final int y;

    //constructor
    public Coordinates (int x, int y){
        this.x = x;
        this.y = y;

    }

    //getters


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    //store coordinates in a hashmap and use the main class Object
    //Boilerplate code
    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    //hashcode function - generate unique identifier from x & y
    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }

}
