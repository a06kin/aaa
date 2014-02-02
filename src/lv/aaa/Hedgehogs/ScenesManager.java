package lv.aaa.Hedgehogs;

import lv.aaa.Hedgehogs.scenes.BaseScene;
import lv.aaa.Hedgehogs.scenes.MenuScene;
import lv.aaa.Hedgehogs.scenes.SplashScene;
import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.*;

public class ScenesManager {

    private static final ScenesManager INSTANCE = new ScenesManager();

    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
    private BaseScene loadingScene;
    private BaseScene currentScene;
    private SceneType currentSceneType = SceneType.SCENE_SPLASH;

    private Engine engine = ResourcesManager.getInstance().engine;

    public enum SceneType {
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
    }

    public void setScene(BaseScene scene) {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }

    public void setScene(SceneType sceneType) {
        switch (sceneType) {
            case SCENE_MENU:
                setScene(menuScene);
                break;
            case SCENE_GAME:
                setScene(gameScene);
                break;
            case SCENE_SPLASH:
                setScene(splashScene);
                break;
            case SCENE_LOADING:
                setScene(loadingScene);
                break;
            default:
                break;
        }
    }

    public void createMenuScene() {
        ResourcesManager.getInstance().loadMenuResources();
        menuScene = new MenuScene();
        setScene(menuScene);
        disposeSplashScene();
    }

    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }

    public void disposeSplashScene() {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }

    public SceneType getCurrentSceneType() {
        return currentSceneType;
    }

    public BaseScene getCurrentScene() {
        return currentScene;
    }

    public static ScenesManager getInstance() {
        return INSTANCE;
    }
}
