package jacobkoger.schoolopacandroid;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

@Database(entities = {Book.class}, version = 3)
public abstract class BookDatabase extends RoomDatabase {

    private static BookDatabase INSTANCE;

    public abstract BookDao bookDao();

    public static BookDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static BookDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                BookDatabase.class,
                "book-database").allowMainThreadQueries().fallbackToDestructiveMigration()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getAppDatabase(context).bookDao().insertAll(new Book("test", "test", getAppDatabase(context).bookDao().getAll().size(), false));
                            }
                        });
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);

                    }
                })
                .build();
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

