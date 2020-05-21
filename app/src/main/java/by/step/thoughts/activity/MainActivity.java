package by.step.thoughts.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
import by.step.thoughts.data.AppDatabase;
import by.step.thoughts.entity.Category;
import by.step.thoughts.fragment.BasketFragment;
import by.step.thoughts.fragment.ShopFragment;
import by.step.thoughts.viewmodel.DatabaseViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class MainActivity extends AppCompatActivity {

    String activeFragmentTag = ShopFragment.class.getSimpleName();
    MaterialToolbar topAppBar;
    BottomNavigationView bottomNavBar;

    //  private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "MainActivity: onCreate");

//        Thread th = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                AppDatabase database = Room
//                        .databaseBuilder(MainActivity.this, AppDatabase.class, Constants.DATABASE_FILENAME)
//                        .createFromAsset(Constants.DATABASE_FILENAME)
//                        .build();
//                database.getCategoryDao().insert(new Category("ddd"));
//            }
//        });
//        th.start();


        //      this.savedInstanceState = savedInstanceState;

        new ViewModelProvider(this).get(DatabaseViewModel.class).setContext(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new BasketFragment(), BasketFragment.TAG)
                .commit();

//
//        int pageId = getSelectedPageId();
//
//        configureTopAppBar();
//        configureBottomNavBar(pageId);
    }

//    private int getSelectedPageId() {
//        int pageId = savedInstanceState == null
//                ? Constants.DEFAULT_PAGE_ID
//                : savedInstanceState.getInt(Constants.ARG_PAGE_ID);
//
//        Log.i(LOG_TAG, "getSelectedPageId: " + msg(pageId));
//
//        return pageId;
//    }
//
//    private String msg(int pageId) {
//        if (pageId == R.id.shop_page)
//            return "shop_page";
//        if (pageId == R.id.basket_page)
//            return "basket_page";
//        if (pageId == R.id.purse_page)
//            return "purse_page";
//        return "";
//    }
//
//    private boolean navigateSwitch(int pageId) {
//        switch (pageId) {
//            case R.id.shop_page:
//                return navigateTo(new ShopFragment());
//            case R.id.basket_page:
//                return navigateTo(new BasketFragment());
//            case R.id.purse_page:
//                return true;
//        }
//        return false;
//    }
//
//    private <T extends Fragment> boolean navigateTo(String neededFragmentTag) {
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        if (!activeFragmentTag.equals(neededFragmentTag)) {
//            Fragment activeFragment = fragmentManager.findFragmentByTag(activeFragmentTag);
//            if (activeFragment != null) {
//                fragmentTransaction.hide(activeFragment);
//            }
//            Fragment neededFragment = fragmentManager.findFragmentByTag(neededFragmentTag);
//            if (neededFragment != null) {
//                fragmentTransaction.show(neededFragment);
//            } else {
//
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .add(R.id.container, fragment, neededFragmentTag);
//            }
//        }
//
//
//        return true;
//    }
//
//    private void configureTopAppBar() {
//        topAppBar = findViewById(R.id.topAppBar);
//        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//    }
//
//    private void configureBottomNavBar(int pageId) {
//        bottomNavBar = findViewById(R.id.bottomNavBar);
//        bottomNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                return navigateSwitch(item.getItemId());
//            }
//        });
//        bottomNavBar.setSelectedItemId(pageId);
//
//    }
//
//    private Fragment createFragmentByTag(String tag) {
//        String shopFragmentTag = ShopFragment.class.getSimpleName();
//
//
//        switch (tag) {
//            case shopFragmentTag:
//                break;
//        }
//    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.ARG_PAGE_ID, bottomNavBar.getSelectedItemId());
        Log.i(LOG_TAG, "onSaveInstanceState: ");
    }
}
