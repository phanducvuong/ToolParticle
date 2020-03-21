package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import GameGDX.Scene;
import GameGDX.UI;
import View.ParticleView;

public class ScrollParScreen extends Screen {
    public static ScrollParScreen instance;
    private Group ui = new Group();
    private Label lbTotal;
    private Group[] grFolderPar = new Group[Gdx.files.internal("Particle").list().length];
    private Integer[] numPar = new Integer[grFolderPar.length];
    private Table[] tables = new Table[Gdx.files.internal("Particle").list().length];

    public ScrollParScreen() {
        instance = this;
        for (int i = 0; i < grFolderPar.length; i++) {
            grFolderPar[i] = new Group();
            main.addActor(grFolderPar[i]);
            tables[i] = new Table();
        }
        main.addActor(ui);
        UI.NewTextButton("MENU", 1, Color.WHITE, Color.RED, 0, 1280, Align.topLeft, 120, 70, ui).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Hide();
                MenuScreen.instance.Show();
            }
        });
        lbTotal = UI.NewLabel("Total: ", Color.WHITE, 0.7f, 360, 1280, Align.top, 720, 80, ui);
    }

    public void parView(int index) {
        ScrollPane scroll = new ScrollPane(tables[index]);
        scroll.setBounds(0, 0, Scene.width, Scene.height);
        tables[index].debug();

        for (int i = 0; i < MenuScreen.folderCFX().size(); i++) {
            ParticleView par = new ParticleView(i);
            if (par.par.pe != null) tables[index].add(par).width(720).height(360).row();
        }
        numPar[index] = tables[index].getChildren().size;
        grFolderPar[index].addActor(scroll);
    }

    public void Show(int indexFolder) {
        grFolderPar[indexFolder].setVisible(true);
        for (int i = 0; i < tables[indexFolder].getChildren().size; i++) {
            ParticleView par = (ParticleView)tables[indexFolder].getChildren().get(i);
            par.par.isStop = false;
        }
        lbTotal.setText(MenuScreen.nameFolder + " -- TOTAL: "+ numPar[indexFolder]);
        Scene.showLoad(false);
        super.Show();
    }

    @Override
    public void Hide() {
        for (Group gr : grFolderPar) gr.setVisible(false);
        for (Table tb : tables)
            for (int i = 0; i < tb.getChildren().size; i++) {
                ParticleView par = (ParticleView)tb.getChildren().get(i);
                par.par.isStop = true;
            }
        super.Hide();
    }
}