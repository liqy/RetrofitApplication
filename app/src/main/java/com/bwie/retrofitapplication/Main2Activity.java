package com.bwie.retrofitapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bwie.retrofitapplication.model.LocalGroup;
import com.bwie.retrofitapplication.model.RootData;
import com.bwie.retrofitapplication.network.GoodParams;
import com.bwie.retrofitapplication.network.Retrofit2Helper;
import com.bwie.retrofitapplication.network.UploadParams;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        GoodParams params = new GoodParams();
        final List<Long> list = new ArrayList<>();
        list.add(63787875L);
        list.add(55197488L);
        list.add(72841648L);
        list.add(61962889L);
        list.add(72472781L);
        params.goods_ids = list;

        Retrofit2Helper.getGoodsService().localGroups(params, "3470667255")
                //TODO 数据转换
                .map(new Function<JsonObject, Map<String, LocalGroup>>() {
                    @Override
                    public Map<String, LocalGroup> apply(@NonNull JsonObject jsonObject) throws Exception {
                        jsonObject.remove("server_time");//TODO 移除server_time节点

                        //TODO 注意一下
                        return new Gson().fromJson(jsonObject,
                                new TypeToken<Map<String, LocalGroup>>() {
                                }.getType());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String, LocalGroup>>() {
                    @Override
                    public void accept(Map<String, LocalGroup> retMap) throws Exception {
                        for (LocalGroup g : retMap.values()) {
                            Log.d(getLocalClassName(), g.toString());
                        }
                    }
                });


        Retrofit2Helper.getUploadService().upload(new UploadParams()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<RootData>() {
            @Override
            public void accept(RootData rootData) throws Exception {

            }
        });


    }
}
