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
        boolean isPlayerAdded = gameConnector.addPlayer(player);
        System.out.println(gameConnector.getMessage());
        if(isPlayerAdded){
            int round = 1;
            boolean isGameStarted = gameConnector.isGameStared();
            boolean isUserFirst = !gameConnector.isGameStared();
            while(!isGameStarted){
                Thread.sleep(1000);
                isGameStarted = gameConnector.isGameStared();
            }

            if(isUserFirst){
                System.out.println(gameConnector.getMessage());
            }

            int signsNumberServer = 0;

            while (gameConnector.isGameStared()){

                while (isUserFirst && signsNumberServer % 2 == 1) {
                    Thread.sleep(1000);
                    System.out.println("Please wait..");
                    signsNumberServer = gameConnector.howManySignsOnBoard();
                }
                while(!isUserFirst && signsNumberServer%2 == 0) {
                    Thread.sleep(1000);
                    System.out.println("Please wait..");
                    signsNumberServer = gameConnector.howManySignsOnBoard();
                }

                if(round != 1)
                    System.out.println(gameConnector.getMessage());

                Scanner scanner = new Scanner(System.in);
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                boolean isMoved = gameConnector.move(x,y,player);
                if(isMoved)
                    round++;
                System.out.println("After\n" + gameConnector.getMessage());
                signsNumberServer = gameConnector.howManySignsOnBoard();
                //scanner.close();
            }
        }
    }
}
