package lv.aaa.Hedgehogs.scenes;

import lv.aaa.Hedgehogs.Board;
import lv.aaa.Hedgehogs.CellPopup;
import lv.aaa.Hedgehogs.ScenesManager;
import org.andengine.entity.scene.background.Background;
import org.andengine.util.adt.color.Color;

public class GameScene extends BaseScene {

    public CellPopup cellPopup;

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);
        this.sortChildren();
    }

    @Override
    public void createScene() {
        /*this.setBackground(new SpriteBackground(new Sprite(GameController.CAMERA_WIDTH * 0.5f,
                GameController.CAMERA_HEIGHT * 0.5f, GameController.CAMERA_WIDTH, GameController.CAMERA_HEIGHT,
                resourcesManager.getMenuBgRegion(), vbom)));*/

        this.setBackground(new Background(Color.BLACK));
        new Board(this);
        cellPopup = new CellPopup(this);
        new MyParticles(this);
        this.setTouchAreaBindingOnActionDownEnabled(true);
    }

    @Override
    public void onBackKeyPressed() {
        ScenesManager.getInstance().createMenuScene();
    }

    @Override
    public ScenesManager.SceneType getSceneType() {
        return ScenesManager.SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {

    }

}
