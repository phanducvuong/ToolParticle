package GameGDX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Scene {
    public static int width = 720, height = 1280;
    private static Group ui, uiOther;
    private static Stage stage = new Stage(new StretchViewport(width, height));
    private static Label lbLoading;

    public Scene() {
        ui = new Group();
        uiOther = new Group();
        stage.addActor(ui);
        stage.addActor(uiOther);
        lbLoading = UI.NewLabel("Loading...", Color.WHITE, 1f, Scene.width / 2, Scene.height / 2, Align.center, uiOther);
        showLoad(false);
        Gdx.input.setInputProcessor(stage);
    }

    public static void showLoad(boolean isActive) {
        lbLoading.setVisible(isActive);
    }

    public static void AddToUI(Actor actor) {
        ui.addActor(actor);
    }

    public static void Act() {
        stage.act(Gdx.graphics.getDeltaTime());
    }

    public static void Render() {
        stage.draw();
    }
}
