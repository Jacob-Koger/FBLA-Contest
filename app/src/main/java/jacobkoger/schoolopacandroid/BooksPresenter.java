package jacobkoger.schoolopacandroid;


import android.content.Context;
import android.support.annotation.NonNull;

public class BooksPresenter implements BooksListContract.Presenter {

    final BooksListContract.View mView;
    final Context mContext;

    public BooksPresenter(@NonNull Context context, @NonNull BooksListContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
