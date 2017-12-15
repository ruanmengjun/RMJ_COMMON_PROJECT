package service.manager;

import android.content.Context;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import service.RetrofitHelper;
import service.RetrofitService;
import service.entity.Book;
import service.entity.IDCardBean;
import service.entity.News;
import service.entity.SignInBean;

/**
 * Created by admin on 2017/11/2.
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
    public Observable<Book> getSearchBooks(String name, String tag, int start, int count){
        return mRetrofitService.getSearchBook(name,tag,start,count);
    }

    public Observable<SignInBean> getSignData(String device_id,String mobile,String password,String push_id,String sign){
        return mRetrofitService.getSignData(device_id,mobile,password,push_id,sign);
    }

    public Observable<IDCardBean> getIDCardInfo(Map<String, RequestBody> image){
        return mRetrofitService.getIDCardData(image);
    }

    public Observable<News> getNewsData(String type,String key){
        return mRetrofitService.getNewsData(type,key);
    }
}
