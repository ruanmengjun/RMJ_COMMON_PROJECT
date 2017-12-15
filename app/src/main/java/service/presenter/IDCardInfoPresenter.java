package service.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import service.entity.IDCardBean;
import service.manager.DataManager;
import service.view.IDCardInfoView;
import service.view.View;

/**
 * Created by admin on 2017/11/22.
 */

public class IDCardInfoPresenter implements Presenter {
    private Context mContext;
    private IDCardInfoView idCardInfoView;
    private DataManager manager;
    private IDCardBean idCardBean;
    private CompositeSubscription mCompositeSubscription;

    public IDCardInfoPresenter(Context context){
        this.mContext=context;
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
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        idCardInfoView=(IDCardInfoView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getIDCardInfo(Map<String, RequestBody> image){
        mCompositeSubscription.add(manager.getIDCardInfo(image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IDCardBean>() {
                    @Override
                    public void onCompleted() {
                        if (idCardBean!=null){
                            idCardInfoView.onSuccess(idCardBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        idCardInfoView.onError("获取失败");
                    }

                    @Override
                    public void onNext(IDCardBean bean) {
                        idCardBean=bean;
                    }
                })
        );
    }
}
