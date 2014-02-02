package lv.aaa.Hedgehogs.scenes;

import lv.aaa.Hedgehogs.Board;
import lv.aaa.Hedgehogs.ScenesManager;

public class GameScene extends BaseScene {

    @Override
    public void createScene() {
        new Board(this);
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
