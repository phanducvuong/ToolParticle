package com.game.fbinstant;


import zen.IZen;

public class FBInstantPlayer {
    public static native String GetName() /*-{
            return $wnd.FBInstant.player.getName();
    }-*/;

    public static native String GetPhoto() /*-{
            return $wnd.FBInstant.player.getPhoto();
    }-*/;
    public static native String GetID() /*-{
            return $wnd.FBInstant.player.getID();
    }-*/;

    public static native void GetDoubleStatsAsync(String statname, IZen.FBInstant_GetStatsCallback callback)    /*-{
        $wnd.FBInstant.player
            .getStatsAsync([statname])
            .then(function(stats) {
              $wnd.console.log(JSON.stringify(stats));
              if (typeof stats[statname] !== 'undefined'){
                var val = stats[statname];
                $wnd.console.log("GetDoubleStatsAsync " + val);
                callback.@zen.IZen.FBInstant_GetStatsCallback::OnValue(DZ)(val, false);
              }
              else {
                $wnd.console.log("GetDoubleStatsAsync undefined");
                callback.@zen.IZen.FBInstant_GetStatsCallback::OnValue(DZ)(0, true);
              }
        });
    }-*/;

    public static native void SetDoubleStatsAsync(String statname, double value, IZen.FBInstant_SetStatsCallback callback)    /*-{
          var tempObj = {};
          tempObj[statname] = value;

          $wnd.FBInstant.player
            .setStatsAsync(tempObj)
            .then(function() {
                $wnd.console.log("SetDoubleStatsAsync ok");
                if(callback!=null)
                    callback.@zen.IZen.FBInstant_SetStatsCallback::OnSuccess(Z)(true);
            });
    }-*/;



    public static native void IncrementDoubleStatsAsync(String statname, double value, IZen.FBInstant_IncrementDoubleStatsCallback callback) /*-{
            var tempObj = {};
            tempObj[statname] = value;
            $wnd.FBInstant.player
                .incrementStatsAsync(tempObj)
                .then(function(stats) {
                  var newval = stats[statname];
                  if(callback!=null)
                    callback.@zen.IZen.FBInstant_IncrementDoubleStatsCallback::OnSuccess(D)(newval);

                });
    }-*/;

}
