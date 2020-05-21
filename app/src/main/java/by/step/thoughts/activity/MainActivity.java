package by.step.thoughts.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
import by.step.thoughts.fragment.BasketFragment;
import by.step.thoughts.fragment.ShopFragment;
import by.step.thoughts.viewmodel.DatabaseViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class MainActivity extends AppCompatActivity {

    static String activeFragmentTag = ShopFragment.TAG;
    static int activePageId = R.id.shop_page;

    MaterialToolbar topAppBar;
    BottomNavigationView bottomNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "MainActivity: onCreate (activeFragmentTag: " + activeFragmentTag + ")");


        if (savedInstanceState != null) {
            return;
        }
        Button dbgBtn = findViewById(R.id.debugBtn);
        dbgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                Toast.makeText(MainActivity.this, String.valueOf(fragments.size()), Toast.LENGTH_SHORT).show();
            }
        });





        new ViewModelProvider(this).get(DatabaseViewModel.class).setContext(this);


        configureTopAppBar();
        configureBottomNavBar(activePageId);
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
    private void switchFragment(String neededFragmentTag) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment activeFragment = fragmentManager.findFragmentByTag(activeFragmentTag);

        if (activeFragment != null && !activeFragmentTag.equals(neededFragmentTag)) {
            fragmentTransaction.hide(activeFragment);
        }

        Fragment neededFragment = fragmentManager.findFragmentByTag(neededFragmentTag);

        if (neededFragment == null) {

            neededFragment = by.step.thoughts.FragmentFactory.createFromTag(neededFragmentTag);

            if (neededFragment != null) {
                fragmentTransaction.add(R.id.container, neededFragment, neededFragmentTag);
            }

        } else if (neededFragment.isHidden()) {
            fragmentTransaction.show(neededFragment);
        }

        fragmentTransaction.commit();
    }

    private void configureTopAppBar() {
        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initPage() {

    }

    private void configureBottomNavBar(int pageId) {
        Log.i(LOG_TAG, "configureBottomNavBar");

        bottomNavBar = findViewById(R.id.bottomNavBar);
        bottomNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.shop_page:
                        switchFragment(ShopFragment.TAG);
                        activeFragmentTag = ShopFragment.TAG;
                        activePageId = R.id.shop_page;
                        Log.i(LOG_TAG, "onNavigationItemSelected: shop_page switch");
                        return true;
                    case R.id.basket_page:
                        switchFragment(BasketFragment.TAG);
                        activeFragmentTag = BasketFragment.TAG;
                        activePageId = R.id.basket_page;
                        Log.i(LOG_TAG, "onNavigationItemSelected: basket_page switch");
                        return true;
                    default:
                        Log.i(LOG_TAG, "onNavigationItemSelected: default switch");
                        return false;
                }
            }
        });
        bottomNavBar.setSelectedItemId(pageId);

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(LOG_TAG, "onSaveInstanceState");
    }
}
