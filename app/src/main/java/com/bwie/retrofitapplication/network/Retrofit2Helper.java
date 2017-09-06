package com.bwie.retrofitapplication.network;

import com.bwie.retrofitapplication.BuildConfig;
import com.bwie.retrofitapplication.network.api.ClientService;
import com.bwie.retrofitapplication.network.api.NewGoodsService;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liqy on 2017/9/6.
 */

public class Retrofit2Helper {

    private static OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();
    }
    /**
     * 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志,设置UA拦截器
     */
    private static void initOkHttpClient() {
        //日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //获取单例 双重检测
        if (mOkHttpClient == null) {

            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    //设置Http缓存
                    mOkHttpClient = new OkHttpClient.Builder()//构造者
                            //插入日志拦截器
                            .addInterceptor(new LoggingInterceptor.Builder()
                                    .loggable(BuildConfig.DEBUG)
                                    .setLevel(Level.BASIC)
                                    .log(Platform.INFO)
                                    .request("Request")
                                    .response("Response")
                                    .addHeader("version", BuildConfig.VERSION_NAME)
                                    .build())//日志
                            //重新连接
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    /**
     * 根据传入的baseUrl，和api创建retrofit
     */
    private static <T> T createApi(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)//设置host
                .client(mOkHttpClient)//绑定自己的HTTP客户端
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava
                .addConverterFactory(GsonConverterFactory.create())//Gson
                .build();
        return retrofit.create(clazz);
    }

    public static NewGoodsService getGoodsService(){
        return createApi(NewGoodsService.class,Constants.APP_BASE_URL);
    }

    public static ClientService getClientServicee(){
        return createApi(ClientService.class,Constants.APP_BASE_URL);
    }


}
