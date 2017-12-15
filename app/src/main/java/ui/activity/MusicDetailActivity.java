package ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import app.MyApplication;
import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import rmj.example.com.rmj_common_project.R;
import service.entity.Audio;
import ui.service.MusicService;
import utils.AudioUtil;
import utils.CommonTitleBar;
import utils.FormatHelper;
import utils.SnackbarUtil;

public class MusicDetailActivity extends BaseActivity {

    private static final String TAG = "RhymeMusic";
    private static final String SUB = "[PlaybackActivity]#";

    @BindView(R.id.previous_icon)
    TextView previousIcon;
    @BindView(R.id.play_status)
    TextView playStatus;
    @BindView(R.id.next_icon)
    TextView nextIcon;
    @BindView(R.id.play_pattern_icon)
    TextView playPatternIcon;
    @BindView(R.id.play_list)
    TextView playList;
    @BindView(R.id.start_position)
    TextView startPosition;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.end_position)
    TextView endPosition;
    private CommonTitleBar titleBar;

    private List<Audio> audioList; // 音乐列表

    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private MusicService.MusicBinder musicBinder;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_music_detail;
    }

    @Override
    protected void initView() {
        titleBar = new CommonTitleBar(this);
        titleBar.setTitle("找钱花音乐平台");
        titleBar.hideRight(true);
        previousIcon.setTypeface(iconfont);
        playStatus.setTypeface(iconfont);
        nextIcon.setTypeface(iconfont);
        playPatternIcon.setTypeface(iconfont);
        playList.setTypeface(iconfont);
    }

    @Override
    protected void initData() {
        musicBinder = MyApplication.getInstance().getMusicBinder();

        /*初始化音乐列表*/
        audioList = AudioUtil.getAudioList(MusicDetailActivity.this);
        int currentMusic = MyApplication.getInstance().getCurrentMusic();
        setResource();
        seekbar.setMax(audioList.get(currentMusic).getDuration());
        seekbar.setProgress(MyApplication.getInstance().getCurrentPosition());
        /*播放流逝的时间*/
        updateProgress();

        /*音乐的总时间*/
        updateDuration();

        /*播放页面的音乐标题*/
        updateMusicInfo();

        /*显示播放模式*/
        updatePlayMode();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerLocalReceiver();  // 注册本地广播接收器

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if ( fromUser )
                {
                    musicBinder.changeProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick({R.id.play_status, R.id.previous_icon, R.id.next_icon,R.id.layout1})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.play_status:
                playMusic();
                //播放
                break;
            case R.id.previous_icon:
                musicBinder.preTrack();
                //上一首
                break;
            case R.id.next_icon:
                //下一首
                musicBinder.nextTrack();
                break;
            case R.id.layout1:
                MusicService.MusicBinder musicBinder = MyApplication.getInstance().getMusicBinder();
                musicBinder.changePlayMode();
                String tt = MyApplication.getInstance().getMusicBinder().getCurrentMode();
                switch (tt){
                    case "0":
                        playPatternIcon.setText(R.string.order_play);
                        SnackbarUtil.LongSnackbar(v, "当前播放模式为：" + "顺序播放", SnackbarUtil.Info).show();
                        break;
                    case "1":
                        playPatternIcon.setText(R.string.random);
                        SnackbarUtil.LongSnackbar(v, "当前播放模式为：" + "随机播放", SnackbarUtil.Info).show();
                        break;
                    case "2":
                        playPatternIcon.setText(R.string.single);
                        SnackbarUtil.LongSnackbar(v, "当前播放模式为：" + "单曲循环", SnackbarUtil.Info).show();
                        break;
                    case "3":
                        playPatternIcon.setText(R.string.order);
                        SnackbarUtil.LongSnackbar(v, "当前播放模式为：" + "列表循环", SnackbarUtil.Info).show();
                        break;
                }
                break;
        }
    }

    /**
     * 内部类
     * 本地广播接收器。
     * 接受来自MusicService的广播，并进行处理
     */
    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            switch (action) {
                case MusicService.ACTION_UPDATE_CURRENT_MUSIC:
                    Log.d(TAG, SUB + "更新音乐标题");
                    updateMusicInfo();
//                    artWorkFragment.setArtWork(); //  更新专辑图片
//                    lyricFragment.initLyric();
                    break;

                case MusicService.ACTION_UPDATE_DURATION:
                    Log.d(TAG, SUB + "更新音乐时长");
                    updateDuration();
                    break;

                case MusicService.ACTION_UPDATE_PROGRESS:
                    updateProgress();
                    break;

                case MusicService.ACTION_UPDATE_PLAY_STATUS:
                    setResource();
                    break;

                case MusicService.ACTION_UPDATE_PLAY_MODE:
                    updatePlayMode();
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 播放音乐
     */
    private void playMusic() {
        if (MyApplication.getInstance().getMediaPlayer().isPlaying()) {
            musicBinder.pausePlay();
        } else {
            musicBinder.startPlay(MyApplication.getInstance().getCurrentMusic(),
                    MyApplication.getInstance().getMediaPlayer().getCurrentPosition());
        }
    }

    /**
     * 注册本地广播接收器
     */
    private void registerLocalReceiver() {
        Log.d(TAG, SUB + "registerLocalReceiver");
        intentFilter = new IntentFilter();
        intentFilter.addAction(MusicService.ACTION_UPDATE_CURRENT_MUSIC);
        intentFilter.addAction(MusicService.ACTION_UPDATE_DURATION);
        intentFilter.addAction(MusicService.ACTION_UPDATE_PROGRESS);
        intentFilter.addAction(MusicService.ACTION_UPDATE_PLAY_STATUS);
        intentFilter.addAction(MusicService.ACTION_UPDATE_PLAY_MODE);

        localReceiver = new LocalReceiver();
        /*注册本地广播监听器*/
        MyApplication.getInstance().getManager().registerReceiver(localReceiver, intentFilter);
    }

    /**
     * 更新当前音乐的显示信息
     */
    private void updateMusicInfo() {
        /*设置音乐名与音乐人*/
        String title = audioList.get(MyApplication.getInstance().getCurrentMusic()).getTitle();
        String artist = audioList.get(MyApplication.getInstance().getCurrentMusic()).getArtist();
        if (artist.equals("<unknown>")) {
            titleBar.setTitle(title + "—" +"未知");
        } else {
            titleBar.setTitle(title + "—" + artist);
        }
    }

    /**
     * 更新播放音乐流逝的时间
     */
    private void updateProgress() {
        int currentPosition = MyApplication.getInstance().getMediaPlayer().getCurrentPosition();
        String strElapsed = FormatHelper.formatDuration(currentPosition);
        startPosition.setText(strElapsed);
        seekbar.setProgress(MyApplication.getInstance().getMediaPlayer().getCurrentPosition());
    }

    /**
     * 更新音乐文件的总时长
     */
    private void updateDuration() {
        int currentMusic = MyApplication.getInstance().getCurrentMusic();
        int duration = audioList.get(currentMusic).getDuration();
        Log.d(TAG, SUB + duration);

        endPosition.setText(FormatHelper.formatDuration(duration));
        seekbar.setMax(duration);
    }

    /**
     * 更新播放模式显示
     */
    private void updatePlayMode() {
        Log.d(TAG, SUB + "updatePlayMode");
        String currentMode=musicBinder.getCurrentMode();
        switch (currentMode){
            case "0":
                playPatternIcon.setText(R.string.order_play);
                break;
            case "1":
                playPatternIcon.setText(R.string.random);
                break;
            case "2":
                playPatternIcon.setText(R.string.single);
                break;
            case "3":
                playPatternIcon.setText(R.string.order);
                break;
        }

    }

    private void setResource() {
        if (MyApplication.getInstance().getMediaPlayer().isPlaying()) {
            playStatus.setText(R.string.pausing);
        } else {
            playStatus.setText(R.string.playing2);
            // lyricFragment.destroyRunnable();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().getManager().unregisterReceiver(localReceiver);
    }
}
