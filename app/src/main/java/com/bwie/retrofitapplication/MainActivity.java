package com.bwie.retrofitapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.bwie.retrofitapplication.model.HomePriority;
import com.bwie.retrofitapplication.network.RetrofitHelper;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    ;
    TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hello = (TextView) findViewById(R.id.hello);

        Observable.timer(3, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                RetrofitHelper.getClientService()
                        .homeOperations("3470667255")//订阅源
                        .subscribeOn(Schedulers.io())//工作子线程
                        .observeOn(AndroidSchedulers.mainThread())//切换线程到主线程
                        .subscribe(homePriorities -> {
                            for (HomePriority p : homePriorities) {
                                Log.d(getLocalClassName(), p.opt_name);
                                hello.setText(p.opt_name);
                            }
                        });
            }
        });


        Observable.timer(2,TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                finish();
            }
        });


    }
}
