package com.piemon.tictoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameService extends Remote{
    boolean join(Player player) throws RemoteException;
    boolean move(int x, int y, Player player) throws RemoteException;
    char[][] getCurrentBoard() throws RemoteException;
    int getPlayersCount() throws RemoteException;
}
