package com.piemon.tictoe;

public interface Observer {
    void notifyPlayer(boolean isGameInProgress, String gameWinnerName);
}
