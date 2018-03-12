package com.piemon;

import com.piemon.tictoe.GameService;
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
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Properties prop = new Properties();
    InputStream input = null;

    public static void main(String[] args) throws IOException, NotBoundException, InterruptedException {
        InputStream input = new FileInputStream("config.properties");
        prop.load(input);
        Registry registry = LocateRegistry.getRegistry(prop.getProperty("host"));
        runGame(registry);
    }

    private static void runGame(Registry registry) throws RemoteException, NotBoundException, InterruptedException {
        GameService gameService = (GameService) registry.lookup("TicTacToe");
        Player player = new Player(prop.getProperty("user.name"));
        System.out.println(player.getName());
        if(gameService.join(player)){
            System.out.println("Entered");
            char[][] board = gameService.getCurrentBoard();
            for (char[] aBoard : board) {
                System.out.println('|');
                for (int j = 0; j < aBoard.length; j++)
                    System.out.print(aBoard[j] + '|');
            }
            manageGame(gameService, player);
        } else System.out.println("Please wait until previous game end,");


    }

    private static void manageGame(GameService gameService, Player player) throws InterruptedException, RemoteException {
        int playerNumber = player.getJoinOrderNum();
        while (true){
            TimeUnit.SECONDS.sleep(1);
            if(gameService.getPlayersCount() == 2 && player.getJoinOrderNum() == playerNumber){
                System.out.println("It's your turn. Insert x and y: ");
                Scanner scanner = new Scanner(System.in);
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if(gameService.move(x, y, player)) {
                    playerNumber = (playerNumber == 1 ? 2 : 1);
                    break;
                } else {
                    System.out.println("WRONG X AND/OR Y, TRY AGAIN");
                }
            } else{
                System.out.println("Please wait until second player join or make a move...");
            }
        }
    }
}
