package jacobkoger.schoolopacandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements AddBookFragment.OnBookMadeListener {


    NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ImageButton addBookButton;
    boolean isTeacher;
    private BooksListContract.Presenter presenter;
    BooksListFragment booksListFragment = new BooksListFragment();

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("isTeacher", Context.MODE_PRIVATE);
        isTeacher = sharedPreferences.getBoolean("isTeacher", false);

        presenter = new BooksPresenter(this, booksListFragment);
//        BookDatabase.getAppDatabase(this).bookDao().insertAll(new Book("Test", "Test", BookDatabase.getAppDatabase(this).bookDao().getAll().size()));
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navView);

        drawerToggle = setupDrawerToggle();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContent, booksListFragment).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawers();
                }
                String title = item.getTitle().toString();
                if (title.equals("Books List")) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flContent, booksListFragment).commit();
                    item.setCheckable(true);
                    item.setChecked(true);
                    //       addBookButton.setVisibility(View.VISIBLE);
                } else if (title.equals("Add Books")) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flContent, new AddBookFragment()).commit();
                    item.setCheckable(true);
                    item.setChecked(true);
                    //       addBookButton.setVisibility(View.INVISIBLE);

                }
                return false;
            }
        });
        if (!isTeacher) {
            View header = navigationView.inflateHeaderView(R.layout.header_teacher);
            navigationView.removeHeaderView(header);
            navigationView.addHeaderView(header);
        }
        addBookButton = findViewById(R.id.button_addBook);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AddBookFragment addBookFragment = new AddBookFragment();

                getSupportFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.flContent, addBookFragment).commit();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                break;
        }
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBookMade(Book... books) {
        BookDatabase.getAppDatabase(this).bookDao().insertAll(books);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContent, new BooksListFragment()).commit();
    }
}
