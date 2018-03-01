package jacobkoger.schoolopacandroid;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class BookHolder extends RecyclerView.ViewHolder {
    public final TextView bookTitle;
    public final TextView bookAuthor;
    public final Button removeButton;
    public final Button checkoutButton;

    public BookHolder(View view) {
        super(view);
        bookTitle = view.findViewById(R.id.book_title);
        bookAuthor = view.findViewById(R.id.book_author);
        checkoutButton = view.findViewById(R.id.checkout_button);
        removeButton = view.findViewById(R.id.remove_button);
    }

}
