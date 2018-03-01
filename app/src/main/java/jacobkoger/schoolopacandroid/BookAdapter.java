package jacobkoger.schoolopacandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {
    List<Book> bookList;

    public BookAdapter(Context context) {
        bookList = BookDatabase.getAppDatabase(context).bookDao().getAll();
    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rows, parent, false);
        return new BookHolder(v);
    }

    @Override
    public void onBindViewHolder(BookHolder holder, int position) {
        final Book book = bookList.get(holder.getAdapterPosition());
        holder.bookTitle.setText(book.getTitle());
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDatabase bd = BookDatabase.getAppDatabase(view.getContext());
                bd.bookDao().deleteBook(bd.bookDao().findByName(book.getTitle(), book.getAuthor()));
                bookList.remove(book.getIndex());
                notifyItemRemoved(book.getIndex());
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
