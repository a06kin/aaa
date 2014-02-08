package lv.aaa.Hedgehogs;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    public static ArrayList<Integer> getMinesIndexes() {
        Random random = new Random(System.currentTimeMillis());
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (result.size() != GameController.MINES_COUNT) {
            int rand = random.nextInt(GameController.CELL_COUNT_ROW * GameController.CELL_COUNT_ROW);
            if (!result.contains(rand)) {
                result.add(rand);
            }
        }
        System.out.println(result);
        return result;
    }

    public static String[][] generateBoardWithMines() {
        ArrayList<Integer> indexes = Generator.getMinesIndexes();
        String[][] board = new String[GameController.CELL_COUNT_ROW][GameController.CELL_COUNT_ROW];
        int cell = 0;
        for (int y = 0; y < GameController.CELL_COUNT_ROW; y++) {
            for (int x = 0; x < GameController.CELL_COUNT_ROW; x++) {
                if (indexes.contains(cell)) {
                    board[y][x] = "x";
                } else {
                    board[y][x] = "";
                }
                cell++;
            }
        }
        return board;
    }

    public static String[][] generateFullBoard() {
        int minX = 0;
        int minY = 0;
        int maxX = GameController.CELL_COUNT_ROW - 1;
        int maxY = GameController.CELL_COUNT_ROW - 1;

        Random random = new Random(System.currentTimeMillis());
        char[] items = {'a', 'b', 'c'};

        String[][] board = Generator.generateBoardWithMines();
        for (int y = 0; y < GameController.CELL_COUNT_ROW; y++) {
            for (int x = 0; x < GameController.CELL_COUNT_ROW; x++) {
                if (!board[y][x].equals("x")) {
                    int startPosX = (x - 1 < minX) ? x : x - 1;
                    int startPosY = (y - 1 < minY) ? y : y - 1;
                    int endPosX = (x + 1 > maxX) ? x : x + 1;
                    int endPosY = (y + 1 > maxY) ? y : y + 1;
                    int count = 0;
                    for (int rowNum = startPosX; rowNum <= endPosX; rowNum++) {
                        for (int colNum = startPosY; colNum <= endPosY; colNum++) {
                            if (board[colNum][rowNum].equals("x")) {
                                count++;
                            }
                        }
                    }

                    int index = random.nextInt(8);
                    String minesCount = String.valueOf(count);
                    if (index < items.length ) {
                        minesCount = minesCount + "-" + items[index];
                    }
                    board[y][x] = String.valueOf(minesCount);
                }
            }
        }
        return board;
    }

}
