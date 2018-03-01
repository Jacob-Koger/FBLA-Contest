package jacobkoger.schoolopacandroid;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public class BookDatabaseHelper {
    private static BookDatabaseHelper instance = null;
    public static BookDatabase getInstance(Context context) {

    }
}
@Database(entities  = {Book.class}, version = 1)
abstract class BookDatabase extends RoomDatabase{
    public abstract BookDao bookDao();
}
