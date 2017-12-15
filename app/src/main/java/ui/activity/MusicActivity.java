package ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.transition.Explode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import app.MyApplication;
import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import rmj.example.com.rmj_common_project.R;
import service.entity.Audio;
import ui.adapter.MusicListAdapter;
import ui.service.MusicService;
import utils.AudioUtil;
import utils.BounceListView;
import utils.CircleProgressView;
import utils.CommonTitleBar;
import utils.FormatHelper;
import utils.ImageUtil;
import utils.SnackbarUtil;

public class MusicActivity extends BaseActivity {
    private static final String TAG = "RhymeMusic";
    private static final String SUB = "[PlaybackActivity]#";
    @BindView(R.id.text_sum_songs)
    TextView textSumSongs;
    @BindView(R.id.id_music_list)
    BounceListView idMusicList;
    @BindView(R.id.seek_bar)
    CircleProgressView seekBar;
    @BindView(R.id.detail_previous)
    ImageButton detailPrevious;
    @BindView(R.id.detail_play)
    ImageButton detailPlay;
    @BindView(R.id.detail_next)
    ImageButton detailNext;
    @BindView(R.id.text_current_play_mode)
    TextView textCurrentPlayMode;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.music_title)
    TextView musicTitle;
    @BindView(R.id.music_artist)
    TextView musicArtist;
    @BindView(R.id.status_icon)
    TextView statusIcon;
    @BindView(R.id.play_pattern_icon)
    TextView playPatternIcon;
    @BindView(R.id.vp)
    ViewPager mViewPaper;
    @BindView(R.id.dot_0)
    View dot0;
    @BindView(R.id.dot_1)
    View dot1;
    @BindView(R.id.dot_2)
    View dot2;
    @BindView(R.id.dot_3)
    View dot3;
    @BindView(R.id.dot_4)
    View dot4;
    @BindView(R.id.img_title)
    TextView imgTitle;
    @BindView(R.id.play_icon)
    TextView playIcon;
    @BindView(R.id.multiple_icon)
    TextView multipleIcon;
    @BindView(R.id.bottomLayout)
    RelativeLayout bottomLayout;
    @BindView(R.id.img)
    ImageView img;
    private ArrayList<Audio> audioList;
    private MusicListAdapter musicListAdapter;

    private LocalReceiver localReceiver;
    private MusicService.MusicBinder musicBinder;

    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private final int[] imageIds = new int[]{
            R.mipmap.one,
            R.mipmap.two,
            R.mipmap.three,
            R.mipmap.four,
            R.mipmap.five
    };
    //存放图片的标题
    private final String[] titles = new String[]{
            "巩俐不低俗，我就不能低俗",
            "扑树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级",
            "乐视网TV版大派送",
            "热血屌丝的反杀"
    };
    private ScheduledExecutorService scheduledExecutorService;

    private MyHandler myHandler;

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            mViewPaper.setCurrentItem(currentItem);
//        }
//    };

    public static class MyHandler extends Handler{
        //声明一个弱引用对象
        WeakReference<MusicActivity> mReference;
        public MyHandler(MusicActivity activity){
            mReference=new WeakReference<MusicActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //在使用activity之前先判空处理
            if (mReference != null && mReference.get() != null) {
                mReference.get().mViewPaper.setCurrentItem(mReference.get().currentItem);
            }
        }
    }


    @Override
    protected int initLayoutId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }
        return R.layout.activity_music;
    }

    @Override
    protected void initView() {
//初始化控件布局等等
        CommonTitleBar titleBar = new CommonTitleBar(this);
        titleBar.setTitle("找钱花音乐平台");
        titleBar.hideBoth(true);

        statusIcon.setTypeface(iconfont);
        playIcon.setTypeface(iconfont);
        multipleIcon.setTypeface(iconfont);
        playPatternIcon.setTypeface(iconfont);
    }

    @Override
    protected void initData() {
        audioList = new ArrayList<>();
        loadData();

        musicBinder = MyApplication.getInstance().getMusicBinder();
        /*初始化音乐列表*/
        registerLocalReceiver();  // 注册本地广播接收器
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myHandler=new MyHandler(this);
        idMusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyApplication.getInstance().getMusicBinder().startPlay(position, 0);
                /*播放流逝的时间*/
                updateProgress();
                /*音乐的总时间*/
                updateDuration();
                /*播放页面的音乐标题*/
                updateMusicInfo();
                /*显示播放模式*/
                updatePlayMode();
                Bitmap bitmap = ImageUtil.getAlbumCover(getApplicationContext(), audioList.get(position).getId());
                if (bitmap!=null){
                    img.setImageBitmap(bitmap);
                }else{
                    img.setImageResource(R.mipmap.transparent);
                }

            }
        });

        //显示的图片
        images = new ArrayList<>();
        for (int imageId : imageIds) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageId);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<>();
        dots.add(dot0);
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);
        imgTitle.setText(titles[0]);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);
        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                imgTitle.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.mipmap.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.mipmap.dot_nomal);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        mViewPaper.setOnTouchListener(new View.OnTouchListener() {
            int flag = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        flag = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        flag = 1;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (flag == 0) {
                            int item = mViewPaper.getCurrentItem();
                            if (item == 0) {
                                Log.i("s", item + "");
//                                Toast.makeText(GlidePicActivity.this,item,Toast.LENGTH_SHORT).show();
                            } else if (item == 1) {
                                Log.i("s", item + "");
                                //Toast.makeText(GlidePicActivity.this,item,Toast.LENGTH_SHORT).show();
                            } else if (item == 2) {
                                Log.i("s", item + "");
                                //Toast.makeText(GlidePicActivity.this,item,Toast.LENGTH_SHORT).show();
                            } else if (item == 3) {
                                Log.i("s", item + "");
                                //Toast.makeText(GlidePicActivity.this,item,Toast.LENGTH_SHORT).show();
                            } else if (item == 4) {
                                startActivity(new Intent(MusicActivity.this, SetActivity.class));
                                Log.i("s", item + "");
                                //Toast.makeText(GlidePicActivity.this,item,Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;

                }
                return false;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("rmj", "restart");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("rmj", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("rmj", "destroy");
        MyApplication.getInstance().getManager().unregisterReceiver(localReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("rmj", "resume");
        boolean flag = MyApplication.getInstance().isSelected();
        if (MyApplication.getInstance().getMediaPlayer().isPlaying() || flag) {
            setResource();
            /*播放流逝的时间*/
            updateProgress();
                /*音乐的总时间*/
            updateDuration();
                /*播放页面的音乐标题*/
            updateMusicInfo();
                /*显示播放模式*/
            updatePlayMode();
            Bitmap bitmap = ImageUtil.getAlbumCover(mContext, audioList.get(MyApplication.getInstance().getCurrentMusic()).getId());
            if (bitmap!=null){
                img.setImageBitmap(bitmap);
            }else{
                img.setImageResource(R.mipmap.transparent);
            }
        }

    }

    /**
     * 点击播放全部
     */
    public void playAllSongs(View view) {
        Log.d(TAG, SUB + "playAllSongs");
        if (audioList.size() != 0) {
            MyApplication.getInstance().getMusicBinder().startPlay(0, 0);
            updatePlayMode();
            Audio audio = audioList.get(MyApplication.getInstance().getCurrentMusic());
            String tips = "正在播放: " + audio.getTitle() + " — " + audio.getArtist();
            Snackbar.make(view, tips, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            SnackbarUtil.LongSnackbar(view, "播放列表为空!", SnackbarUtil.Info).setAction("心随我动", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
        }

    }

    /**
     * 加载音乐列表数据
     */
    private void loadData() {
        /*加载音乐列表*/
        audioList = AudioUtil.getAudioList(mContext);
        musicListAdapter = new MusicListAdapter(getApplicationContext(), audioList);
        idMusicList.setAdapter(musicListAdapter);

        /*显示本地音乐数量*/
        String allSongs = "(共" + audioList.size() + "首)";
        textSumSongs.setText(allSongs);
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
        IntentFilter intentFilter = new IntentFilter();
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
                    musicListAdapter.notifyDataSetChanged();
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
     * 更新当前音乐的显示信息
     */
    private void updateMusicInfo() {
        /*设置音乐名与音乐人*/

        String title = audioList.get(MyApplication.getInstance().getCurrentMusic()).getTitle();
        String artist = audioList.get(MyApplication.getInstance().getCurrentMusic()).getArtist();
        musicTitle.setText(title);
        if (artist.equals("<unknown>")) {
            musicArtist.setText("未知");
        } else {
            musicArtist.setText(artist);
        }

//        String showTitle = title + "\n" + artist;
//
//        name.setText(showTitle);
    }

    /**
     * 更新播放音乐流逝的时间
     */
    private void updateProgress() {
        int currentPosition = MyApplication.getInstance().getMediaPlayer().getCurrentPosition();
        String strElapsed = FormatHelper.formatDuration(currentPosition);
        //textElapsed.setText(strElapsed);
        seekBar.setProgress(MyApplication.getInstance().getMediaPlayer().getCurrentPosition());

    }

    /**
     * 更新音乐文件的总时长
     */
    private void updateDuration() {
        int currentMusic = MyApplication.getInstance().getCurrentMusic();
        int duration = audioList.get(currentMusic).getDuration();
        Log.d(TAG, SUB + duration);

        //textDuration.setText(FormatHelper.formatDuration(duration));
        seekBar.setMaxProgress(duration);
    }

    /**
     * 更新播放模式显示
     */
    private void updatePlayMode() {
        Log.d(TAG, SUB + "updatePlayMode");
        //textCurrentPlayMode.setText(musicBinder.getCurrentMode());
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
            statusIcon.setText(R.string.play);
            //detailPlay.setBackgroundResource(R.mipmap.detail_play);
        } else {
            statusIcon.setText(R.string.pause);
            //detailPlay.setBackgroundResource(R.mipmap.detail_pause);
        }

    }

    @OnClick({R.id.detail_play, R.id.detail_previous, R.id.detail_next, R.id.status_icon, R.id.play_pattern_icon, R.id.music_title_layout})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.status_icon:
                Boolean flag = MyApplication.getInstance().isSelected();
                if (flag) {
                    playMusic();
                } else {
                    SnackbarUtil.LongSnackbar(v, "请选择一首歌曲播放!", SnackbarUtil.Info).setAction("心随我动", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }
                break;
            case R.id.detail_play:
                playMusic();
                break;
            case R.id.detail_previous:
                detailPlay.setBackgroundResource(R.mipmap.detail_play);
                musicBinder.preTrack();
                break;
            case R.id.detail_next:
                detailPlay.setBackgroundResource(R.mipmap.detail_play);
                musicBinder.nextTrack();
                break;
            case R.id.play_pattern_icon:
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
            case R.id.music_title_layout:
                startActivity(new Intent(MusicActivity.this, MusicDetailActivity.class));
                break;
        }
    }


    /**
     * 自定义Adapter
     *
     * @author liuyazhuang
     */
    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                5,
                5,
                TimeUnit.SECONDS);
    }


    /**
     * 图片轮播任务
     *
     * @author liuyazhuang
     */
    private class ViewPageTask implements Runnable {

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
//            handler.sendEmptyMessage(0);
            myHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */


    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

}
