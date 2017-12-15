package service;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;
import service.entity.Book;
import service.entity.IDCardBean;
import service.entity.News;
import service.entity.SignInBean;

/**
 * Created by admin on 2017/11/1.
 */

public interface RetrofitService {

    /*获取图书*/
    @POST("book/search")
    @FormUrlEncoded
    Observable<Book> getSearchBook(
            @Field("q")String name,@Field("tag")String tag,@Field("start")int start,@Field("count")int count
    );

    /*登录*/
    @POST("UserCenter/signIn")
    @FormUrlEncoded
    Observable<SignInBean> getSignData(
            @Field("device_id") String device_id, @Field("mobile") String mobile, @Field("password") String password, @Field("push_id") String push_id, @Field("sign") String sign
    );

//    /*上传文件*/
    @POST("faceid/v1/ocridcard")
    @Multipart
    Observable<IDCardBean> getIDCardData( @PartMap Map<String, RequestBody> image);

    /*获取新闻数据*/
    @POST("toutiao/index")
    @FormUrlEncoded
    Observable<News> getNewsData(@Field("type") String type, @Field("key") String key);

}
