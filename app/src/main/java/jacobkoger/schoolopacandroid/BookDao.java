package jacobkoger.schoolopacandroid;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Query("SELECT * FROM book WHERE title LIKE :title AND " +
            "author LIKE :author LIMIT 1")
    Book findByName(String title, String author);

    @Insert
    void insertAll(Book... books);

    @Delete
    void deleteBook(Book... books);
}
