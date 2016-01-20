package com.jash.himalayademo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jash.himalayademo.util.DBUtil;

/**
 * Created by jash
 * Date: 16-1-20
 * Time: 上午10:34
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        DBUtil.initialize(this);
    }
}
