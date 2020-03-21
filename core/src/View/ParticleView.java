package View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import GameGDX.Particle;
import GameGDX.UI;
import Screen.GamePlayScreen;
import Screen.MenuScreen;
import Screen.ScrollParScreen;

public class ParticleView extends Group {
    private boolean isScaleAdd = false, isScaleSub = false;
    public Particle par;

    public ParticleView(final int index) {
        UI.NewLabel((index+1)+"", Align.right, Color.WHITE, 1, 700, 280, Align.right,300, 100, this);
        par = new Particle(MenuScreen.folderCFX().get(index), 720/2, 360/2);
        par.setScale(0.5f);
        par.Start();
        addActor(par);

        Group gr = new Group();
        addActor(gr);
        gr.setSize(720/2, 360);
        gr.setX(gr.getWidth()/2);
        gr.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScrollParScreen.instance.Hide();
                GamePlayScreen.instance.NewPar(index, true);
                GamePlayScreen.instance.Show();
            }
        });
        setTouchable(Touchable.childrenOnly);

        UI.NewTextButton("Zoom++", 1, Color.WHITE, Color.RED, 720-100, 130, Align.bottomLeft, 100, 80, this).addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return isScaleAdd = true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isScaleAdd = false;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        UI.NewTextButton("Zoom--", 1, Color.WHITE, Color.RED, 0, 130, Align.bottomLeft, 100, 80, this).addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return isScaleSub = true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isScaleSub = false;
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void act(float delta) {
        if (isScaleAdd) {
            par.setScale(par.getScaleX()+0.02f);
            par.Start();
        }

        if (isScaleSub) {
            par.setScale(par.getScaleX()-0.02f);
            par.Start();
        }

        super.act(delta);
    }
}
