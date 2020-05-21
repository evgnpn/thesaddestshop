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

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import by.step.thoughts.R;
import by.step.thoughts.fragment.BasketFragment;
import by.step.thoughts.fragment.ShopFragment;
import by.step.thoughts.viewmodel.DatabaseViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class MainActivity extends AppCompatActivity {

    static int activePageId = R.id.shop_page;

    MaterialToolbar topAppBar;
    BottomNavigationView bottomNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            initFragments();
        }

//        if (savedInstanceState != null) {
//            return;
//        }

//        Button dbgBtn = findViewById(R.id.debugBtn);
//        dbgBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<Fragment> fragments = getSupportFragmentManager().getFragments();
//                Toast.makeText(MainActivity.this, String.valueOf(fragments.size()), Toast.LENGTH_SHORT).show();
//            }
//        });

        new ViewModelProvider(this).get(DatabaseViewModel.class).setContext(this);

        configureTopAppBar();
        configureBottomNavBar(activePageId);
    }

    private String getFragmentTagByPageId(int pageId) {
        switch (pageId) {
            case R.id.shop_page:
                return ShopFragment.TAG;
            case R.id.basket_page:
                return BasketFragment.TAG;
            case R.id.purse_page:
                return "";
            default:
                return null;
        }
    }

    private void initFragments() {
        Log.i(LOG_TAG, "initFragments");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment shopFragment = new ShopFragment();
        Fragment basketFragment = new BasketFragment();

        fragmentTransaction
                .add(R.id.container, shopFragment, ShopFragment.TAG).show(shopFragment)
                .add(R.id.container, basketFragment, BasketFragment.TAG).hide(basketFragment);

        fragmentTransaction.commit();
    }

    private void switchFragment(String neededFragmentTag) {

        String activeFragmentTag = getFragmentTagByPageId(activePageId);

        if (activeFragmentTag == null) {
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment activeFragment = fragmentManager.findFragmentByTag(activeFragmentTag);
        if (activeFragment != null)
            fragmentTransaction.hide(activeFragment);

        Fragment neededFragment = fragmentManager.findFragmentByTag(neededFragmentTag);
        if (neededFragment != null)
            fragmentTransaction.show(neededFragment);

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

    private void configureBottomNavBar(int pageId) {
        Log.i(LOG_TAG, "configureBottomNavBar");

        bottomNavBar = findViewById(R.id.bottomNavBar);
        bottomNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.shop_page:
                        switchFragment(ShopFragment.TAG);
                        activePageId = R.id.shop_page;
                        return true;
                    case R.id.basket_page:
                        switchFragment(BasketFragment.TAG);
                        activePageId = R.id.basket_page;
                        return true;
                    default:
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
