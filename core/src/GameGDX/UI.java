package GameGDX;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class UI {
    public static TextureRegion NewTexture(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(0, 0, width, height);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegion(texture);
    }

    public static Image NewImage(Color color, float x, float y, int align, int width, int height, Group parent) {
        Image image = new Image(NewTexture(width, height));
        image.setColor(color);
        image.setPosition(x, y, align);

        parent.addActor(image);
        return image;
    }

    //NewLabel
    private static float fontScale = 1f;

    public static void SetText(Label label, String content, float maxScale) {
        for (int i = 10; i > 0; i--) {
            float scale = 0.1f * i;
            label.setFontScale(maxScale * scale * fontScale);
            label.setText(content);
            if (label.getPrefHeight() < label.getHeight()) return;
        }
    }

    public static Label NewLabel(String content, int alignment, Color color, float scale, float x, float y, int align, float width, float height, Group parent) {
        Label.LabelStyle labelStyle = new Label.LabelStyle(Loader.font, Color.WHITE);
        Label label = new Label(content, labelStyle);
        label.setSize(width, height);
        label.setPosition(x, y, align);
        label.setAlignment(alignment);
        label.setTouchable(Touchable.disabled);
        label.setWrap(true);
        label.setColor(color);
        label.setFontScale(scale * fontScale);
        SetText(label, content, scale);

        parent.addActor(label);
        return label;
    }

    public static Label NewLabel(String content, Color color, float scale, float x, float y, int align, float width, float height, Group parent) {
        return NewLabel(content, Align.center, color, scale, x, y, align, width, height, parent);
    }

    public static Label NewLabel(String content, Color color, float scale, float x, float y, int align, Group parent) {
        Label.LabelStyle labelStyle = new Label.LabelStyle(Loader.font, color);
        Label label = new Label(content, labelStyle);
        label.setFontScale(scale * fontScale);
        label.setPosition(x, y, align);
        label.setAlignment(align);
        label.setTouchable(Touchable.disabled);

        parent.addActor(label);
        return label;
    }

    public static Group NewTextButton(String content, float scale, Color cl, Color clBtn, float x, float y, int align, float width, float height, Group parent) {
        Group group = new Group();
        group.setSize(width, height);
        group.setPosition(x, y, align);

        NewImage(clBtn, 0, 0, Align.bottomLeft, (int)width, (int)height, group);
        NewLabel(content, cl, scale, 0, 0, Align.bottomLeft, width, height, group);

        parent.addActor(group);
        return group;
    }
}
