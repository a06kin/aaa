package lv.aaa.Hedgehogs.scenes;

import lv.aaa.Hedgehogs.Board;
import lv.aaa.Hedgehogs.CellPopup;
import lv.aaa.Hedgehogs.GameController;
import lv.aaa.Hedgehogs.ScenesManager;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;

public class GameScene extends BaseScene {

    public CellPopup cellPopup;

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);
        this.sortChildren();
    }

    @Override
    public void createScene() {
        /*this.setBackground(new SpriteBackground());*/
        SpriteBackground spriteBackground = new SpriteBackground(1.0f, 1.0f, 1.0f, new Sprite(GameController.CAMERA_WIDTH * 0.5f,
                GameController.CAMERA_HEIGHT * 0.5f, GameController.CAMERA_WIDTH, GameController.CAMERA_HEIGHT,
                resourcesManager.getGameBgRegion(), vbom));
        this.setBackground(spriteBackground);
        this.setBackgroundEnabled(true);

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
