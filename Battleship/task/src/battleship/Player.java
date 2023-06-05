package battleship;

import java.util.Scanner;

public class Player {

    char[][] field = new char[10][10];
    char [][] fogField = new char[10][10];

    char[][] ships = new char[10][10];

    public Player() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.field[i][j] = '~';
                this.fogField[i][j] = '~';
                this.ships[i][j] = '~';
            }
        }
    }

//    public  void printFogField(char[][] field) {
//        System.out.print(" ");
//        for (int i = 0; i < 10; i++) {
//            System.out.print(" " + (i + 1));
//        }
//        System.out.println();
//        char a = 'A';
//        for (int i = 0; i < 10; i++) {
//            System.out.print(a);
//            a++;
//            for (int j = 0; j < 10; j ++) {
//                System.out.print(" " +fogField[i][j]);
//            }
//            System.out.println();
//        }
//    }

    public  void printGameField(char[][] field) {
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

    public void placeShip(String shipName, int shipLength, char ship) {
        boolean isPlaced = false;
        System.out.println("\n" +
                "Enter the coordinates of the " + shipName + " (" + shipLength + " cells):");
        Scanner scanner = new Scanner(System.in);
        while(!isPlaced) {
            String first = scanner.next();
            String second = scanner.next();
            int x1 = Math.min(first.charAt(0) - 'A',second.charAt(0) - 'A');
            int x2 = Math.max(first.charAt(0) - 'A',second.charAt(0) - 'A');
            int y1 = Math.min(Integer.parseInt(first.substring(1)) - 1, Integer.parseInt(second.substring(1)) - 1);
            int y2 = Math.max(Integer.parseInt(first.substring(1)) - 1, Integer.parseInt(second.substring(1)) - 1);
            if (y2 - y1 != shipLength - 1 && x2 - x1 != shipLength - 1) {
                System.out.println("Error! Wrong length of the " + shipName + "! Try again:");
            } else if (x1 != x2 && y1 != y2) {
                System.out.println("Error! Wrong ship location! Try again:");
            } else if (checkForNearbyShips(x1, y1, x2, y2)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
            } else {
                if (x1 == x2) {
                for (int i = y1; i <= y2; i++) {
                    field[x1][i] = 'O';
                    ships[x1][i] = ship;
                    }
                    isPlaced = true;
                } else {
                    for (int i = x1; i <= x2; i++) {
                        field[i][y1] = 'O';
                        ships[i][y1] = ship;
                    }
                    isPlaced = true;
                }
            }

        }
    }

    public boolean checkForNearbyShips(int x1, int y1, int x2, int y2) {
        boolean isNear = false;
        for(int i = x1 - 1; i <= x2 + 1; i++){
            for (int j = y1 - 1; j <= y2 + 1; j++) {
                if (i >= 0 && i < 10 && j >= 0 && j < 10 && field[i][j] == 'O' ) {
                    isNear = true;
                }
            }
        }
        return isNear;
    }

    public void takeAShot(char[][] field, char[][] ships) {
        boolean shotTaken = false;
        Scanner scanner = new Scanner(System.in);
        while (shotTaken == false) {
            String shot = scanner.next();
            int x1 = shot.charAt(0) - 'A';
            int y1 = Integer.parseInt(shot.substring(1)) - 1;
            if (x1 < 0 || x1 > 10 || y1 < 0 || y1 > 9) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");

            } else if (field[x1][y1] == '~') {
                field[x1][y1] = 'M';
                fogField[x1][y1] = 'M';
                //printGameField(fogField);
                System.out.println("You missed!");
                System.out.println("Press Enter and pass the move to another player");

                //printGameField(field);
                shotTaken = true;
            } else if (field[x1][y1] == 'O' || field[x1][y1] == 'X') {
                field[x1][y1] = 'X';
                fogField[x1][y1] = 'X';
                if (checkWinner(field) == true){
                    System.out.println("You sank the last ship. You won. Congratulations!");
                } else if (isShipSunk(x1, y1, ships) == false) {
                    System.out.println("You hit a ship!");
                } else {
                    System.out.println("You sank a ship!");
                }
                ships[x1][y1] = 'X';
                //printGameField(fogField);

                System.out.println("Press Enter and pass the move to another player");

                //printGameField(field);
                shotTaken = true;
            }

        }
        //System.out.println("You sank the last ship. You won. Congratulations!");
    }

    public boolean checkWinner(char[][] field) {
        boolean isWinner = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j] == 'O') {
                    isWinner = false;
                }
            }
        }
        return isWinner;
    }

    public boolean isShipSunk(int x, int y,char[][] ships) {
        int count = 0;
        boolean isSank =false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (ships[i][j] == ships[x][y]) {
                    count ++;
                }
            }
        }
        if (count == 1) {
            isSank = true;
        }
        return isSank;
    }
}

