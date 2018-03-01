package jacobkoger.schoolopacandroid;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Query("SELECT * FROM book WHERE title LIKE :title AND " +
            "author LIKE :author LIMIT 1")
    Book findByName(String title, String author);

    @Query("UPDATE book SET isCheckedOut = :checkOut where title LIKE :title AND " +
            "author LIKE :author")
    void checkOut(String title, String author, boolean checkOut);

    @Insert
    void insertAll(Book... books);

    @Delete
    void deleteBook(Book... books);
}
