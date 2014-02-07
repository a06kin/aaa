package lv.aaa.Hedgehogs.scenes;

import android.hardware.SensorManager;
import lv.aaa.Hedgehogs.GameController;
import lv.aaa.Hedgehogs.ResourcesManager;
import org.andengine.entity.IEntity;
import org.andengine.entity.particle.BatchedSpriteParticleSystem;
import org.andengine.entity.particle.emitter.RectangleParticleEmitter;
import org.andengine.entity.particle.initializer.*;
import org.andengine.entity.particle.modifier.RotationParticleModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.UncoloredSprite;
import org.andengine.util.adt.color.Color;

public class MyParticles {

    private class MyGravityParticleInitializer<T extends IEntity> extends AccelerationParticleInitializer<T> {
        private MyGravityParticleInitializer() {
            super(0, -SensorManager.GRAVITY_EARTH);
        }
    }

    public MyParticles(Scene scene) {
        RectangleParticleEmitter particleEmitter = new RectangleParticleEmitter(100, GameController.CELL_SIZE * 6 + 380, GameController.CAMERA_WIDTH, 50);
        BatchedSpriteParticleSystem spriteParticleSystem = new BatchedSpriteParticleSystem(particleEmitter, 5, 10, 200,
                ResourcesManager.getInstance().getSpark1Region(), ResourcesManager.getInstance().vbom);
        spriteParticleSystem.addParticleInitializer(new ExpireParticleInitializer<UncoloredSprite>(20));
        spriteParticleSystem.addParticleInitializer(new ColorParticleInitializer<UncoloredSprite>(Color.WHITE));
        spriteParticleSystem.addParticleInitializer(new MyGravityParticleInitializer<UncoloredSprite>());
        spriteParticleSystem.addParticleInitializer(new ScaleParticleInitializer<UncoloredSprite>(0.5f));
        spriteParticleSystem.addParticleInitializer(new AlphaParticleInitializer<UncoloredSprite>(0.8f, 0.9f));
        spriteParticleSystem.addParticleModifier(new RotationParticleModifier<UncoloredSprite>(0, 10, 0, 2000));
//        scene.attachChild(spriteParticleSystem);
    }

}
