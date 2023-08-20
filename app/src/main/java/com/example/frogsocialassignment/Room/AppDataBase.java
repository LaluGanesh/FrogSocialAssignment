package com.example.frogsocialassignment.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.frogsocialassignment.MovieListResponseModel;
import com.example.frogsocialassignment.Room.Dao.CartDao;
import com.example.frogsocialassignment.Room.Entity.UserLogin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserLogin.class,MovieListResponseModel.class}, version = Constants.ROOM_DATABASE_VERSION, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDataBase extends RoomDatabase {

    private static volatile AppDataBase APPINSTANCE;

    public abstract CartDao cartDao();

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDataBase getRoomDatabase(final Context context) {

        if (APPINSTANCE == null) {
            synchronized (AppDataBase.class) {
                APPINSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, Constants.ROOM_DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return APPINSTANCE;

    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {


        }
    };

    public static void destroyInstance() {
        APPINSTANCE = null;
    }

}
