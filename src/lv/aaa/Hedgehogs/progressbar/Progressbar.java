package lv.aaa.Hedgehogs.progressbar;

import lv.aaa.Hedgehogs.ResourcesManager;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;

public class Progressbar extends Entity {

    private final Rectangle mBackgroundRectangle;
    private final Rectangle mProgressRectangle;
    private float pixelsPerPercentRatio;
    private Text text;

    public Progressbar(float pX, float pY) {
        this.setPosition(pX, pY);
        ITextureRegion progressBarRegion = ResourcesManager.getInstance().getProgressBarRegion();
        float width = progressBarRegion.getWidth();
        float height = progressBarRegion.getHeight() * 2;
        this.mBackgroundRectangle = new Rectangle(0, 0, width, height, ResourcesManager.getInstance().vbom);
        this.mProgressRectangle = new Rectangle(0, 0, width, height, ResourcesManager.getInstance().vbom);
        setBackColor(0, 0, 0, 0);
        setProgressColor(1, 0, 0, 1);
        this.attachChild(mBackgroundRectangle);
        this.attachChild(mProgressRectangle);
        this.attachChild(new Sprite(0, 0, width, height, progressBarRegion, ResourcesManager.getInstance().vbom));
        pixelsPerPercentRatio = width / 100;
        text = new Text(0, 0, ResourcesManager.getInstance().getFontAdOneToNoone6(),
                "1234567890/", ResourcesManager.getInstance().vbom);
        text.setScale(1);
        this.attachChild(text);
    }

    public Progressbar setBackColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
        this.mBackgroundRectangle.setColor(pRed, pGreen, pBlue, pAlpha);
        return this;
    }

    public Progressbar setProgressColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
        this.mProgressRectangle.setColor(pRed, pGreen, pBlue, pAlpha);
        return this;
    }

    public Progressbar setProgress(final float pProgress) {
        if(pProgress < 0) {
            this.mProgressRectangle.setWidth(0); //This is an internal check for my specific game, you can remove it.
        } else if(pProgress > 100) {
            this.mProgressRectangle.setWidth(100); //This is an internal check for my specific game, you can remove it.
        } else {
            float width = this.pixelsPerPercentRatio * pProgress;
            this.mProgressRectangle.setWidth(width);
            this.mProgressRectangle.setPosition(-(100 - width) / 2 + 1, mProgressRectangle.getY());
        }
        text.setText(String.valueOf((int)pProgress) + "/100");
        return this;
    }

}
