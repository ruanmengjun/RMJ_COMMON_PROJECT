package ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

import app.MyApplication;
import ui.service.MusicService;

/**
 * 定时停止播放，具体操作
 * Created by Idea on 2016/6/11.
 */
public class AlarmReceiver extends BroadcastReceiver
{
    private static final String TAG = "RhymeMusic";
    private static final String SUB = "[AlarmReceiver]#";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d(TAG, SUB + "音乐自动停止播放了");

        //MusicApplication application = (MusicApplication) context.getApplicationContext();
        MediaPlayer mediaPlayer = MyApplication.getInstance().getMediaPlayer();
        MusicService.MusicBinder musicBinder = MyApplication.getInstance().getMusicBinder();

        if ( mediaPlayer != null && mediaPlayer.isPlaying() )
        {
            musicBinder.pausePlay();
        }
    }
}
