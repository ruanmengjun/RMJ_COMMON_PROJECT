package ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import rmj.example.com.rmj_common_project.R;
import utils.CacheDataManager;
import utils.CommonTitleBar;

public class SetActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.btn)
    Button btn;
    private CommonTitleBar titleBar;
    @Override
    protected int initLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
//初始化控件布局等等
        titleBar = new CommonTitleBar(this);
        titleBar.setTitle("缓存");
        titleBar.hideRight(true);
    }

    @Override
    protected void initData() {
        try {
            tv.setText(CacheDataManager.getTotalCacheSize(mContext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn)
    public void click(View v){
        new Thread(new clearCache()).start();

    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(SetActivity.this,"清理完成",Toast.LENGTH_SHORT).show();
                    try {
                        tv.setText(CacheDataManager.getTotalCacheSize(SetActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        };
    };

    class clearCache implements Runnable {
        @Override
        public void run() {
            try {
                CacheDataManager.clearAllData(mContext);//清除本应用所有数据库
                //CacheDataManager.clearAllCache(SetActivity.this);
                Thread.sleep(3000);
                if (CacheDataManager.getTotalCacheSize(SetActivity.this).startsWith("0")) {
                    handler.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                return;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            if (uri != null) {
                String host = uri.getHost();
                String dataString = intent.getDataString();
                String name = uri.getQueryParameter("name");
                String id=uri.getQueryParameter("id");
                String path = uri.getPath();
                String path1 = uri.getEncodedPath();
                String queryString = uri.getQuery();
                Log.d("Alex", "host:"+host);
                Log.d("Alex", "dataString:" + dataString);
                Log.d("Alex", "id:" + id);
                Log.d("Alex", "path:" + path);
                Log.d("Alex", "path1:" + path1);
                Log.d("Alex", "queryString:" + queryString);
            }
        }
    }


}
