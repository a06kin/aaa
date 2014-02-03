package lv.aaa.Hedgehogs.scenes;

import android.os.Process;
import lv.aaa.Hedgehogs.GameController;
import lv.aaa.Hedgehogs.ScenesManager;
import lv.aaa.Hedgehogs.SpriteButton;
import lv.aaa.Hedgehogs.scenes.BaseScene;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

public class MenuScene extends BaseScene {

    private SpriteBackground menuBg;
    private Sprite play;
    private Sprite options;
    private Sprite exit;

    @Override
    public void createScene() {
        this.menuBg = new SpriteBackground(new Sprite(GameController.CAMERA_WIDTH * 0.5f,
                GameController.CAMERA_HEIGHT * 0.5f, GameController.CAMERA_WIDTH, GameController.CAMERA_HEIGHT,
                resourcesManager.getMenuBgRegion(), vbom));

        this.play = new SpriteButton(0, 70, resourcesManager.getPlayButtonRegion(), vbom) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                switch(pSceneTouchEvent.getAction()) {
                    case TouchEvent.ACTION_DOWN:
                        this.setScale(1.2f);
                        break;
                    case TouchEvent.ACTION_UP:
                        this.setScale(1.0f);
                        if (this.isTouchInSpriteArea(pTouchAreaLocalX, pTouchAreaLocalY)) {
                            ScenesManager.getInstance().createGameScene();
                        }
                        break;
                }
                return true;
            }
        };

        this.options = new SpriteButton(0, 0, resourcesManager.getOptionsButtonRegion(), vbom) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                switch(pSceneTouchEvent.getAction()) {
                    case TouchEvent.ACTION_DOWN:
                        this.setScale(1.2f);

                        break;
                    case TouchEvent.ACTION_UP:
                        this.setScale(1.0f);
                        break;
                }
                return true;
            }
        };

        this.exit = new SpriteButton(0, -70, resourcesManager.getExitButtonRegion(), vbom) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                switch(pSceneTouchEvent.getAction()) {
                    case TouchEvent.ACTION_DOWN:
                        this.setScale(1.2f);
                        break;
                    case TouchEvent.ACTION_UP:
                        this.setScale(1.0f);
                        if (this.isTouchInSpriteArea(pTouchAreaLocalX, pTouchAreaLocalY)) {
                            Process.killProcess(Process.myPid());
                        }
                        break;
                }
                return true;
            }
        };
        Scene childScene = new Scene();
        childScene.attachChild(play);
        childScene.registerTouchArea(play);
        childScene.attachChild(options);
        childScene.registerTouchArea(options);
        childScene.attachChild(exit);
        childScene.registerTouchArea(exit);
        childScene.setBackground(menuBg);
        childScene.setBackgroundEnabled(true);
        childScene.setTouchAreaBindingOnActionDownEnabled(true);

        childScene.setPosition(GameController.CAMERA_WIDTH / 2, GameController.CAMERA_HEIGHT / 2);
        setChildScene(childScene);
    }

    @Override
    public void onBackKeyPressed() {
        android.os.Process.killProcess(Process.myPid());
    }

    @Override
    public ScenesManager.SceneType getSceneType() {
        return ScenesManager.SceneType.SCENE_MENU;
    }

    @Override
    public void disposeScene() {
        play.detachSelf();
        play.dispose();
        options.detachSelf();
        options.dispose();
        exit.detachSelf();
        exit.dispose();
        this.detachSelf();
        this.dispose();
    }

}
