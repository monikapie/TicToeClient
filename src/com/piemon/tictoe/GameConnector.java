package com.piemon.tictoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameConnector extends Remote {
    String addPlayer(Player player) throws RemoteException;
    String move(int x, int y, Player player) throws RemoteException;
}
