package com.piemon.tictoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameConnector extends Remote {
    boolean addPlayer(Player player) throws RemoteException;
    boolean move(int x, int y, Player player) throws RemoteException;
    boolean isGameStared()  throws RemoteException;
    int howManySignsOnBoard() throws RemoteException;
    String drawBoard() throws RemoteException;
    String getMessage() throws RemoteException;
}
