package com.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.game.MyGame;

import zen.IZen;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGame(new IZen() {
			@Override
			public void ShowFullscreen() {

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

			}

			@Override
			public void ReportScore(String leaderboardID, long score, FBInstant_LeaderboardEntryCallback callback) {

			}

			@Override
			public void Rate() {

			}

			@Override
			public void Like() {

			}

			@Override
			public String GetDefaultLanguage() {
				return null;
			}

			@Override
			public boolean isVideoRewardReady() {
				return false;
			}

			@Override
			public void ShowVideoReward(OnVideoRewardClosed callback) {

			}

			@Override
			public void LinkOtherGame(String packageName) {

			}

			@Override
			public void Log(String log) {

			}

			@Override
			public Pixmap getFrameBufferPixmap(int x, int y, int w, int h) {
				return null;
			}

			@Override
			public void LoadUrlTexture(String url, UrlTextureCallback callback) {

			}

			@Override
			public void FBInstant_GetDoubleStats(String statname, FBInstant_GetStatsCallback callback) {

			}

			@Override
			public void FBInstant_SetDoubleStats(String statname, double value, FBInstant_SetStatsCallback callback) {

			}

			@Override
			public void FBInstant_IncrementDoubleStats(String statname, double value, FBInstant_IncrementDoubleStatsCallback callback) {

			}

			@Override
			public void FBInstant_LoadLeaderboardFriend(FBInstant_LeaderboardEntryCallback callback) {

			}

			@Override
			public void FBInstant_LoadLeaderboard(FBInstant_LeaderboardEntryCallback callback) {

			}

			@Override
			public void FBInstant_LoadMyLeaderboard(FBInstant_LeaderboardEntryCallback callback) {

			}

			@Override
			public void FBInstant_GetPlayerInfo(FBInstant_PlayerInfoCallback callback) {

			}

			@Override
			public void FBInstant_PreloadData(String pack, FBInstant_PreloadCallback callback) {

			}

			@Override
			public void FBInstant_PreloadFile(String filePath, FBInstant_PreloadCallback callback) {

			}

			@Override
			public void FBInstant_ShareAsync(Pixmap pixmap, FBInstant_Callback callback) {

			}

			@Override
			public void OnShow() {

			}
		}), config);
	}
}
