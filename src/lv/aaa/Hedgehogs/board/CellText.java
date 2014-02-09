package lv.aaa.Hedgehogs.board;

import lv.aaa.Hedgehogs.GameController;
import lv.aaa.Hedgehogs.ResourcesManager;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.StrokeFont;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class CellText extends Sprite {

    public CellText(float pX, float pY, ITextureRegion pTextureRegion,
                    VertexBufferObjectManager pVertexBufferObjectManager, Scene scene, String code) {
        super(pX, pY, GameController.CELL_SIZE, GameController.CELL_SIZE, pTextureRegion, pVertexBufferObjectManager);
        StrokeFont fontAdOneToNoone = ResourcesManager.getInstance().getFontAdOneToNoone36();
        fontAdOneToNoone.prepareLetters("1234567890".toCharArray());
        Text text = new Text(GameController.CELL_SIZE / 2, GameController.CELL_SIZE / 2 - 1,
                fontAdOneToNoone, "0", 1, ResourcesManager.getInstance().vbom);
        String[] splitted = code.split("-");
        ITextureRegion toAdd = null;
        if (splitted[0].equals("x")) {
            toAdd = ResourcesManager.getInstance().getAttackRegion();
            splitted[0] = "";
        } else {
            if (splitted.length == 2) {
                switch (splitted[1].toCharArray()[0]) {
                    case 'a':
                        toAdd = ResourcesManager.getInstance().getCakeRegion();
                        break;
                    case 'b':
                        toAdd = ResourcesManager.getInstance().getDonutRegion();
                        break;
                    case 'c':
                        toAdd = ResourcesManager.getInstance().getBlueberryRegion();
                        break;
                }
            }
        }
        if (toAdd != null) {
            this.attachChild(new Sprite(GameController.CELL_SIZE / 2, GameController.CELL_SIZE / 2,
                    GameController.CELL_SIZE, GameController.CELL_SIZE, toAdd, ResourcesManager.getInstance().vbom));
        }
        text.setText(splitted[0]);
        this.attachChild(text);
        scene.attachChild(this);
    }
}
