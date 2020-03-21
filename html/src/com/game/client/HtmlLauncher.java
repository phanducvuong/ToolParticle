package com.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.backends.gwt.preloader.AssetDownloader;
import com.badlogic.gdx.backends.gwt.preloader.Preloader;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.MyGame;
import com.game.extend.GPreloader;
import com.game.fbinstant.FBInstant;
import com.game.fbinstant.FBInstantAds;
import com.game.fbinstant.FBInstantLeaderboard;
import com.game.fbinstant.FBInstantPlayer;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;

import java.nio.ByteBuffer;

import zen.IZen;

public class HtmlLauncher extends GwtApplication {
        private boolean isHandleResize = false;
        private long lastInterstitialTime;
        public static boolean isInited = false;
        @Override
        public GwtApplicationConfiguration getConfig () {
                int height = com.google.gwt.user.client.Window.getClientHeight();
                int width = com.google.gwt.user.client.Window.getClientWidth();

                GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(width, height);
                Window.enableScrolling(false);
                Window.setMargin("0");

                //float r = (float)height/(float)width;  //4:3=1.33 16:9=1.76
                float r = (float)width/(float)height;
                if(r < 1.5f){
                        isHandleResize = true;
                        //Window.addResizeHandler(new ResizeListener());
                }

                cfg.disableAudio = true;
                cfg.preferFlash = false;
                cfg.preserveDrawingBuffer = true;
                //cfg.antialiasing = true;

                return cfg;
        }
        public void SetupCanvas(){
                CanvasElement canvas = getCanvasElement();
                int w = canvas.getWidth();// getInnerWidth();
                int h = canvas.getHeight();//getInnerHeight();
                double dpr  = devicePixelRatio();
                int cw = (int)(w*dpr);
                int ch = (int)(h*dpr);

                if(isHandleResize){
                        w = getInnerWidth();
                        h = getInnerHeight();
                        cw = getFBWidth();
                        ch = getFBHeight();
                }

                canvas.getStyle().setWidth(w, Style.Unit.PX);
                canvas.getStyle().setHeight(h, Style.Unit.PX);
                canvas.setWidth(cw);
                canvas.setHeight(ch);

        }

        private GPreloader zenPreloader = null;
        public GPreloader createPreloader() {
                consolelog("createZenPreloader");
                if(zenPreloader == null)
                        zenPreloader = new GPreloader(getPreloaderBaseURL());
                return zenPreloader;
        }

