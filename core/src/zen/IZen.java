package zen;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by VuNguyen on 1/16/18.
 */

public interface IZen {
    public void ShowFullscreen();
    public void ShowBanner(boolean visible);

    public void TrackLevelStart(int level);
    public void TrackLevelFailed(int level);
    public void TrackLevelCompleted(int level);
    public void ShowLeaderBoard();
    public void ReportScore(String leaderboardID, long score, FBInstant_LeaderboardEntryCallback callback);
    public void Rate();
    public void Like();
    public String GetDefaultLanguage();
    public boolean isVideoRewardReady();
    public void ShowVideoReward(OnVideoRewardClosed callback);
    public void LinkOtherGame(String packageName);
    public void Log(String log);
    public interface PreloaderCallback {
        public void success(Texture texture);
        public void error();
    }
    public Pixmap getFrameBufferPixmap(int x, int y, int w, int h);

    public interface OnVideoRewardClosed{
        public void OnEvent(boolean success);
    }


    public void LoadUrlTexture(String url, final UrlTextureCallback callback);

    public static interface UrlTextureCallback {
        public void success(Texture texture);
    }

    //FBInstant Part

    public void FBInstant_GetDoubleStats(String statname, FBInstant_GetStatsCallback callback);
    public void FBInstant_SetDoubleStats(String statname, double value, FBInstant_SetStatsCallback callback);
    public void FBInstant_IncrementDoubleStats(String statname, double value, FBInstant_IncrementDoubleStatsCallback callback);
    public void FBInstant_LoadLeaderboardFriend(FBInstant_LeaderboardEntryCallback callback);
    public void FBInstant_LoadLeaderboard(FBInstant_LeaderboardEntryCallback callback);
    public void FBInstant_LoadMyLeaderboard(FBInstant_LeaderboardEntryCallback callback);
    public void FBInstant_GetPlayerInfo(FBInstant_PlayerInfoCallback callback);
    public void FBInstant_PreloadData(String pack, FBInstant_PreloadCallback callback);
    public void FBInstant_PreloadFile(String filePath, FBInstant_PreloadCallback callback);
    public void FBInstant_ShareAsync(Pixmap pixmap, FBInstant_Callback callback);
    public void OnShow();

    public static interface FBInstant_Callback {
        public void OnSuccess(boolean success);
    }

    public static interface FBInstant_GetStatsCallback {
        public void OnValue(double value, boolean undefined);
    }
    public static interface FBInstant_PreloadCallback {
        public void OnSuccess(boolean success);
    }

    public static interface FBInstant_SetStatsCallback {
        public void OnSuccess(boolean success);
    }

    public static interface FBInstant_IncrementDoubleStatsCallback {
        public void OnSuccess(double newvalue);
    }

    public static interface FBInstant_LeaderboardEntryCallback {
        public void OnEntry(int rank, String name, String url, int money);
    }

    public static interface FBInstant_PlayerInfoCallback {
        public void OnSuccess(String name, String photoUrl);
    }

    public static interface FBInstant_FinishInitCallback {
        public void OnSuccess(boolean success);
    }

}
