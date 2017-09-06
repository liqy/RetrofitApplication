package com.bwie.retrofitapplication.network.api;

import com.bwie.retrofitapplication.model.AvatarSubject;
import com.bwie.retrofitapplication.network.GoodParams;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by liqy on 2017/9/6.
 */

public interface NewGoodsService {

    @GET("avatars_subjects")
    Observable<AvatarSubject> avatarsSubjects(@Query("pdduid") String pdduid);

    @POST("goods/local_groups")
    Observable<JsonObject> localGroups(@Body GoodParams params, @Query("pdduid") String pdduid);

}
