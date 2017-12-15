package service.view;

import service.entity.News;

/**
 * Created by admin on 2017/11/2.
 */

public interface NewsView extends View {
    //view就是将presenter获取到的数据进行展示
    void onSuccess(News news);
    void onError(String result);
}
