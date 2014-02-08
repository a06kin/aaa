package lv.aaa.Hedgehogs.board;

import lv.aaa.Hedgehogs.GameController;
import lv.aaa.Hedgehogs.SpriteButton;
import lv.aaa.Hedgehogs.scenes.GameScene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Cell extends SpriteButton {

    private GameScene scene;

    public Cell(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager,
                GameScene scene) {
        super(pX, pY, GameController.CELL_SIZE, GameController.CELL_SIZE, pTextureRegion, pVertexBufferObjectManager);
        this.scene = scene;
        this.scene.attachChild(this);
    }

    @Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX,
                                 final float pTouchAreaLocalY) {
        if (GameController.isTouchPausePassed()) {
            switch(pSceneTouchEvent.getAction()) {
                case TouchEvent.ACTION_DOWN:
                    if (scene.cellPopup.currentCell != null && scene.cellPopup.currentCell.equals(this)) {
                        scene.cellPopup.hidePopup();
                        scene.cellPopup.currentCell = null;
                    } else if (scene.cellPopup.currentCell == null) {
                        scene.cellPopup.currentCell = this;
                        scene.cellPopup.showPopup(this.getX(), this.getY());
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
        return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
    }

}
