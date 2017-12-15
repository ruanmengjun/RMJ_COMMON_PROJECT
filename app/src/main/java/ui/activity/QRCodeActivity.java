package ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import rmj.example.com.rmj_common_project.R;
import ui.adapter.QRCodeViewPagerAdapter;
import ui.fragment.AddQRFragment;
import ui.fragment.AddQRLogoFragment;
import ui.fragment.QRLongTouchScanFragment;
import ui.fragment.QRScanFragment;
import utils.CommonTitleBar;

public class QRCodeActivity extends BaseActivity {
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private CommonTitleBar titleBar;
    private List<Fragment> list;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void initView() {
        titleBar = new CommonTitleBar(this);
        titleBar.setTitle("二维码相关");
        titleBar.hideRight(true);
        view1.setBackgroundColor(getResources().getColor(R.color.main_red_color));
    }

    @Override
    protected void initData() {
        list=new ArrayList<Fragment>();
        list.add(new AddQRFragment());
        list.add(new AddQRLogoFragment());
        list.add(new QRScanFragment());
        list.add(new QRLongTouchScanFragment());
        viewPager.setAdapter(new QRCodeViewPagerAdapter(getSupportFragmentManager(),list));

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
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
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
        }
    }

    public void setBottomLineShow(){
        view1.setBackgroundColor(getResources().getColor(R.color.white));
        view2.setBackgroundColor(getResources().getColor(R.color.white));
        view3.setBackgroundColor(getResources().getColor(R.color.white));
        view4.setBackgroundColor(getResources().getColor(R.color.white));
    }
}
