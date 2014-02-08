package lv.aaa.Hedgehogs.board;

import android.opengl.GLES20;
import android.widget.Toast;
import lv.aaa.Hedgehogs.GameController;
import lv.aaa.Hedgehogs.ResourcesManager;
import lv.aaa.Hedgehogs.SpriteButton;
import lv.aaa.Hedgehogs.scenes.GameScene;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class CellPopup extends Sprite {

    private GameScene scene;
    public Cell currentCell = null;
    private Sprite check, setFlag;
    private SequenceEntityModifier sequenceEntityModifier;
    private float offset = GameController.CELL_SIZE / 2;
    private boolean isLoading = false;

    public CellPopup(GameScene scene) {
        this(0, 0, ResourcesManager.getInstance().getCellHoverRegion(), ResourcesManager.getInstance().vbom, scene);
    }

    public void hidePopup() {
        this.setVisible(false);
        scene.setOnAreaTouchTraversalBackToFront();
    }

    public void showPopup(float xPos, float yPos) {
        this.setPosition(xPos, yPos);
        this.setVisible(true);
        this.check.setScale(0);
        this.setFlag.setScale(0);
        scene.registerTouchArea(check);
        scene.registerTouchArea(setFlag);
        scene.setOnAreaTouchTraversalFrontToBack();
        sequenceEntityModifier.reset();
    }

    public CellPopup(float pX, float pY, ITextureRegion pTextureRegion,
                     VertexBufferObjectManager pVertexBufferObjectManager, final GameScene scene) {
        super(pX, pY, GameController.CELL_SIZE, GameController.CELL_SIZE, pTextureRegion, pVertexBufferObjectManager);
        this.scene = scene;
        this.sequenceEntityModifier = new SequenceEntityModifier(
                new ScaleModifier(0.5f, 0, 1.3f),
                new ScaleModifier(0.1f, 1.3f, 1));
        sequenceEntityModifier.setAutoUnregisterWhenFinished(false);
        this.setVisible(false);
        this.setZIndex(99);
        this.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(
                new AlphaModifier(0.4f, 1, 0), new AlphaModifier(0.2f, 0, 1)
        )));
        check = new SpriteButton(this.getX() + offset - GameController.CELL_SIZE, offset, GameController.CELL_SIZE, GameController.CELL_SIZE,
                ResourcesManager.getInstance().getCheckRegion(), ResourcesManager.getInstance().vbom) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                if (GameController.isTouchPausePassed()) {
                    if (currentCell != null && !isLoading) {
                        isLoading = true;
                        final float currentPosX = currentCell.getX();
                        final float currentPosY = currentCell.getY();
                        final AnimatedSprite loading = new AnimatedSprite(currentPosX, currentPosY,
                                ResourcesManager.getInstance().getLoadingRegion(), ResourcesManager.getInstance().vbom);
                        loading.animate(90);
                        loading.setScale(0.65f);
                        scene.attachChild(loading);
                        CellPopup.this.hidePopup();
                        scene.unregisterTouchArea(currentCell);
                        ResourcesManager.getInstance().engine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
                            public void onTimePassed(final TimerHandler pTimerHandler) {
                                ResourcesManager.getInstance().engine.unregisterUpdateHandler(pTimerHandler);
                                scene.detachChild(currentCell);
                                currentCell = null;
                                new CellText(currentPosX, currentPosY, ResourcesManager.getInstance().getCellPressedRegion(),
                                        ResourcesManager.getInstance().vbom, scene, "0");
                                scene.detachChild(loading);
                                isLoading = false;
//                                TODO send request and update table
                            }
                        }));
                    }
                    isLoading = false;
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
                        pTouchAreaLocalY);
            }
        };
        setFlag = new SpriteButton(this.getX() + offset + GameController.CELL_SIZE, offset, GameController.CELL_SIZE, GameController.CELL_SIZE,
                ResourcesManager.getInstance().getFlagRegion(), ResourcesManager.getInstance().vbom) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                if (GameController.isTouchPausePassed()) {
//                    TODO
                    ResourcesManager.getInstance().gameController.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ResourcesManager.getInstance().gameController.getBaseContext(),
                                    "SETFLAG button is pressed", Toast.LENGTH_SHORT);
                        }
                    });
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
                        pTouchAreaLocalY);
            }
        };
        this.attachChild(check);
        this.attachChild(setFlag);
        check.registerEntityModifier(sequenceEntityModifier);
        setFlag.registerEntityModifier(sequenceEntityModifier);
        scene.attachChild(this);
    }
}
