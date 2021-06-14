package battleship;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class GameBoard {
    String[][] bord = new String[11][11];

    GameBoard() {                   //конструктор, рисует игровое поле
        bord[0][0] = " ";
        int a = 1;
        for (int i = 1; i < bord[0].length; i++) {
            bord[0][i] = String.valueOf(a);
            a++;

        }
        char aa = 'A';
        for (int i = 1; i < bord[0].length; i++) {
            bord[i][0] = String.valueOf(aa);
            aa++;

        }
        for (int i = 1; i < bord.length; i++) {
            for (int j = 1; j < bord[i].length; j++) {
                bord[i][j] = "~";
            }
        }

    }

    public void printBord(String bord[][]) {     //метод, выводит игровое поле на консоль
        for (int i = 0; i < bord.length; i++) {
            for (int j = 0; j < bord[i].length; j++) {
                System.out.print(bord[i][j] + " ");
            }
            System.out.println();
        }

    }

    public String[][] arrangementOfShips(String[][] bord) {   //метод для розстановки кораблей
        printBord(bord);
        int[] coordinates = new int[5];
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        boolean bol = true;
        Scanner sc = new Scanner(System.in);
        while (bol) {
            String coordinatesStr = sc.nextLine();
            coordinates = transfer(coordinatesStr);
            if (coordinates[4] == -1) {
                continue;
            }
            if (coordinates[4] != 4) {
                System.out.println("Error! Wrong length of the Aircraft Carrier! Try again:");
                continue;
            }
            bord = printShip(bord, coordinates);
            printBord(bord);
            break;
        }

        System.out.println("Enter the coordinates of the Battleship (4 cells):");

        while (bol) {
            String coordinatesStrBattleship = sc.nextLine();
            coordinates = transfer(coordinatesStrBattleship);
            coordinates = checkPlacement(bord, coordinates);
            if (coordinates[4] == -1) {
                continue;
            }
            if (coordinates[4] != 3) {
                System.out.println("Error! Wrong length of the Battleship! Try again:");
                continue;
            }

            bord = printShip(bord, coordinates);
            printBord(bord);
            break;
        }
        System.out.println("Enter the coordinates of the Submarine (3 cells):");

        while (bol) {
            String coordinatesStrBattleship = sc.nextLine();
            coordinates = transfer(coordinatesStrBattleship);
            coordinates = checkPlacement(bord, coordinates);
            if (coordinates[4] == -1) {
                continue;
            }
            if (coordinates[4] != 2) {
                System.out.println("Error! Wrong length of the Submarine! Try again:");
                continue;
            }

            bord = printShip(bord, coordinates);
            printBord(bord);
            break;
        }
        System.out.println("Enter the coordinates of the Cruiser (3 cells):");

        while (bol) {
            String coordinatesStrBattleship = sc.nextLine();
            coordinates = transfer(coordinatesStrBattleship);
            coordinates = checkPlacement(bord, coordinates);
            if (coordinates[4] == -1) {
                continue;
            }
            if (coordinates[4] != 2) {
                System.out.println("Error! Wrong length of the Cruiser! Try again:");
                continue;
            }

            bord = printShip(bord, coordinates);
            printBord(bord);
            break;
        }
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");

        while (bol) {
            String coordinatesStrBattleship = sc.nextLine();
            coordinates = transfer(coordinatesStrBattleship);
            coordinates = checkPlacement(bord, coordinates);
            if (coordinates[4] == -1) {
                continue;
            }
            if (coordinates[4] != 1) {
                System.out.println("Error! Wrong length of the Destroyer! Try again:");
                continue;
            }

            bord = printShip(bord, coordinates);
            printBord(bord);
            break;
        }


        //
        // }
        return bord;
    }


    public int[] transfer(String a) {//метод принимает строку с кординатами , возвращает масив int, первые четыри, это
        String[] arr = a.toUpperCase(Locale.ROOT).split("[^A-Z]");//кординаты, последний это длинна корабля, в случии
        String[] arrNum = a.toUpperCase(Locale.ROOT).split("[^0-9]");
        int cord[] = new int[5];                                 //неправельной розстановки, вернет длинну -1,
        try {
            byte temp[] = arr[0].getBytes(StandardCharsets.UTF_8);   // и сообщение об ошибке.
            cord[0] = temp[0] - 64;
            // byte temp1[] = arr[1].getBytes(StandardCharsets.UTF_8);
            cord[1] = Integer.parseInt(arrNum[1]);
            if (cord[1] != 10) {
                byte temp2[] = arr[2].getBytes(StandardCharsets.UTF_8);
                cord[2] = temp2[0] - 64;
            } else {
                byte temp2[] = arr[3].getBytes(StandardCharsets.UTF_8);
                cord[2] = temp2[0] - 64;

            }

            cord[3] = Integer.parseInt(arrNum[3]);
        } catch (Exception e) {
            // System.out.println("ERROR");
            cord[4] = -1;
        }
        if (cord[0] == cord[2]) {
            cord[4] = Math.abs(cord[1] - cord[3]);
        } else if (cord[1] == cord[3]) {
            cord[4] = Math.abs(cord[0] - cord[2]);
        } else if (cord[2] != 0 && cord[3] != 0) {
            System.out.println("Error! Wrong ship location! Try again:");
            cord[4] = -1;
        }
        return cord;
    }

    public String[][] printShip(String bord[][], int[] cord) {// метод рисует корабль;
        String ship = "O";
        int xMin = 0;
        int xMax = 0;
        int yMin = 0;
        int yMax = 0;

        int x1 = cord[0];
        int y1 = cord[1];
        int x2 = cord[2];
        int y2 = cord[3];
        int l = cord[4];
        if (x1 == x2) {
            yMin = Math.min(y1, y2);
            yMax = Math.max(y1, y2);

            for (int i = yMin; i <= yMax; i++) {
                bord[x1][i] = ship;
            }
        } else if (y1 == y2) {
            xMin = Math.min(x1, x2);
            xMax = Math.max(x1, x2);
            for (int i = xMin; i <= xMax; i++) {
                bord[i][y1] = ship;
            }
        }
        return bord;
    }

    public int[] checkPlacement(String[][] bord, int[] cord) {//метод проверки близкостоящих кораблей
        int xMin = 0;
        int xMax = 0;
        int yMin = 0;
        int yMax = 0;
        int x1 = cord[0];
        int y1 = cord[1];
        int x2 = cord[2];
        int y2 = cord[3];
        int l = cord[4];
        int count = 0;


        if (x1 == x2) {
            if (x1 != 1) xMin = x1 - 1;
            if (x2 != 10) xMax = x2 + 1;

            yMin = Math.min(y1, y2);
            yMax = Math.max(y1, y2);
            if (yMin != 1) yMin -= 1;
            if (yMax != 10) yMax += 1;

        } else if (y1 == y2) {
            if (y1 != 1) yMin = y1 - 1;
            if (y2 != 10) yMax = y2 + 1;

            xMin = Math.min(x1, x2);
            xMax = Math.max(x1, x2);
            if (xMin != 1) xMin -= 1;
            if (xMax != 10) xMax += 1;
        }
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMin; j <= yMax; j++) {
                if (bord[i][j].equals("O")) {
                    count++;
                }
            }
        }
        if (count > 0) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            cord[4] = -1;
        }
        return cord;
    }

}
