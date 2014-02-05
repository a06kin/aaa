package lv.aaa.Hedgehogs;

import lv.aaa.Hedgehogs.scenes.GameScene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Cell extends SpriteButton {

    private GameScene scene;

    public Cell(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager,
                GameScene scene) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        this.scene = scene;
    }

    @Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX,
                                 final float pTouchAreaLocalY) {
        if (GameController.isTouchPausePassed()) {
            switch(pSceneTouchEvent.getAction()) {
                case TouchEvent.ACTION_DOWN:
                    if (scene.cellPopup.currentCell != null && scene.cellPopup.currentCell.equals(this)) {
                        scene.cellPopup.currentCell = null;
                        scene.cellPopup.setVisible(false);
                    } else if (scene.cellPopup.currentCell == null) {
                        scene.cellPopup.currentCell = this;
                        scene.cellPopup.setPosition(this.getX(), this.getY());
                        scene.cellPopup.setVisible(true);
                    }
                    break;
                case TouchEvent.ACTION_MOVE:
                    break;
                case TouchEvent.ACTION_OUTSIDE:
                    break;
                case TouchEvent.ACTION_UP:
                    break;
            }
        }
        return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
                pTouchAreaLocalY);
    }

}
