package com.jash.himalayademo.util;

import android.content.Context;

import com.jash.himalayademo.dao.DaoMaster;
import com.jash.himalayademo.dao.DaoSession;

/**
 * Created by jash
 * Date: 16-1-20
 * Time: 下午4:09
 */
public class DBUtil {
    private static DaoSession session;

    public static void initialize(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "himalaya.db", null);
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        session = master.newSession();
    }

    public static DaoSession getSession() {
        return session;
    }
}
