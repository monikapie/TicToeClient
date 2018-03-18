package com.piemon;

import com.piemon.tictoe.GameConnector;
import com.piemon.tictoe.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private static final Properties prop = new Properties();
    InputStream input = null;

    public static void main(String[] args) throws IOException, NotBoundException, InterruptedException {
        InputStream input = new FileInputStream("config.properties");
        prop.load(input);
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",9000);
        runGame(registry);
    }

    private static void runGame(Registry registry) throws RemoteException, NotBoundException, InterruptedException {
        GameConnector gameConnector = (GameConnector) registry.lookup("TicTacToe");
        Player player = new Player(prop.getProperty("user.name"));
        System.out.println(gameConnector.addPlayer(player));
        while(true){
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            System.out.println(gameConnector.move(x,y,player));
        }
    }
}
