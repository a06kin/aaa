package lv.aaa.Hedgehogs.board;

import lv.aaa.Hedgehogs.GameController;
import lv.aaa.Hedgehogs.ResourcesManager;
import lv.aaa.Hedgehogs.scenes.GameScene;
import org.andengine.entity.sprite.Sprite;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Board {
    private String[][] board;
    private int size;
    private GameScene scene;

    public Board(GameScene scene) {
        this.scene = scene;
        Sprite boardBg = new Sprite(150, -150,
                ResourcesManager.getInstance().getBoardBgRegion(), ResourcesManager.getInstance().vbom);
        this.scene.attachChild(boardBg);
        board = askBoard();
//        this.size = GameController.CELL_COUNT_ROW;
//        board = Generator.generateFullBoard();
        drawBoard();
    }

    private void drawBoard() {
        this.scene.setPosition((GameController.CAMERA_WIDTH - GameController.CELL_SIZE * this.size) / 2 + GameController.CELL_SIZE / 2,
                GameController.CELL_SIZE * this.size + 30);
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                if (board[y][x].equals("c")) {
                    Cell cell = new Cell(GameController.CELL_SIZE * x, -GameController.CELL_SIZE * y, ResourcesManager.getInstance().getCellRegion(),
                            ResourcesManager.getInstance().vbom, scene);
                    this.scene.registerTouchArea(cell);
                } else {
                    new CellText(GameController.CELL_SIZE * x, -GameController.CELL_SIZE * y, ResourcesManager.getInstance().getCellPressedRegion(),
                            ResourcesManager.getInstance().vbom, scene, board[y][x]);
                }
            }
        }
    }

    private String[][] askBoard() {
//        TODO RETRIEVE JSON FROM SERVER
        String json = "{\"size\":6," +
                "\"data\":[" +
                "[\"1\",\"c\",\"1-a\",\"c\",\"0\",\"0-c\"]," +
                "[\"c\",\"x\",\"c\",\"c\",\"c\",\"0\"]," +
                "[\"c\",\"3\",\"3-b\",\"c\",\"c\",\"c\"]," +
                "[\"c\",\"c\",\"c\",\"x\",\"c\",\"0\"]," +
                "[\"c\",\"x\",\"c\",\"c\",\"c\",\"c\"]," +
                "[\"c\",\"2-b\",\"2\",\"1-c\",\"c\",\"0-a\"]" +
                "]}";

        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject)parser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray array;
        String[][] result;
        this.size = Integer.parseInt(obj.get("size").toString());
        array = (JSONArray) obj.get("data");
        result = new String[size][size];
        for (int y = 0; y < array.size(); y++) {
            JSONArray temp = (JSONArray) array.get(y);
            for (int x = 0; x < temp.size(); x++) {
                result[y][x] = temp.get(x).toString();
            }
        }
        return result;
    }
}
