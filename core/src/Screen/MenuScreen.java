package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

import java.util.List;

import GameGDX.Loader;
import GameGDX.Scene;
import GameGDX.UI;

public class MenuScreen extends Screen {
    public static MenuScreen instance;
    private Table table = new Table();
    public static int indexFolder = 0;

    public MenuScreen() {
        instance = this;

        ScrollPane scroll = new ScrollPane(table);
        scroll.setBounds(0, 0, Scene.width, Scene.height);
        main.addActor(scroll);
        table.debug();

        for (int index = 0; index < Loader.nameFolder.length; index++)
            FolderCFX(Loader.nameFolder[index], index);
    }

    public static String nameFolder;
    private void FolderCFX(final String name, final int index) {
        Group gr = new Group();
        UI.NewTextButton(name, 1, Color.WHITE, Color.BLACK, 0, 0, Align.bottomLeft, 720, 100, gr).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                nameFolder = name;
                Scene.showLoad(true);
                Hide();
                indexFolder = index;
                if (folderCFX().size() <= 0) {
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            Loader.instance.LoadFilePar(index);
                            ScrollParScreen.instance.parView(index);
                            ScrollParScreen.instance.Show(indexFolder);
                        }
                    }, 0.5f);
                } else {
                    Loader.rootParticle = Gdx.files.internal("Particle/" + nameFolder + "/");
                    ScrollParScreen.instance.Show(indexFolder);
                }
            }
        });
        table.add(gr).width(720).height(100).row();
    }

    public static List<String> folderCFX() {
        return Loader.par[indexFolder];
    }
}
