package com.bwie.retrofitapplication.network.api;


import com.bwie.retrofitapplication.model.RootData;
import com.bwie.retrofitapplication.network.UploadParams;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by liqy on 2017/9/6.
 */

public interface UploadService {
    @POST("api/projectDiary/add")
    Observable<RootData> upload(@Body UploadParams params);
}
