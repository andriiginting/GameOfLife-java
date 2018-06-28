package com.gojek.GojekGol;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameOfLife {

    public static void main(String[] args) {
        Cell cell = new Cell();

        String inputFileName = "./";
        inputFileName += args[0];

        int[][] gameOfLifeGrid = inputReader(inputFileName);

        for (int[] aGameOfLifeGrid : gameOfLifeGrid) {
            for (int col = 0; col < gameOfLifeGrid[0].length; col++) {
                System.out.print(aGameOfLifeGrid[col] + " ");
            }
            System.out.println();
        }

        System.out.println();


        int[][] newLifeGrid = new int[gameOfLifeGrid.length][gameOfLifeGrid[0].length];

        int counterOne = 0;

        while (counterOne < 10) {

            for (int row = 0; row < gameOfLifeGrid.length; row++) {
                for (int col = 0; col < gameOfLifeGrid[0].length; col++) {
                    if (gameOfLifeGrid[row][col] == 0) {
                        newLifeGrid[row][col] = cell.reproduce(gameOfLifeGrid, row, col);
                    } else {
                        newLifeGrid[row][col] = cell.livesRules(gameOfLifeGrid, row, col);
                    }
                }
            }

            for (int row = 0; row < newLifeGrid.length; row++) {
                System.arraycopy(newLifeGrid[row], 0, gameOfLifeGrid[row], 0, newLifeGrid[0].length);
            }

            for (int[] aNewLifeGrid : newLifeGrid) {
                for (int col = 0; col < newLifeGrid[0].length; col++) {
                    if (aNewLifeGrid[col] == 0) {
                        System.out.print("  ");
                    } else {
                        System.out.print("1 ");
                    }
                }
                System.out.println();
            }

            System.out.println();

            try {
                Thread.sleep(1000);
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } catch (InterruptedException ignored) {
            }

            counterOne++;

        }
    }

    private static int[][] inputReader(String fileName) {
        Scanner fileReader = null;
        int numRow = 0;
        int numCols = 0;

        try {
            fileReader = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }

        String inputLine;

        if (fileReader != null && fileReader.hasNextLine()) {
            inputLine = fileReader.nextLine();
            numRow = Integer.parseInt(inputLine);
        }

        if (fileReader != null && fileReader.hasNextLine()) {
            inputLine = fileReader.nextLine();
            numCols = Integer.parseInt(inputLine);
        }

        int[][] lifeArr = new int[numRow][numCols];
        int counter = 0;

        String patternLine;

        if (fileReader != null) {
            while (fileReader.hasNextLine() && counter < numRow) {
                inputLine = fileReader.nextLine();
                patternLine = inputLine.replaceAll(",", "");

                for (int i = 0; i < numCols; i++) {
                    lifeArr[counter][i] = patternLine.charAt(i) - '0';
                }
                counter++;
            }
        }

        fileReader.close();

        return lifeArr;

    }
}
