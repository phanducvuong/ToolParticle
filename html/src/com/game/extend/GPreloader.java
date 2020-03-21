package com.game.extend;

import com.badlogic.gdx.backends.gwt.preloader.AssetDownloader;
import com.badlogic.gdx.backends.gwt.preloader.AssetFilter;
import com.badlogic.gdx.backends.gwt.preloader.Blob;
import com.badlogic.gdx.backends.gwt.preloader.Preloader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.google.gwt.dom.client.ImageElement;

import zen.IZen;

public class GPreloader extends Preloader {

    public GPreloader (String newBaseURL)
    {
        super(newBaseURL);
    }
    public void preloadFile (final String assetFileUrl, final IZen.FBInstant_PreloadCallback callback) {
        final AssetDownloader loader = new AssetDownloader();

        String[] tokens = assetFileUrl.split(":");
        if (tokens.length != 4) {
            throw new GdxRuntimeException("Invalid assets description file.");
        }
        AssetFilter.AssetType type = AssetFilter.AssetType.Text;
        if (tokens[0].equals("i")) type = AssetFilter.AssetType.Image;
        if (tokens[0].equals("b")) type = AssetFilter.AssetType.Binary;
        if (tokens[0].equals("a")) type = AssetFilter.AssetType.Audio;
        if (tokens[0].equals("d")) type = AssetFilter.AssetType.Directory;
        long size = Long.parseLong(tokens[2]);
        if (type == AssetFilter.AssetType.Audio && !loader.isUseBrowserCache()) {
            size = 0;
        }
        final Asset asset = new Asset(tokens[1].trim(), type, size, tokens[3]);

        if (contains(asset.url)) {
            asset.loaded = asset.size;
            asset.succeed = true;
            callback.OnSuccess(true);
        }
        else
        {
            loader.load(baseUrl + asset.url, asset.type, asset.mimeType, new AssetDownloader.AssetLoaderListener<Object>() {
                @Override
                public void onProgress (double amount) {
                    asset.loaded = (long) amount;
                }
                @Override
                public void onFailure () {
                    asset.failed = true;
                    callback.OnSuccess(false);
                }
                @Override
                public void onSuccess (Object result) {
                    switch (asset.type) {
                        case Text:
                            texts.put(asset.url, (String) result);
                            break;
                        case Image:
                            images.put(asset.url, (ImageElement) result);
                            break;
                        case Binary:
                            binaries.put(asset.url, (Blob) result);
                            break;
                        case Audio:
                            audio.put(asset.url, null);
                            break;
                        case Directory:
                            directories.put(asset.url, null);
                            break;
                    }
                    asset.succeed = true;
                    callback.OnSuccess(true);
                }
            });
        }
    }
}
