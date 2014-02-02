package lv.aaa.Hedgehogs;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

public class ResourcesManager {

    private static final ResourcesManager INSTANCE = new ResourcesManager();

    public Engine engine;
    public GameController gameController;
    public Camera camera;
    public VertexBufferObjectManager vbom;

    private ITextureRegion splashRegion;

    public ITextureRegion getMenuBgRegion() {
        return menuBgRegion;
    }

    public ITextureRegion getPlayButtonRegion() {
        return playButtonRegion;
    }

    public ITextureRegion getOptionsButtonRegion() {
        return optionsButtonRegion;
    }

    public ITextureRegion getExitButtonRegion() {
        return exitButtonRegion;
    }

    public ITextureRegion getSplashRegion() {
        return splashRegion;
    }

    private ITextureRegion menuBgRegion;
    private ITextureRegion playButtonRegion;
    private ITextureRegion optionsButtonRegion;
    private ITextureRegion exitButtonRegion;

    private BuildableBitmapTextureAtlas menuTextureAtlas;
    private BitmapTextureAtlas splashTextureAtlas;

    public void loadMenuResources() {
        loadMenuGraphics();
        loadMenuAudio();
    }

    private void loadMenuGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        menuTextureAtlas = new BuildableBitmapTextureAtlas(gameController.getTextureManager(), 2048, 1024, TextureOptions.NEAREST_PREMULTIPLYALPHA);
        menuBgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameController, "main_scene.png");
        playButtonRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameController, "menu_play.png");
        optionsButtonRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameController, "menu_options.png");
        exitButtonRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameController, "menu_exit.png");

        try {
            this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(1, 1, 0));
            this.menuTextureAtlas.load();
        }
        catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }
    }

    private void loadMenuAudio() {

    }

    public void loadGameResources() {
        loadGameGraphics();
        loadGameFonts();
        loadGameAudio();
    }

    private void loadGameGraphics() {

    }

    private void loadGameFonts() {

    }

    private void loadGameAudio() {

    }

    public void loadSplashScreen() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new BitmapTextureAtlas(gameController.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
        splashRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                splashTextureAtlas, gameController, "broken_heart.png", 0, 0);
        splashTextureAtlas.load();
    }

    public void unloadSplashScreen() {
        splashRegion = null;
        splashTextureAtlas.unload();
    }

    public static void prepareManager(Engine engine, GameController gameController,
                                      Camera camera, VertexBufferObjectManager vbom) {
        getInstance().engine = engine;
        getInstance().gameController = gameController;
        getInstance().camera = camera;
        getInstance().vbom = vbom;
    }

    public static ResourcesManager getInstance() {
        return INSTANCE;
    }
}
