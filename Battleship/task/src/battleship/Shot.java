package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shot {
    GameBoard gB = new GameBoard();

    String[][] fog = gB.bord;
int shot=17;

    public String[][] shota(String bord[][],String bord1[][]) throws IOException {
      //14.06  System.out.println("\nThe game starts!\n");
        gB.printBord(fog);
        System.out.println("---------------------");
        gB.printBord(bord);


        //14.06  System.out.println("\nTake a shot!\n");
      //14.06  while (true) {

            String a;
            //  while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int[] coord = new int[5];
            while (true) {
                a = br.readLine();
                coord = gB.transfer(a);
                if (coord[0] > 10 || coord[1] > 10) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                    continue;
                }
                break;
            }

           printShot(bord1, coord);
         //     if (bord[0][0].equals("exit")){break;}
       // }
             return bord;
     //    }
    }

    public String[][] printShot(String bord[][], int coord[]) {
        if (bord[coord[0]][coord[1]].equals("~")||bord[coord[0]][coord[1]].equals("M")) {
            bord[coord[0]][coord[1]] = "M";
            fog[coord[0]][coord[1]] = "M";
           //14.06 gB.printBord(fog);
            System.out.println("You missed!");
            //14.06 gB.printBord(bord);

        }else  if(bord[coord[0]][coord[1]].equals("X")){
            //14.06 gB.printBord(fog);
            System.out.println("You sank the last ship. You won. Congratulations!");

        }
        else if (bord[coord[0]][coord[1]].equals("O")) {
            bord[coord[0]][coord[1]] = "X";
            fog[coord[0]][coord[1]] = "X";
            shot--;
            //14.06 gB.printBord(fog);
            if (shot==0){
                System.out.println("You sank the last ship. You won. Congratulations!");
                bord[0][0]="exit";

                return bord;
            }
            boolean bol=true;
            bol=sankShip(bord,coord,bol);
            if(bol==true)System.out.println("You hit a ship!");

           // gB.printBord(bord);
        }
        return bord;
    }
    public boolean sankShip(String[][] bord, int[] cord,boolean bol) {//метод проверки проверки поражения всего
        int xMin = 0;                                          //корабля, скопирыван с метода росстановки кораблей,
        int xMax = 0;                                           //по этому много лишнего
        int yMin = 0;
        int yMax = 0;
        int x1 = cord[0];
        int y1 = cord[1];
        int x2 = x1;
        int y2 = y1;
        int l = 1;
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
        if (count == 0) {
            System.out.println("You sank a ship! Specify a new target:");
            bol=false;
        }else bol=true;
        return bol;
    }


}
