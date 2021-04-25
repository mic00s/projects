package com.sudoku.userinterface;

import javafx.scene.control.TextField;

//maintains an x- and y- coordinate
public class SudokuTextField extends TextField {
    private final int x;
    private final int y;

    public SudokuTextField(int x, int y){
            this.x = x;
            this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //override 2 functions to have smooth entering of 1-9 numbers
    @Override
    public void replaceText(int i, int i1, String s){
        if (!s.matches("[0-9]")) {
            super.replaceText(i, i1, s);
        }
    }

    @Override
    public void replaceSelection(String s){
        if (!s.matches("[0-9]")){
            super.replaceSelection(s);
        }
    }
}
