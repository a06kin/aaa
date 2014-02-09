package lv.aaa.Hedgehogs;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.StrokeFont;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

public class ResourcesManager {

    private static final ResourcesManager INSTANCE = new ResourcesManager();

    public Engine engine;
    public GameController gameController;
    public Camera camera;
    public VertexBufferObjectManager vbom;

    private BuildableBitmapTextureAtlas cellBackgrounds;
    private BuildableBitmapTextureAtlas menuTextureAtlas;
    private BuildableBitmapTextureAtlas bg;
    private BitmapTextureAtlas splashTextureAtlas;

    private ITextureRegion menuBgRegion;
    private ITextureRegion playButtonRegion;
    private ITextureRegion optionsButtonRegion;
    private ITextureRegion exitButtonRegion;

    private ITextureRegion splashRegion;
    private ITextureRegion cellRegion;
    private ITextureRegion cellPressedRegion;
    private ITextureRegion cellHoverRegion;
    private ITextureRegion flagRegion;
    private ITextureRegion checkRegion;

    private ITextureRegion gameBgRegion;
    private ITextureRegion boardBgRegion;
    private ITextureRegion spark1Region;
    private ITextureRegion spark2Region;

    private ITextureRegion cakeRegion;
    private ITextureRegion donutRegion;
    private ITextureRegion blueberryRegion;
    private ITextureRegion attackRegion;
    private ITextureRegion progressBarRegion;

    private StrokeFont fontAdOneToNoone36;
    private Font fontAdOneToNoone6;

    private TiledTextureRegion loadingRegion;

    private TiledTextureRegion playerRegion;

    public TiledTextureRegion getLoadingRegion() {
        return loadingRegion;
    }

    public TiledTextureRegion getPlayerRegion() {
        return playerRegion;
    }

    public Font getFontAdOneToNoone6() {
        return fontAdOneToNoone6;
    }

    public StrokeFont getFontAdOneToNoone36() {
        return fontAdOneToNoone36;
    }

    public ITextureRegion getProgressBarRegion() {
        return progressBarRegion;
    }

    public ITextureRegion getAttackRegion() {
        return attackRegion;
    }

    public ITextureRegion getDonutRegion() {
        return donutRegion;
    }

    public ITextureRegion getCakeRegion() {
        return cakeRegion;
    }

    public ITextureRegion getBlueberryRegion() {
        return blueberryRegion;
    }

    public ITextureRegion getGameBgRegion() {
        return gameBgRegion;
    }

    public ITextureRegion getBoardBgRegion() {
        return boardBgRegion;
    }

    public ITextureRegion getSpark1Region() {
        return spark1Region;
    }

    public ITextureRegion getSpark2Region() {
        return spark2Region;
    }

    public ITextureRegion getCellRegion() {
        return cellRegion;
    }

    public ITextureRegion getCellPressedRegion() {
        return cellPressedRegion;
    }

    public ITextureRegion getCellHoverRegion() {
        return cellHoverRegion;
    }

    public ITextureRegion getFlagRegion() {
        return flagRegion;
    }

    public ITextureRegion getCheckRegion() {
        return checkRegion;
    }

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
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        FontFactory.setAssetBasePath("fonts/");

        BitmapTextureAtlas fontAdOneToNooneTexture = new BitmapTextureAtlas(gameController.getTextureManager(),
                1024, 128, TextureOptions.BILINEAR);
        fontAdOneToNoone36 = FontFactory.createStrokeFromAsset(gameController.getFontManager(),
                fontAdOneToNooneTexture, gameController.getAssets(), "AnOdeToNoone.ttf", 36, true,
                android.graphics.Color.WHITE, 1, android.graphics.Color.rgb(0, 0, 0));
        fontAdOneToNoone36.load();
        BitmapTextureAtlas fontAdOneToNooneTexture2 = new BitmapTextureAtlas(gameController.getTextureManager(),
                128, 128, TextureOptions.BILINEAR);
        fontAdOneToNoone6 = FontFactory.createFromAsset(gameController.getFontManager(), fontAdOneToNooneTexture2,
                gameController.getAssets(), "AnOdeToNoone.ttf", 12, true, android.graphics.Color.WHITE);
        fontAdOneToNoone6.load();
        fontAdOneToNooneTexture.load();
        fontAdOneToNooneTexture2.load();

        bg = new BuildableBitmapTextureAtlas(gameController.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        gameBgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bg, gameController, "game_bg.png");
        loadingRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bg, gameController, "loading.png", 4, 1);
        playerRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bg, gameController, "player.png", 3, 4);

        cellBackgrounds = new BuildableBitmapTextureAtlas(gameController.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        cellRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "cell.png");
        cellPressedRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "cell_pressed.png");
        cellHoverRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "selection.png");
        flagRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "flag.png");
        checkRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "check.png");
        spark1Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "spark1.png");
        spark2Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "spark2.png");
        boardBgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "board_bg.png");
        cakeRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "cake.png");
        donutRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "donut.png");
        blueberryRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "blueberry.png");
        attackRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "attack.png");
        progressBarRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cellBackgrounds, gameController, "bar_top.png");

        try {
            this.bg.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
            this.bg.load();
            this.cellBackgrounds.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
            this.cellBackgrounds.load();
        } catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }
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
