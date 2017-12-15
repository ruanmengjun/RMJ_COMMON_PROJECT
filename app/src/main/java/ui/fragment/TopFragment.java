package ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import constant.GlobalParams;
import rmj.example.com.rmj_common_project.R;
import service.entity.News;
import service.presenter.NewsPresenter;
import service.view.NewsView;
import ui.adapter.NewsAdapter;

public class TopFragment extends BaseFragment{

    Unbinder unbinder;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.wv_content)
    WebView wvContent;
    private NewsPresenter newsPresenter = new NewsPresenter(mContext);
    private NewsAdapter adapter;
    private News myNews;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_top;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = wvContent.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsPresenter.onCreate();
        newsPresenter.attachView(newsView);
        newsPresenter.getNewsData("top", GlobalParams.JUHESHUJU_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private NewsView newsView = new NewsView() {
        @Override
        public void onSuccess(News news) {
            myNews = news;
            adapter = new NewsAdapter(mContext, news);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listview.setVisibility(View.GONE);
                    wvContent.setVisibility(View.VISIBLE);

                    String url=myNews.getResult().getData().get(position).getUrl();

                    if (!TextUtils.isEmpty(url)){
                        wvContent.loadUrl(url);
                    }else{
                        Toast.makeText(mContext,"出错了",Toast.LENGTH_SHORT).show();
                    }

                    wvContent.setWebViewClient(new WebViewClient());

                    wvContent.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            Toast.makeText(mContext,"返回了",Toast.LENGTH_SHORT).show();
                            //这event.getAction() == KeyEvent.ACTION_DOWN表示是返回键事件
                            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                                if (keyCode == KeyEvent.KEYCODE_BACK && wvContent.canGoBack()) {  //表示按返回键 时的操作
                                    wvContent.goBack();   //后退
                                    return true;    //已处理     返回true表示被处理否则返回false
                                }
                            }
                            return false;
                        }
                    });
                }
            });
        }

        @Override
        public void onError(String result) {
            Toast.makeText(mContext, "失败", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        newsPresenter.onStop();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //清除记录
        wvContent.clearCache(true);
        wvContent.clearHistory();
        wvContent.clearFormData();

        wvContent.destroy();
        unbinder.unbind();

    }
}
