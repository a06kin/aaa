package lv.aaa.Hedgehogs.scenes;

import lv.aaa.Hedgehogs.ResourcesManager;
import lv.aaa.Hedgehogs.board.Board;
import lv.aaa.Hedgehogs.board.CellPopup;
import lv.aaa.Hedgehogs.GameController;
import lv.aaa.Hedgehogs.ScenesManager;
import lv.aaa.Hedgehogs.progressbar.Progressbar;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;

public class GameScene extends BaseScene {

    public CellPopup cellPopup;
    public Progressbar progressbar;

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
        progressbar = new Progressbar(-10, 224);
        progressbar.setProgress(97);
        this.attachChild(progressbar);
        progressbar = new Progressbar(-10, 208);
        progressbar.setProgressColor(0, 0, 1, 1);
        progressbar.setProgress(33);
        this.attachChild(progressbar);
        AnimatedSprite player = new AnimatedSprite(-10, 168, ResourcesManager.getInstance().getPlayerRegion(),
                ResourcesManager.getInstance().vbom);
        player.animate(new long[]{200}, new int[]{5}, false);
        player.setScale(3);
        this.attachChild(player);
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
