package ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import base.BaseActivity;
import rmj.example.com.rmj_common_project.R;
import utils.CommonTitleBar;

public class GlidePicActivity extends BaseActivity {
//    @BindView(R.id.img)
//    ImageView img;
    private CommonTitleBar titleBar;
    private String url = "http://onat9tcj8.bkt.clouddn.com/baidu.png";



    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.mipmap.banner,
            R.mipmap.banner,
            R.mipmap.banner,
            R.mipmap.banner,
            R.mipmap.banner
    };
    //存放图片的标题
    private String[]  titles = new String[]{
            "巩俐不低俗，我就不能低俗",
            "扑树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级",
            "乐视网TV版大派送",
            "热血屌丝的反杀"
    };
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mViewPaper.setCurrentItem(currentItem);
        }
    };



    @Override
    protected int initLayoutId() {
        return R.layout.viewpager_layout;
    }

    @Override
    protected void initView() {
        //初始化控件布局等等
//        titleBar = new CommonTitleBar(this);
//        titleBar.setTitle("首页");
//        titleBar.hideRight(true);
    }

    @Override
    protected void initData() {
//        ImageUtil.loadImage(url,img);
//       // GlideUtils.getInstance().LoadContextCircleBitmap(mContext,url,img);
//        GlideUtils.getInstance().LoadContextBlurBitmap(mContext,url,img);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPaper = (ViewPager) findViewById(R.id.vp);

        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));
        dots.add(findViewById(R.id.dot_4));

        title = (TextView) findViewById(R.id.title);
        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
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
            int flage=0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        flage = 0 ;
                        break ;
                    case MotionEvent.ACTION_MOVE:
                        flage = 1 ;
                        break ;
                    case  MotionEvent.ACTION_UP :
                        if (flage == 0) {
                            int item = mViewPaper.getCurrentItem();
                            if (item == 0) {
                                Log.i("s",item+"");
//                                Toast.makeText(GlidePicActivity.this,item,Toast.LENGTH_SHORT).show();
                            } else if (item == 1) {
                                Log.i("s",item+"");
                                //Toast.makeText(GlidePicActivity.this,item,Toast.LENGTH_SHORT).show();
                            } else if (item == 2) {
                                Log.i("s",item+"");
                                //Toast.makeText(GlidePicActivity.this,item,Toast.LENGTH_SHORT).show();
                            }else if (item == 3) {
                                Log.i("s",item+"");
                                //Toast.makeText(GlidePicActivity.this,item,Toast.LENGTH_SHORT).show();
                            }else if (item == 4) {
                                startActivity(new Intent(GlidePicActivity.this,SetActivity.class));
                                Log.i("s",item+"");
                                //Toast.makeText(GlidePicActivity.this,item,Toast.LENGTH_SHORT).show();
                            }
                        }
                        break ;

                }
                return false;
            }
        });
    }

    /**
     * 自定义Adapter
     * @author liuyazhuang
     *
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
                3,
                3,
                TimeUnit.SECONDS);
    }


    /**
     * 图片轮播任务
     * @author liuyazhuang
     *
     */
    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            handler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */




    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

}
