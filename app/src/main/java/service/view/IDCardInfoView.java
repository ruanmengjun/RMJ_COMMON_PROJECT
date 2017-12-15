package service.view;

import service.entity.IDCardBean;

/**
 * Created by admin on 2017/11/2.
 */

public interface IDCardInfoView extends View {
    //view就是将presenter获取到的数据进行展示
    void onSuccess(IDCardBean idCardBean);
    void onError(String result);
}
