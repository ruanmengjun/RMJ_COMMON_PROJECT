package service;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import app.MyApplication;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetworkUtil;

/**
 * Created by admin on 2017/11/2.
 */

public class RetrofitHelper {
    private String CACHE_PATH = "okhttp_cache";
    private Context context;
//    private String baseUrl="https://api.douban.com/v2/";
//    private String baseUrl="https://api.faceid.com/";
    private String baseUrl="http://v.juhe.cn/";

    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    private OkHttpClient okHttpClient ;

    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }
    private RetrofitHelper(Context mContext){
        context = mContext;
        init();
    }

    private void init(){
//        //初始化OKHttp
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logInterceptor)//日志拦截器
                .addNetworkInterceptor(new CacheInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)//设置连接超时
                .retryOnConnectionFailure(true)
                .cache(new Cache(new File(MyApplication.getInstance().getCacheDir(), CACHE_PATH), 1024 * 1024 * 10))
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))//json数据转换
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//将Callable接口转换成Observable接口
//                .client(getUnsafeOkHttpClient())//网络请求客户端为okhttp
                .client(okHttpClient)//网络请求客户端为okhttp
                .build();
    }


    class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            //可以进行网络重连接等操作
            //@link 这篇博客写的很详细:http://www.jianshu.com/p/faa46bbe8a2e
            Request request = chain.request();
            Response response = chain.proceed(request);
            if (NetworkUtil.isNetworkAvailable()) {
                // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + 0)
                        .build();
            } else {
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24)
                        .build();
            }
            return response;
        }
    }

    public  OkHttpClient getUnsafeOkHttpClient() {

        try {
            InputStream inputStream = null;
            inputStream=context.getAssets().open("ssl_support.crt");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Certificate certificate = certificateFactory.generateCertificate(inputStream);
            KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
            keyStore.load(null, null);
            keyStore.setCertificateEntry("trust", certificate);

            TrustManagerFactory trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();


            //初始化OKHttp
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logInterceptor)//日志拦截器
                    .addNetworkInterceptor(new CacheInterceptor())
                    .connectTimeout(15, TimeUnit.SECONDS)//设置连接超时
                    .retryOnConnectionFailure(true)
                    .cache(new Cache(new File(MyApplication.getInstance().getCacheDir(), CACHE_PATH), 1024 * 1024 * 10))
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .build();

            if (inputStream!=null){
                inputStream.close();
            }
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public RetrofitService getServer(){
        //RetrofitService接口类的实例化
        return mRetrofit.create(RetrofitService.class);
    }
}
