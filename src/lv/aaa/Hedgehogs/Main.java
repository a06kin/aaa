package lv.aaa.Hedgehogs;

public class Main {

    public static void main(String[] args) {
        String[][] result = Generator.generateFullBoard();
        for (int y = 0; y < GameController.CELL_COUNT_ROW; y++) {
            for (int x = 0; x < GameController.CELL_COUNT_ROW; x++) {
                System.out.print(result[y][x] + " ");
            }
            System.out.println("");
        }
    }

}
