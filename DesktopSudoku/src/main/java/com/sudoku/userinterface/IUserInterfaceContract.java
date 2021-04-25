package com.sudoku.userinterface;

//three layer architecture

import com.sudoku.problemdomain.SudokuGame;

public interface IUserInterfaceContract {
    //use parent interface like a namespace, to differentiate interfaces
    //EventListener is a controller that presents everything
    interface EventListener{
        void onSudokuInput (int x, int y, int input);
        void onDialogClick ();

    }

    interface View{
        void setListener(IUserInterfaceContract.EventListener listener);
        void updateSquare(int x, int y, int input);
        void updateBoard(SudokuGame game);
        void showDialog (String message);
        void showError (String message);
    }
}
