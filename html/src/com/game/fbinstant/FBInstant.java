package com.game.fbinstant;

import zen.IZen;

public class FBInstant {
    public static native void LoadingProgress(int percent) /*-{
            $wnd.FBInstant.setLoadingProgress(percent);
    }-*/;

    public static native void LoadingFinished(IZen.FBInstant_FinishInitCallback callback) /*-{
            $wnd.FBInstant.startGameAsync().then(function() {
                $wnd.startGame();
                if(callback!=null)
                    callback.@zen.IZen.FBInstant_FinishInitCallback::OnSuccess(Z)(true);
              });
    }-*/;

    public static native String GetPlatform()/*-{
            return $wnd.FBInstant.getPlatform();
    }-*/;
    public static native void ShareAsync(String base64Picture, IZen.FBInstant_Callback callback)    /*-{
            $wnd.ShareAsync(base64Picture,function() {
                if(callback!=null)
                    callback.@zen.IZen.FBInstant_Callback::OnSuccess(Z)(true);
              });
    }-*/;
    public static native String GetLocale()/*-{
            return $wnd.FBInstant.getLocale();
    }-*/;
}
