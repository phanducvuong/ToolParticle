package com.game.fbinstant;

import zen.IZen;

public class FBInstantLeaderboard {
    public static native void ReportScore(String leaderboard_name,double score, IZen.FBInstant_LeaderboardEntryCallback callback) /*-{
        $wnd.console.log("ReportScore " + score);
        $wnd.FBInstant
          .getLeaderboardAsync(leaderboard_name)
          .then(function(leaderboard){
            return leaderboard.setScoreAsync(score);
          })
          .then(function(entry) {
            if(callback!=null)
                callback.@zen.IZen.FBInstant_LeaderboardEntryCallback::OnEntry(ILjava/lang/String;Ljava/lang/String;I)
                (entry.getRank(), entry.getPlayer().getName(), entry.getPlayer().getPhoto(), entry.getScore());
          });

        }-*/;

    public static native void LoadLeaderboard( IZen.FBInstant_LeaderboardEntryCallback callback) /*-{
          $wnd.LoadLeaderboard(function(rank,name,photo,score){
               if(callback!=null)
                callback.@zen.IZen.FBInstant_LeaderboardEntryCallback::OnEntry(ILjava/lang/String;Ljava/lang/String;I)(rank,name,photo,score);
          });
    }-*/;
    public static native void LoadMyLeaderboard( IZen.FBInstant_LeaderboardEntryCallback callback) /*-{
          $wnd.LoadMyLeaderboard(function(rank,name,photo,score){
               if(callback!=null)
                callback.@zen.IZen.FBInstant_LeaderboardEntryCallback::OnEntry(ILjava/lang/String;Ljava/lang/String;I)(rank,name,photo,score);
          });
    }-*/;
    public static native void LoadLeaderboardFriend( IZen.FBInstant_LeaderboardEntryCallback callback) /*-{
          $wnd.LoadLeaderboardConnected(function(rank,name,photo,score){
               if(callback!=null)
                callback.@zen.IZen.FBInstant_LeaderboardEntryCallback::OnEntry(ILjava/lang/String;Ljava/lang/String;I)(rank,name,photo,score);
          });
    }-*/;
}
