package GameGDX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Particle extends Actor {
    public ParticleEffect pe;
    public boolean isLoop = true, isStop = true;

    public Particle(String name, float x, float y) {
        try {
            pe = new ParticleEffect();
            pe.load(Loader.mapPar.get(name), Loader.rootParticle);
            pe.setPosition(x, y);
        } catch (Exception ex) {
        }
    }

    public void Start() {
        if (pe == null) return;
        isStop = false;
        pe.reset();
        pe.scaleEffect(this.getScaleX());
    }

    @Override
    public void act(float delta) {
        if (pe == null || !actorIsVisible(this) || isStop) return;
        if (pe.isComplete() && isLoop) Start();
        pe.update(delta);
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (pe == null || !actorIsVisible(this) || isStop) return;
        pe.draw(batch);
    }

    private boolean actorIsVisible(Actor actor) {
        Vector2 actorStagePos = actor.localToStageCoordinates(new Vector2(0, 0));
        Vector2 actorStagePosTl = actor.localToStageCoordinates(new Vector2(
                actor.getWidth(),
                actor.getHeight()));

        Vector3 actorPixelPos = new Vector3(actorStagePos.x, actorStagePos.y, 0);
        Vector3 actorPixelPosTl = new Vector3(actorStagePosTl.x, actorStagePosTl.y, 0);

        actorPixelPos = actor.getStage().getCamera().project(actorPixelPos);
        actorPixelPosTl = actor.getStage().getCamera().project(actorPixelPosTl);

        return !(actorPixelPosTl.x < 0 ||
                actorPixelPos.x > Gdx.graphics.getWidth() ||
                actorPixelPosTl.y < 0 ||
                actorPixelPos.y > Gdx.graphics.getHeight()
        );
    }
}