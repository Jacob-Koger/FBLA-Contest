package jacobkoger.schoolopacandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {
    List<Book> bookList = new ArrayList<>();

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
    public void onBindViewHolder(BookHolder holder, final int position) {
        final Book book = bookList.get(holder.getAdapterPosition());
        holder.bookTitle.setText("Title: " + book.getTitle());
        holder.bookAuthor.setText("Author: " + book.getAuthor());
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDatabase bd = BookDatabase.getAppDatabase(view.getContext());
                bd.bookDao().deleteBook(bd.bookDao().findByName(book.getTitle(), book.getAuthor()));
                bookList.remove(position);
                notifyItemRemoved(position);
            }
        });
        holder.checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDatabase bd = BookDatabase.getAppDatabase(view.getContext());
                bd.bookDao().checkOut(book.getTitle(), book.getAuthor(), true);
                bookList.set(position, new Book(book.getTitle(), book.getAuthor(), position, true));
                notifyItemChanged(position);
            }
        });
        if(BookDatabase.getAppDatabase(holder.checkoutButton.getContext()).bookDao().findByName(book.getTitle(), book.getAuthor()).getCheckedOut()) {
            holder.checkoutButton.setText("Already Checked Out");
            holder.checkoutButton.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
