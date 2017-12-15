package service.presenter;

import android.content.Intent;

import service.view.View;

/**
 * Created by admin on 2017/11/2.
 */

public interface Presenter {
    //presenter主要用于网络的请求以及数据的获取

    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachView(View view);

    void attachIncomingIntent(Intent intent);//暂时没用到
}
