package Screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.List;

import GameGDX.Loader;
import GameGDX.Particle;
import GameGDX.Scene;
import GameGDX.UI;

public class GamePlayScreen extends Screen {
    public static GamePlayScreen instance;

    private Group ui = new Group(), grLb = new Group(), tab = new Group();
    private boolean isScaleAdd = false, isScaleSub = false;

    public GamePlayScreen() {
        instance = this;

        main.addActor(ui);
        ui.addActor(grLb);
        ui.addActor(tab);

        UI.NewTextButton("BACK", 1, Color.WHITE, Color.RED, 0, 1280, Align.topLeft, 130, 80, ui).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Hide();
                Reset();
                ScrollParScreen.instance.Show(MenuScreen.indexFolder);
            }
        });

        UI.NewTextButton("NOT LOOP\nREPLAY", 1, Color.WHITE, Color.RED, 720, 1280, Align.topRight, 130, 80, ui).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Reset();
                NewPar(index, false);
            }
        });

        UI.NewTextButton("LOOP", 1, Color.WHITE, Color.RED, 720 - 130 - 50, 1280, Align.topRight, 130, 80, ui).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Reset();
                NewPar(index, true);
            }
        });

        UI.NewTextButton("ZOOM++", 1, Color.WHITE, Color.RED, 720, 1280 - 100, Align.topRight, 130, 80, ui).addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return isScaleAdd = true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isScaleAdd = false;
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                par.setScale(par.getScaleX()+0.1f);
                par.Start();
            }
        });

        UI.NewTextButton("ZOOM--", 1, Color.WHITE, Color.RED, 0, 1280 - 100, Align.topLeft, 130, 80, ui).addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return isScaleSub = true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isScaleSub = false;
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                par.setScale(par.getScaleX()-0.1f);
                par.Start();
            }
        });

        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            UI.NewTextButton("SAVE", 1, Color.WHITE, Color.RED, 720, 0, Align.bottomRight, 100, 100, ui).addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    savePar();
                }
            });
        }
    }

    private static int cloneFolderPar = 0;
    private void savePar() {
        String path = "Particle/" + Loader.nameFolder[MenuScreen.indexFolder] + "/",
                pathCopy = "ParticleCopy/" + ++cloneFolderPar + "/",
                filePar = MenuScreen.folderCFX().get(index);

        Gdx.files.local(path + filePar).copyTo(Gdx.files.local(pathCopy + filePar));
        for (ParticleEmitter p : par.pe.getEmitters()) {
            String s = p.getImagePaths().first();
            for (int j = s.length() - 1; j >= 0; j--)
                if (s.charAt(j) == 92) {
                    s = s.substring(j + 1);
                    break;
                }
            Gdx.files.local(path + s).copyTo(Gdx.files.local(pathCopy + s));
        }
    }

    private void Reset() {
        par.remove();
        grLb.clearChildren();
        tab.clearChildren();
    }

    private Particle par;
    private int index;
    public void NewPar(int index, boolean isLoop) {
        this.index = index;
        par = new Particle(MenuScreen.folderCFX().get(index), Scene.width / 2, Scene.height / 2 + 200);
        par.isLoop = isLoop;
        par.Start();
        addActor(par);

        Table table = new Table();
        ScrollPane scroll = new ScrollPane(table);
        scroll.setBounds(0, 0, Scene.width, 500);
        tab.addActor(scroll);
        table.debug();

        List<String> map = new ArrayList<String>();
        for (int i = 0; i < par.pe.getEmitters().size; i++) {
            String s = par.pe.getEmitters().get(i).getImagePaths().first();
            if (!map.contains(s)) map.add(s);
        }
        table.add(UI.NewLabel("1.   " + MenuScreen.folderCFX().get(index), Color.WHITE, 1, 0, 0, Align.topLeft, 720, 100, grLb)).width(720).height(100).row();
        for (int i = 0; i < map.size(); i++) {
            String s = map.get(i);
            Label lb = UI.NewLabel((i + 2) + ".   " + s, Color.WHITE, 1, 0, 0, Align.topLeft, 720, 100, grLb);
            table.add(lb).width(Scene.width).height(100).row();
            for (int j = s.length() - 1; j >= 0; j--)
                if (s.charAt(j) == 92) {
                    lb.setText((i + 2) + ".   " + s.substring(j + 1));
                    break;
                }
        }
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
