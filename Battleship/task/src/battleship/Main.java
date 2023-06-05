package battleship;

import java.util.Arrays;
import java.util.Scanner;

public  class Main {

    public static void main(String[] args) {
        // Write your code here
        char[][] field = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }

        Player player1 = new Player();
        System.out.println("Player 1, place your ships on the game field");
        player1.printGameField(player1.field);
        player1.placeShip("Aircraft Carrier", 5, 'A');
        player1.printGameField(player1.field);
        player1.placeShip("Battleship", 4, 'B');
        player1.printGameField(player1.field);
        player1.placeShip("Submarine", 3, 'S');
        player1.printGameField(player1.field);
        player1.placeShip("Cruiser", 3, 'C');
        player1.printGameField(player1.field);
        player1.placeShip("Destroyer", 2, 'D');
        player1.printGameField(player1.field);
        System.out.println("Press Enter and pass the move to another player");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        Player player2 = new Player();
        System.out.println("Player 2, place your ships on the game field");
        player2.printGameField(player2.field);
        player2.placeShip("Aircraft Carrier", 5, 'A');
        player2.printGameField(player2.field);
        player2.placeShip("Battleship", 4, 'B');
        player2.printGameField(player2.field);
        player2.placeShip("Submarine", 3, 'S');
        player2.printGameField(player2.field);
        player2.placeShip("Cruiser", 3, 'C');
        player2.printGameField(player2.field);
        player2.placeShip("Destroyer", 2, 'D');
        player2.printGameField(player2.field);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        while(true) {
            printGameField(field);
            System.out.println("---------------------");
            player1.printGameField(player1.field);
            System.out.println("Player 1, it's your turn:");
            player1.takeAShot(player2.field, player2.ships);
            scanner.nextLine();
            printGameField(field);
            System.out.println("---------------------");
            player2.printGameField(player2.field);
            System.out.println("Player 2, it's your turn:");
            player2.takeAShot(player1.field, player1.ships);
            scanner.nextLine();
        }
//        player2.printGameField(player2.fogField);
//        System.out.println("---------------------");
//        player1.printGameField(player1.field);
//        System.out.println("The game starts!");
//        player1.printGameField(player1.fogField);
//        player1.takeAShot();



    }

    public static void printGameField(char[][] field) {
        System.out.print(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        char a = 'A';
        for (int i = 0; i < 10; i++) {
            System.out.print(a);
            a++;
            for (int j = 0; j < 10; j ++) {
                System.out.print(" " +field[i][j]);
            }
            System.out.println();
        }
    }
}
