package com.bwie.retrofitapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bwie.retrofitapplication.model.LocalGroup;
import com.bwie.retrofitapplication.network.GoodParams;
import com.bwie.retrofitapplication.network.Retrofit2Helper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        Retrofit2Helper.getGoodsService()
//                .avatarsSubjects("3470667255")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(avatarSubject -> {
//
//                });

        GoodParams params = new GoodParams();
        final List<Long> list = new ArrayList<>();
        list.add(63787875L);
        list.add(55197488L);
        list.add(72841648L);
        list.add(61962889L);
        list.add(72472781L);
        params.goods_ids = list;

        Retrofit2Helper.getGoodsService().localGroups(params, "3470667255")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String, String>>() {
                    @Override
                    public void accept(Map<String, String> stringObjectMap) throws Exception {

                        stringObjectMap.remove("server_time");

                        for (Object o : stringObjectMap.values()) {
                            Log.d(getLocalClassName(),o.toString());
                            Gson gson=new Gson();
//                                Log.d(getLocalClassName(),gson.fromJson(o.toString(), LocalGroup.class).toString());
                        }

                    }
                });


    }
}