        @Override
        public ApplicationListener createApplicationListener () {

                IZen zenObj = new IZen() {
                        @Override
                        public void ShowFullscreen() {

                                if (System.currentTimeMillis() - lastInterstitialTime > 220000) {

                                        if (FBInstantAds.IsFullscreenReady()) {
                                                FBInstantAds.ShowFullscreen();
                                                lastInterstitialTime = System.currentTimeMillis();
                                        }
                                }


                        }

                        @Override
                        public void ShowBanner(boolean visible) {

                        }

                        @Override
                        public void TrackLevelStart(int level) {

                        }

                        @Override
                        public void TrackLevelFailed(int level) {

                        }

                        @Override
                        public void TrackLevelCompleted(int level) {

                        }

                        @Override
                        public void ShowLeaderBoard() {
                                //showLeaderboard();
                        }

                        @Override
                        public void ReportScore(String leaderboardID, long score, FBInstant_LeaderboardEntryCallback callback) {
                                FBInstantLeaderboard.ReportScore("TOP_SCORE",score,callback);
                        }

                        @Override
                        public void Rate() {

                        }

                        @Override
                        public void Like() {

                        }

                        @Override
                        public String GetDefaultLanguage() {
                                return FBInstant.GetLocale();
                        }

                        @Override
                        public boolean isVideoRewardReady() {
                                return FBInstantAds.IsVideoRewardReady();
                        }

                        @Override
                        public void ShowVideoReward(OnVideoRewardClosed callback) {
                                FBInstantAds.ShowVideoReward(callback);
                        }
                        @Override
                        public void LinkOtherGame(String packageName) {

                        }

                        @Override
                        public void Log(String log) {
                                consoleLog(log);
                        }

                        @Override
                        public Pixmap getFrameBufferPixmap(int x, int y, int w, int h) {
                                Gdx.gl.glPixelStorei(3333, 1);
                                Pixmap pixmap = new Pixmap(w, h, Pixmap.Format.RGBA8888);
                                byte[] pa = ScreenUtils.getFrameBufferPixels(x, y ,w, h, true);
                                ByteBuffer pixels = BufferUtils.newByteBuffer(h * w * 4);
                                BufferUtils.copy(pa, 0, pixels, pa.length);

                                ScreenUtils.putPixelsBack(pixmap, pixels);
                                return pixmap;
                        }

                        @Override
                        public void LoadUrlTexture(String url, UrlTextureCallback callback) {
                                loadUrlTexture(url, callback);
                        }

                        @Override
                        public void FBInstant_GetDoubleStats(String statname, FBInstant_GetStatsCallback callback) {
                                FBInstantPlayer.GetDoubleStatsAsync(statname, callback);
                        }

                        @Override
                        public void FBInstant_SetDoubleStats(String statname, double value, FBInstant_SetStatsCallback callback) {
                                FBInstantPlayer.SetDoubleStatsAsync(statname, value, callback);
                        }

                        @Override
                        public void FBInstant_IncrementDoubleStats(String statname, double value, FBInstant_IncrementDoubleStatsCallback callback) {
                                FBInstantPlayer.IncrementDoubleStatsAsync(statname, value, callback);
                        }

                        @Override
                        public void FBInstant_LoadLeaderboardFriend(FBInstant_LeaderboardEntryCallback callback) {
                                FBInstantLeaderboard.LoadLeaderboardFriend(callback);
                        }

                        @Override
                        public void FBInstant_LoadLeaderboard(FBInstant_LeaderboardEntryCallback callback) {
                                FBInstantLeaderboard.LoadLeaderboard(callback);
                        }

                        @Override
                        public void FBInstant_LoadMyLeaderboard(FBInstant_LeaderboardEntryCallback callback) {
                                FBInstantLeaderboard.LoadMyLeaderboard(callback);
                        }

                        @Override
                        public void FBInstant_GetPlayerInfo(FBInstant_PlayerInfoCallback callback) {
                                String name = FBInstantPlayer.GetName();
                                String photo = FBInstantPlayer.GetPhoto();
                                callback.OnSuccess(name, photo);
                        }

                        @Override
                        public void FBInstant_PreloadData(String pack, final FBInstant_PreloadCallback callback) {
                                consolelog("startSecondPreload");
                                Preloader preloader = createPreloader();
                                preloader.preload(pack, new Preloader.PreloaderCallback() {
                                        @Override
                                        public void update(Preloader.PreloaderState state) {
                                                if(state.hasEnded())
                                                {
                                                        //finish
                                                        consolelog("finish preload");
                                                        callback.OnSuccess(true);
                                                }
                                        }

                                        @Override
                                        public void error(String file) {
                                                consolelog("erro: "+file);
                                                callback.OnSuccess(false);
                                        }

                                });
                        }

                        @Override
                        public void FBInstant_PreloadFile(String filePath,final FBInstant_PreloadCallback callback) {
                                consolelog("startPreload file");
                                GPreloader preloader = createPreloader();
                                preloader.preloadFile(filePath,callback);
                        }

                        @Override
                        public void FBInstant_ShareAsync(Pixmap pixmap, FBInstant_Callback callback) {
                                FBInstant.ShareAsync(pixmap.getCanvasElement().toDataUrl(),callback);
                        }

                        @Override
                        public void OnShow() {
                                if(isInited==false){
                                        SetupCanvas();
                                        isInited = true;
                                }
                        }
                };
                return new MyGame(zenObj);
        }

        public void loadUrlTexture(final String assetFileUrl, final IZen.UrlTextureCallback callback) {
                final AssetDownloader loader = new AssetDownloader();
                loader.loadImage(assetFileUrl, "image/png", "anonymous",  new AssetDownloader.AssetLoaderListener<ImageElement>() {
                        @Override
                        public void onProgress(double amount) {
                        }

                        @Override
                        public void onFailure() {

                        }

                        @Override
                        public void onSuccess(ImageElement result) {
                                Pixmap pm = new Pixmap(result);
                                Texture returnTexture = new Texture(pm);
                                callback.success(returnTexture);
                        }
                });

        }


        @Override
        public Preloader.PreloaderCallback getPreloaderCallback() {

                return new Preloader.PreloaderCallback() {
                        public void error(String file) {
                                System.out.println("error: " + file);
                        }

                        public void update(Preloader.PreloaderState state) {
                                double percent = (double)(100.0F * state.getProgress());
                                // meterStyle.setWidth(percent, Style.Unit.PCT);

                                FBInstant.LoadingProgress((int)percent);

                                if(state.hasEnded()) {
                                        FBInstant.LoadingFinished(new IZen.FBInstant_FinishInitCallback() {
                                                @Override
                                                public void OnSuccess(boolean success) {
//                                                        MyGame.doneLoad();
                                                }
                                        });
                                        consoleLog("end");
                                }
                        }
                };
        }


        //html code
        private static native double devicePixelRatio() /*-{
            return  $wnd.window.devicePixelRatio;
        }-*/;

        private static native int getInnerWidth() /*-{
            return $wnd.window.innerWidth;
        }-*/;

        private static native int getInnerHeight() /*-{
            return $wnd.window.innerHeight;
        }-*/;

        private static native int getFBWidth() /*-{
            return $wnd.window.innerWidth * $wnd.window.devicePixelRatio;
        }-*/;

        private static native int getFBHeight() /*-{
            return $wnd.window.innerHeight * $wnd.window.devicePixelRatio;
        }-*/;

        private static native void consolelog(String log) /*-{
            $wnd.consolelog(log);
        }-*/;

}