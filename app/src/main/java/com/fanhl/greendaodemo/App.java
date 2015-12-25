package com.fanhl.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.fanhl.greendaodemo.dao.DaoMaster;
import com.fanhl.greendaodemo.dao.DaoSession;

/**
 * Created by fanhl on 15/12/25.
 */
public class App extends Application {

    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase          database;
    private DaoMaster               daoMaster;
    private DaoSession              daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initDatabase();
    }

    private void initDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        helper = new DaoMaster.DevOpenHelper(this, "greendaodemo-db", null);
        database = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
