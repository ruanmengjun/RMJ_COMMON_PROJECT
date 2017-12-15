package service.presenter;

import android.content.Context;
import android.content.Intent;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import service.entity.SignInBean;
import service.manager.DataManager;
import service.view.SignInView;
import service.view.View;

/**
 * Created by admin on 2017/11/2.
 */

public class LoginPresenter implements Presenter {
    private Context mContext;
    private DataManager manager;
    private SignInView signInView;
    private CompositeSubscription mCompositeSubscription;
    private SignInBean mSignInBean;

    public LoginPresenter(Context context){
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
        signInView=(SignInView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public  void getLoginData(String device_id,String mobile,String password,String push_id,String sign){
        mCompositeSubscription.add(manager.getSignData(device_id,mobile,password,push_id,sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignInBean>() {
                    @Override
                    public void onCompleted() {
                        if (mSignInBean!=null){
                            signInView.onSuccess(mSignInBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        signInView.onError("登录失败");
                    }

                    @Override
                    public void onNext(SignInBean signInBean) {
                        mSignInBean=signInBean;
                    }
                })
        );

    }
}
