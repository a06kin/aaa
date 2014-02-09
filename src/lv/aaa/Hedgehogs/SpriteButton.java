package lv.aaa.Hedgehogs;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class SpriteButton extends Sprite {

    public SpriteButton(float pX, float pY, float width, float height, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, width, height, pTextureRegion, pVertexBufferObjectManager);
    }

    protected boolean isTouchInSpriteArea(float pTouchAreaLocalX, float pTouchAreaLocalY) {
//      TODO don't work properly
        boolean result = (this.getWidth() / 2 < pTouchAreaLocalX || this.getHeight() < pTouchAreaLocalY || pTouchAreaLocalX < 0
                || pTouchAreaLocalY < 0);
        return !result;
    }
}
