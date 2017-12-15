package service.view;

import service.entity.Book;

/**
 * Created by admin on 2017/11/2.
 */

public interface BookView extends View {
    //view就是将presenter获取到的数据进行展示
    void onSuccess(Book mBook);
    void onError(String result);
}
