package jacobkoger.schoolopacandroid;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class BookHolder extends RecyclerView.ViewHolder {
    public final TextView bookTitle;
    public final Button removeButton;

    public BookHolder(View view) {
        super(view);
        bookTitle = view.findViewById(R.id.book_title);
        removeButton = view.findViewById(R.id.remove_button);
    }

}
