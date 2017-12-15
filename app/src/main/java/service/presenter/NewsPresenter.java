package service.presenter;

import android.content.Context;
import android.content.Intent;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import service.entity.News;
import service.manager.DataManager;
import service.view.NewsView;
import service.view.View;

/**
 * Created by admin on 2017/11/25.
 */

public class NewsPresenter implements Presenter {
    private Context mContext;
    private DataManager manager;
    private NewsView newsView;
    private CompositeSubscription mCompositeSubscription;
    private News myNews;

    public NewsPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        newsView = (NewsView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }


    public void getNewsData(String type, String key) {
        mCompositeSubscription.add(manager.getNewsData(type, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onCompleted() {
                        if (myNews != null) {
                            newsView.onSuccess(myNews);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(News news) {
                        myNews = news;
                    }
                })
        )

        ;
    }
}
