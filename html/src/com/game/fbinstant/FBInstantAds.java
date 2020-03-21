package com.game.fbinstant;

import zen.IZen;

public class FBInstantAds {
    public static native void ShowFullscreen() /*-{
            $wnd.showFullscreen();
        }-*/;


    public static native boolean IsFullscreenReady()/*-{
            return $wnd.isFullscreenReady();
        }-*/;

    public static native void ShowVideoReward(IZen.OnVideoRewardClosed callback) /*-{
            $wnd.showVideoReward(function(D){
                callback.@zen.IZen.OnVideoRewardClosed::OnEvent(Z)(D);
            });
        }-*/;


    public static native boolean IsVideoRewardReady()/*-{
            return $wnd.isVideoRewardReady();
        }-*/;
}
