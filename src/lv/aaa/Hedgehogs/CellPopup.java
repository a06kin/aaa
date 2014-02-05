package lv.aaa.Hedgehogs;

import android.opengl.GLES20;
import android.util.Log;
import lv.aaa.Hedgehogs.scenes.GameScene;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class CellPopup extends Sprite {

    public Cell currentCell = null;
    private float offset = ResourcesManager.getInstance().getCellHoverRegion().getWidth() / 2;

    public CellPopup(GameScene scene) {
        this(0, 0, ResourcesManager.getInstance().getCellHoverRegion(), ResourcesManager.getInstance().vbom, scene);
    }

    public CellPopup(float pX, float pY, ITextureRegion pTextureRegion,
                     VertexBufferObjectManager pVertexBufferObjectManager, GameScene scene) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        this.setVisible(false);
        this.setZIndex(99);
        this.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(
                new AlphaModifier(0.4f, 1, 0), new AlphaModifier(0.2f, 0, 1)
        )));
        Sprite check = new SpriteButton(this.getX() + offset - 40, offset,
                ResourcesManager.getInstance().getCheckRegion(), ResourcesManager.getInstance().vbom) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                if (GameController.isTouchPausePassed()) {
//                    TODO
                    Log.i("ACTION", "CHECK");
                }
                return false;
            }
        };
        Sprite setFlag = new SpriteButton(this.getX() + offset + 40, offset,
                ResourcesManager.getInstance().getFlagRegion(), ResourcesManager.getInstance().vbom) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                if (GameController.isTouchPausePassed()) {
//                    TODO
                    Log.i("ACTION", "SetFlag");
                }
                return false;
            }
        };
        this.attachChild(check);
        this.attachChild(setFlag);
        scene.registerTouchArea(check);
        scene.registerTouchArea(setFlag);
    }
}
