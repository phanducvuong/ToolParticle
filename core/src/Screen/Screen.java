package Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import GameGDX.Scene;
import GameGDX.UI;

public class Screen extends Group {
    protected Image overlay;
    protected Group main = new Group();
    private Action done = new Action() {
        @Override
        public boolean act(float delta) {
            setVisible(false);
            return true;
        }
    };

    public Screen() {
        Init(Scene.width, Scene.height);
    }

    private void Init(float width, float height) {
        overlay = UI.NewImage(new Color(0, 0, 0, 0.5f), 0, 0, Align.bottomLeft, Scene.width, Scene.height, this);

        main.setSize(width, height);
        main.setOrigin(Align.center);
        main.setScale(0);
        main.setPosition(Scene.width / 2, Scene.height / 2, Align.center);
        addActor(main);
        setVisible(false);
        Scene.AddToUI(this);
    }

    public void Show() {
        main.clearActions();
        setVisible(true);
        ScaleTo(main, 1, 1, 0.4f, Interpolation.bounceOut, null);
    }

    public void Hide() {
        main.clearActions();
        ScaleTo(main, 0, 0, 0.2f, Interpolation.fade, done);
    }

    public static void ScaleTo(Actor actor, float scaleX, float scaleY, float duration, Interpolation interpolation, Action done) {
        ScaleToAction action = Actions.scaleTo(scaleX, scaleY, duration, interpolation);
        if (done == null) actor.addAction(action);
        else actor.addAction(new SequenceAction(action, done));
    }
}
