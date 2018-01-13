package com.smalldemo.pch.smalldemo.utils;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.smalldemo.pch.smalldemo.model.BasicOjectDao;
import com.smalldemo.pch.smalldemo.model.DaoMaster;
import com.smalldemo.pch.smalldemo.model.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Used by GreenDao to allow a unique Dao Session to be used.
 */
public class App extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "basic-db", null);
        SQLiteDatabase sqLiteDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
