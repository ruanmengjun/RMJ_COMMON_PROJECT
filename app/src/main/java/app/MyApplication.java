package app;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;

import com.squareup.leakcanary.LeakCanary;

import ui.service.MusicService;


public class MyApplication extends Application implements ServiceConnection {
    private static MyApplication instance;
    private MediaPlayer mediaPlayer = null; // 创建MediaPlayer对象
    private MusicService.MusicBinder musicBinder;
    private int currentMusic;  // 当前播放音乐在列表中的位置（从0开始）
    private int currentPosition; // 当前音乐的播放进度
    private boolean isOnline;//是否在线音乐
    private boolean isSelected;//首次进入点击播放按钮提示
    private TextView textAudioIndex;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mediaPlayer = new MediaPlayer();
        bindService();
        //此处监测内存泄漏
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public LocalBroadcastManager getManager() {
        Log.i("tag",this.toString());
        return LocalBroadcastManager.getInstance(this); // 获取实例
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCurrentMusic() {
        return currentMusic;
    }

    public void setCurrentMusic(int currentMusic) {
        this.currentMusic = currentMusic;
    }


    public void bindService() {
        Intent intent = new Intent(this, MusicService.class);
        this.bindService(intent, this, BIND_AUTO_CREATE);
    }

    public void unBindService() {
        this.unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        if (service instanceof MusicService.MusicBinder) {
            musicBinder = (MusicService.MusicBinder) service;
        }
    }

    public MusicService.MusicBinder getMusicBinder() {
        return musicBinder;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isSelected(){
        return isSelected;
    }

    public void setIsSelected(boolean selected){
        isSelected=selected;
    }

    public TextView getTextAudioIndex() {
        return textAudioIndex;
    }

    public void setTextAudioIndex(TextView textAudioIndex) {
        this.textAudioIndex = textAudioIndex;
    }
}
