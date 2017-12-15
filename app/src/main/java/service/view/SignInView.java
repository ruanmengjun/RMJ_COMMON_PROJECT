package service.view;

import service.entity.SignInBean;

/**
 * Created by admin on 2017/11/2.
 */

public interface SignInView extends View {
    //view就是将presenter获取到的数据进行展示
    void onSuccess(SignInBean mBook);
    void onError(String result);
}
