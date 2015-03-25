package istia.ei4.ProjetISTIA;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Alain on 04/02/2015.
 */
public class MapGenerator {

    Random rand;
    public MapGenerator(){
        rand = new Random();
    }

    public ArrayList<GridElement> translateArraysToMap(int[][] horizontalWalls, int[][] verticalWalls)
    {
        int i = 0;
        int j = 0;
        ArrayList<GridElement> data = new ArrayList<GridElement>();

        for(i=0; i<17; i++)
            for(j=0; j < 17; j++)
            {
                if(horizontalWalls[i][j]== 1)
                {
                    data.add(new GridElement(i,j,"mh"));
                }
                if(verticalWalls[i][j]== 1)
                {
                    data.add(new GridElement(i,j,"mv"));
                }
            }
        return data;

    }

    public int getRandom(int min, int max)
    {
        return rand.nextInt((max - min) + 1) + min;
    }

    public ArrayList<GridElement> get16DimensionalMap()
    {
        int[][] horizontalWalls = new int[17][17];
        int[][] verticalWalls = new int[17][17];

        int i =0;
        int j =0;
        int temp = 0;
        int countX = 0;
        int countY = 0;

        Boolean restart;

        do {
            restart = false;

            //On initialise avec aucun mur
            for (i = 0; i < 16; i++)
                for (j = 0; j < 16; j++)
                    horizontalWalls[i][j] = verticalWalls[i][j] = 0;

            //Création des bords
            for (i = 0; i < 16; i++) {
                horizontalWalls[i][0] = 1;
                horizontalWalls[i][16] = 1;
                verticalWalls[0][i] = 1;
                verticalWalls[16][i] = 1;
            }

            //Murs près de la bordure gauche
            horizontalWalls[0][getRandom(2, 7)] = 1;
            do {
                temp = getRandom(8, 15);
            }
            while (horizontalWalls[0][temp - 1] == 1 || horizontalWalls[0][temp] == 1 || horizontalWalls[0][temp + 1] == 1);
            horizontalWalls[0][temp] = 1;

            //Murs près de la bordure droite
            horizontalWalls[15][getRandom(2, 7)] = 1;
            do {
                temp = getRandom(8, 15);
            }
            while (horizontalWalls[15][temp - 1] == 1 || horizontalWalls[15][temp] == 1 || horizontalWalls[15][temp + 1] == 1);
            horizontalWalls[15][temp] = 1;

            //Murs près de la bordure haut
            verticalWalls[getRandom(2, 7)][0] = 1;
            do {
                temp = getRandom(8, 15);
            }
            while (verticalWalls[temp - 1][0] == 1 || verticalWalls[temp][0] == 1 || verticalWalls[temp + 1][0] == 1);
            verticalWalls[temp][0] = 1;

            //Murs près de la bordure bas
            verticalWalls[getRandom(2, 7)][15] = 1;
            do {
                temp = getRandom(8, 15);
            }
            while (verticalWalls[temp - 1][15] == 1 || verticalWalls[temp][15] == 1 || verticalWalls[temp + 1][15] == 1);
            verticalWalls[temp][15] = 1;

            //Dessin du carré du milieu
            horizontalWalls[7][7] = horizontalWalls[8][7] = horizontalWalls[7][9] = horizontalWalls[8][9] = 1;
            verticalWalls[7][7] = verticalWalls[7][8] = verticalWalls[9][7] = verticalWalls[9][8] = 1;

            for (int k = 0; k < 17; k++) {
                Boolean flag = false;
                int tempX = 0;
                int tempY = 0;
                int tempXv = 0;
                int tempYv = 0;

                long compteLoop1 = 0;
                do {
                    compteLoop1++;

                    //Choix de coordonnées aléatoires dans chaque quart de terrain de jeu
                    flag = false;
                    if (k < 4) {
                        tempX = getRandom(1, 7);
                        tempY = getRandom(1, 7);
                    } else if (k < 8) {
                        tempX = getRandom(8, 15);
                        tempY = getRandom(1, 7);
                    } else if (k < 12) {
                        tempX = getRandom(1, 7);
                        tempY = getRandom(8, 15);
                    } else if (k < 16) {
                        tempX = getRandom(8, 15);
                        tempY = getRandom(8, 15);
                    } else {
                        tempX = getRandom(1, 15);
                        tempY = getRandom(1, 15);
                    }

                    if (horizontalWalls[tempX][tempY] == 1 || horizontalWalls[tempX - 1][tempY] == 1 || horizontalWalls[tempX + 1][tempY] == 1)
                        flag = true;
                    if (horizontalWalls[tempX][tempY - 1] == 1 || horizontalWalls[tempX][tempY + 1] == 1)
                        flag = true;

                    if (verticalWalls[tempX][tempY] == 1 || verticalWalls[tempX + 1][tempY] == 1) {
                        flag = true;
                    }
                    if (verticalWalls[tempX][tempY - 1] == 1 || verticalWalls[tempX + 1][tempY - 1] == 1) {
                        flag = true;
                    }

                    if (!flag) {
                        //On compte le nombre de murs dans la même ligne/colonne
                        countX = countY = 0;
                        for (i = 1; i < 15; i++) {
                            if (horizontalWalls[i][tempY] == 1)
                                countX++;
                            if (horizontalWalls[tempX][i] == 1)
                                countY++;
                        }
                        if (tempY == 7 || tempY == 9) {
                            countX -= 2;
                        }
                        if (countX >= 2 || countY >= 2) //Si il y a trop de murs dans la même ligne/colonne, on abandonne
                            flag = true;
                    }

                    if (!flag) {


                        //Choix du 2ème mur du coin en cours de dessin
                        tempXv = tempX + getRandom(0, 1);
                        tempYv = tempY - getRandom(0, 1);

                        //On vérifie qu'il ne tombe pas dessus ou près de murs déja existant
                        if (verticalWalls[tempXv][tempYv] == 1 || verticalWalls[tempXv - 1][tempYv] == 1 || verticalWalls[tempXv + 1][tempYv] == 1)
                            flag = true;
                        if (verticalWalls[tempXv][tempYv - 1] == 1 || verticalWalls[tempXv][tempYv + 1] == 1)
                            flag = true;

                        if (horizontalWalls[tempXv][tempYv] == 1 || horizontalWalls[tempXv - 1][tempYv] == 1)
                            flag = true;

                        if (horizontalWalls[tempXv][tempYv - 1] == 1 || horizontalWalls[tempXv - 1][tempYv - 1] == 1)
                            flag = true;

                        if (verticalWalls[tempXv - 1][tempYv - 1] == 1 || verticalWalls[tempXv - 1][tempYv + 1] == 1)
                            flag = true;

                        if (verticalWalls[tempXv + 1][tempYv + 1] == 1 || verticalWalls[tempXv + 1][tempYv - 1] == 1)
                            flag = true;

                        if (!flag) {
                            //On compte le nombre de murs dans la même ligne/colonne
                            countX = countY = 0;
                            for (i = 1; i < 15; i++) {
                                if (verticalWalls[i][tempYv] == 1)
                                    countX++;
                                if (verticalWalls[tempXv][i] == 1)
                                    countY++;
                            }
                            if (tempXv == 7 || tempXv == 9) {
                                countY -= 2;
                            }
                            if (countX >= 2 || countY >= 2) //Si il y a trop de murs dans la même ligne/colonne, on abandonne
                                flag = true;
                        }

                    }

                    if (compteLoop1 > 1000) {
                        restart = true;
                    }

                } while (flag && !restart);
                horizontalWalls[tempX][tempY] = 1;
                verticalWalls[tempXv][tempYv] = 1;
            }
        }while(restart);



        Boolean flag;
        int cibleX = 0;
        int cibleY = 0;
        do{
            flag = false;
            cibleX = getRandom(0, 15);
            cibleY = getRandom(0, 15);

            if(horizontalWalls[cibleX][cibleY] == 0 && horizontalWalls[cibleX][cibleY+1] == 0)
                flag = true;
            if(verticalWalls[cibleX][cibleY] == 0 && verticalWalls[cibleX+1][cibleY] == 0)
                flag = true;
            if((cibleX == 7 && cibleY == 7) || (cibleX == 7 && cibleY == 8) || (cibleX == 8 && cibleY == 7) || (cibleX == 8 && cibleY == 8))
                flag = true;

        }while(flag);

        String typesOfCibles[] = {"cj","cr","cb", "cj", "cm"};


        ArrayList<GridElement> data = translateArraysToMap(horizontalWalls, verticalWalls);
        data.add(new GridElement(cibleX, cibleY, typesOfCibles[getRandom(0,4)]));

        String typesOfRobots[] = {"rr", "rb", "rj", "rv"};

        ArrayList<GridElement> robotsTemp = new ArrayList<GridElement>();

        int cX;
        int cY;

        for(String currentRobotType:typesOfRobots)
        {
            do {
                flag = false;
                cX = getRandom(0, 15);
                cY = getRandom(0, 15);

                for(GridElement robot:robotsTemp) {
                    if (robot.getX() == cX && robot.getY() == cY)
                        flag = true;
                }

                if((cX == 7 && cY == 7) || (cX == 7 && cY == 8) || (cX == 8 && cY == 7) || (cX == 8 && cY == 8))
                    flag = true;
                if(cX == cibleX && cY == cibleY)
                    flag = true;

            }while(flag);
            robotsTemp.add(new GridElement(cX, cY, currentRobotType));
        }
        data.addAll(robotsTemp);

        return data;
    }
}
