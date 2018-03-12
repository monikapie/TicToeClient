package com.piemon.tictoe;

public class Player implements Observer{
    private int joinOrderNum;
    private String name;
    private char ticTacToeMark;

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getTicTacToeMark() {
        return ticTacToeMark;
    }

    public void setTicTacToeMark(char ticTacToeMark) {
        this.ticTacToeMark = ticTacToeMark;
    }

    public int getJoinOrderNum() {
        return joinOrderNum;
    }

    public void setJoinOrderNum(int joinOrderNum) {
        this.joinOrderNum = joinOrderNum;
    }


    @Override
    public void notifyPlayer(boolean isGameInProgress, String gameWinnerName) {
        if(isGameInProgress) System.out.println("The game is in progress.");
        else System.out.println("The game is won by " + gameWinnerName);
    }
}
