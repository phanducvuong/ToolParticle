package GameGDX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import GameGDX.AssetLoading.DataGame;
import GameGDX.AssetLoading.NodeAsset;

public class Loader {
    public static Loader instance;

    public static BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/" + "font.fnt"), new TextureRegion(new Texture(Gdx.files.internal("Fonts/" + "font.png"))), false);

    public static HashMap<String, FileHandle> mapPar = new HashMap<String, FileHandle>();
    public static List<String>[] par;
    public static FileHandle rootParticle;

    public static String[] nameFolder;

    public Loader() {
        instance = this;
        for (FileHandle f : Gdx.files.local("Particle").list()) if (f.extension().equals("DS_Store")) f.delete();

        par = new List[Gdx.files.internal("Particle").list().length];
        for (int i = 0; i < par.length; i++) par[i] = new ArrayList<String>();

        nameFolder = new String[par.length];
        int i = 0;
        for (FileHandle f : Gdx.files.internal("Particle").list()) nameFolder[i++] = f.name();
    }

    public void LoadFilePar(int indexCFX) {
        if (par[indexCFX].size() > 0) return;
        String path = "Particle/" + nameFolder[indexCFX] + "/";
        rootParticle = Gdx.files.internal(path);
        DataGame.LoadFile(path);
        for (NodeAsset no : DataGame.assets)
            if (no.extension.equals("p") || no.extension.equals("")) {
                String s = no.name + (no.extension.equals("") ? "" : ".p");
                par[indexCFX].add(s);
                mapPar.put(s, Gdx.files.internal(path + s));
            }
    }
}