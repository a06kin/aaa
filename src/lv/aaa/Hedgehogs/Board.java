package lv.aaa.Hedgehogs;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Board {
    private String[][] board;
    private int size;
    Scene scene;

    public Board(Scene scene) {
        this.scene = scene;
        board = askBoard();
        drawBoard(board);
    }

    private void drawBoard(String[][] board) {
        float width = ResourcesManager.getInstance().getCellRegion().getWidth();
        Scene childScene = new Scene();
        childScene.setPosition((GameController.CAMERA_WIDTH - width * this.size) / 2 + width / 2,
                GameController.CAMERA_HEIGHT - (GameController.CAMERA_HEIGHT - width * this.size) / 2 - width / 2);
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                if (board[y][x].equals("c")) {
                    childScene.attachChild(new Sprite(width * x, -width * y, ResourcesManager.getInstance().getCellRegion(),
                            ResourcesManager.getInstance().vbom));
                } else {
                    childScene.attachChild(new Sprite(width * x, -width * y, ResourcesManager.getInstance().getCellPressedRegion(),
                            ResourcesManager.getInstance().vbom));
                }
            }
        }
        scene.setChildScene(childScene);
    }

    private String[][] askBoard() {
//        TODO RETRIEVE JSON FROM SERVER
        String json = "{\"size\":6," +
                "\"data\":[" +
                "[\"1\",\"c\",\"1\",\"c\",\"0\",\"0\"]," +
                "[\"c\",\"x\",\"c\",\"c\",\"c\",\"0\"]," +
                "[\"c\",\"3\",\"3\",\"c\",\"c\",\"c\"]," +
                "[\"c\",\"c\",\"c\",\"c\",\"c\",\"0\"]," +
                "[\"c\",\"x\",\"c\",\"c\",\"c\",\"c\"]," +
                "[\"c\",\"2\",\"2\",\"1\",\"c\",\"0\"]" +
                "]}";


        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject)parser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray array;
        String[][] result = null;
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
