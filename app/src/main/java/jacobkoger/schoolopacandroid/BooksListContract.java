package jacobkoger.schoolopacandroid;

import android.support.annotation.NonNull;


public class BooksListContract {

    interface View {
        void setPresenter(@NonNull Presenter mPresenter);
    }

    interface Presenter {
        void onStart();

        void onStop();
    }

}
