package base;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rmj.example.com.rmj_common_project.R;
import utils.StatusBarCompat;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected Unbinder mUnbinder;
    protected Typeface iconfont;

    protected abstract int initLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initLayoutId();
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(initLayoutId());
        iconfont = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
        StatusBarCompat.compat(this, getResources().getColor(R.color.blue_main));

        mUnbinder = ButterKnife.bind(this);
        initView();
        initData();


    }


    @Override
    protected void onDestroy() {
     //   mUnbinder.unbind();
        super.onDestroy();
    }
}
