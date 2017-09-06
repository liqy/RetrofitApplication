package com.bwie.retrofitapplication.network.api;

import com.bwie.retrofitapplication.model.HomePriority;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by liqy on 2017/9/6.
 */

public interface ClientService {

    @GET("home_operations")
    Observable<List<HomePriority>> homeOperations(@Query("pdduid") String pdduid);
}
