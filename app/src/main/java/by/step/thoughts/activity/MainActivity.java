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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import by.step.thoughts.Constants;
import by.step.thoughts.R;

import by.step.thoughts.data.repository.BasketItemRepository;
import by.step.thoughts.data.repository.ProductRepository;
import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.Product;
import by.step.thoughts.fragment.BasketFragment;
import by.step.thoughts.fragment.ProductDetailsFragment;
import by.step.thoughts.fragment.ShopFragment;
import by.step.thoughts.viewmodel.DatabaseViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class MainActivity extends AppCompatActivity {

    public static int activePageId = Constants.DEFAULT_PAGE_ID;

    private MaterialToolbar topAppBar;
    private BottomNavigationView bottomNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            initFragments();
        }

        Button dbgBtn = findViewById(R.id.debugBtn);
        dbgBtn.setOnClickListener(v -> {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            Toast.makeText(MainActivity.this, String.valueOf(fragments.size()), Toast.LENGTH_SHORT).show();
        });


        DatabaseViewModel databaseViewModel = new ViewModelProvider(this).get(DatabaseViewModel.class);
        databaseViewModel.setContext(this);

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

    private void switchFragmentNew(String neededFragmentTag) {

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
        //  topAppBar.navigation
        topAppBar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void configureBottomNavBar(int pageId) {

        bottomNavBar = findViewById(R.id.bottomNavBar);
        bottomNavBar.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.shop_page:
                    setActiveFragment(ShopFragment.TAG, R.id.shop_page);
                    return true;
                case R.id.basket_page:
                    setActiveFragment(BasketFragment.TAG, R.id.basket_page);
                    return true;
                default:
                    return false;
            }

        });
        bottomNavBar.setSelectedItemId(pageId);
    }

    private void setActiveFragment(String fragmentTag, int pageId) {
        switchFragment(fragmentTag);
        activePageId = pageId;

        if (getSupportFragmentManager().findFragmentByTag(ProductDetailsFragment.TAG) != null)
            topAppBar.setNavigationIcon(null);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(LOG_TAG, "onSaveInstanceState");
    }
}
