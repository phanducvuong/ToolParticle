package GameGDX.AssetLoading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.List;

public class DataGame {
    public static List<NodeAsset> assets = new ArrayList<NodeAsset>();

    public static void LoadFile(String path) {
        assets.clear();
        LoadFile(Gdx.files.internal(path));
    }

    private static void LoadFile(FileHandle fh) {
        if (!fh.isDirectory()) {
            if (fh.extension().equals("DS_Store")) {
                fh.file().delete();
                return;
            }
            assets.add(new NodeAsset(fh.nameWithoutExtension(), fh.extension()));
        } else for (FileHandle f : fh.list()) LoadFile(f);
    }
}
