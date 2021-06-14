package battleship;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        GameBoard gB = new GameBoard();
        GameBoard gB1 = new GameBoard();
        Scanner sc = new Scanner(System.in);
        System.out.println("Player 1, place your ships on the game field\n");
        String[][] bord = gB.bord;
        String[][] bord1 = gB1.bord;
        bord = gB.arrangementOfShips(bord);
        System.out.println("Press Enter and pass the move to another player\n");
        String temp =sc.nextLine();
        System.out.println("Player 2, place your ships to the game field\n");
        bord1 = gB1.arrangementOfShips(bord1);
        System.out.println("Press Enter and pass the move to another player");
        String temp1=sc.nextLine();

       // System.out.println("Player 1, it's your turn:");

        Shot shot = new Shot();
        Shot shot1 = new Shot();
while (true) {
    System.out.println("Player 1, it's your turn:");
    bord = shot.shota(bord, bord1);
    System.out.println("Press Enter and pass the move to another player\\n");
    String temp2 = sc.nextLine();

    System.out.println("Player 2, it's your turn:");
    bord1 = shot1.shota(bord1, bord);
    System.out.println("Press Enter and pass the move to another player\\n");
    String temp3 = sc.nextLine();
}

    }
}
