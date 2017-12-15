package ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import rmj.example.com.rmj_common_project.R;
import ui.adapter.NewsViewPagerAdapter;
import ui.fragment.CaijingFragment;
import ui.fragment.GuojiFragment;
import ui.fragment.GuoneiFragment;
import ui.fragment.JunshiFragment;
import ui.fragment.KejiFragment;
import ui.fragment.ShehuiFragment;
import ui.fragment.ShishangFragment;
import ui.fragment.TiyuFragment;
import ui.fragment.TopFragment;
import ui.fragment.YuleFragment;
import utils.CommonTitleBar;

public class NewsActivity extends BaseActivity {

    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.view8)
    View view8;
    @BindView(R.id.view9)
    View view9;
    @BindView(R.id.view10)
    View view10;
    @BindView(R.id.lineLayout)
    LinearLayout lineLayout;
    @BindView(R.id.nav)
    HorizontalScrollView nav;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<Fragment> fragmentList;
    private CommonTitleBar titleBar;
    @Override
    protected int initLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    protected void initView() {
        titleBar = new CommonTitleBar(this);
        titleBar.setTitle("今日新闻");
        titleBar.hideRight(true);
        view1.setBackgroundColor(getResources().getColor(R.color.main_red_color));
    }

    @Override
    protected void initData() {
        fragmentList=new ArrayList<>();
        fragmentList.add(new TopFragment());
        fragmentList.add(new YuleFragment());
        fragmentList.add(new TiyuFragment());
        fragmentList.add(new ShishangFragment());
        fragmentList.add(new ShehuiFragment());
        fragmentList.add(new KejiFragment());
        fragmentList.add(new JunshiFragment());
        fragmentList.add(new GuoneiFragment());
        fragmentList.add(new GuojiFragment());
        fragmentList.add(new CaijingFragment());

        viewPager.setAdapter(new NewsViewPagerAdapter(getSupportFragmentManager(),fragmentList));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        setBottomLineShow();
                        view1.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                        break;
                    case 1:
                        setBottomLineShow();
                        view2.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                        break;
                    case 2:
                        setBottomLineShow();
                        view3.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                        break;
                    case 3:
                        setBottomLineShow();
                        view4.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                        break;
                    case 4:
                        setBottomLineShow();
                        view5.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                        break;
                    case 5:
                        setBottomLineShow();
                        view6.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                        break;
                    case 6:
                        setBottomLineShow();
                        view7.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                        break;
                    case 7:
                        setBottomLineShow();
                        view8.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                        break;
                    case 8:
                        setBottomLineShow();
                        view9.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                        break;
                    case 9:
                        setBottomLineShow();
                        view10.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8,R.id.btn9, R.id.btn10})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                viewPager.setCurrentItem(0);
                setBottomLineShow();
                view1.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                break;
            case R.id.btn2:
                viewPager.setCurrentItem(1);
                setBottomLineShow();
                view2.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                break;
            case R.id.btn3:
                viewPager.setCurrentItem(2);
                setBottomLineShow();
                view3.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                break;
            case R.id.btn4:
                viewPager.setCurrentItem(3);
                setBottomLineShow();
                view4.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                break;
            case R.id.btn5:
                viewPager.setCurrentItem(4);
                setBottomLineShow();
                view5.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                break;
            case R.id.btn6:
                viewPager.setCurrentItem(5);
                setBottomLineShow();
                view6.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                break;
            case R.id.btn7:
                viewPager.setCurrentItem(6);
                setBottomLineShow();
                view7.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                break;
            case R.id.btn8:
                viewPager.setCurrentItem(7);
                setBottomLineShow();
                view8.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                break;
            case R.id.btn9:
                viewPager.setCurrentItem(8);
                setBottomLineShow();
                view9.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                break;
            case R.id.btn10:
                viewPager.setCurrentItem(9);
                setBottomLineShow();
                view10.setBackgroundColor(getResources().getColor(R.color.main_red_color));
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setBottomLineShow(){
        view1.setBackgroundColor(getResources().getColor(R.color.white));
        view2.setBackgroundColor(getResources().getColor(R.color.white));
        view3.setBackgroundColor(getResources().getColor(R.color.white));
        view4.setBackgroundColor(getResources().getColor(R.color.white));
        view5.setBackgroundColor(getResources().getColor(R.color.white));
        view6.setBackgroundColor(getResources().getColor(R.color.white));
        view7.setBackgroundColor(getResources().getColor(R.color.white));
        view8.setBackgroundColor(getResources().getColor(R.color.white));
        view9.setBackgroundColor(getResources().getColor(R.color.white));
        view10.setBackgroundColor(getResources().getColor(R.color.white));
    }
}
