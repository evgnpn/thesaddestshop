package by.step.thoughts.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
import by.step.thoughts.data.AppDatabase;
import by.step.thoughts.data.repository.BasketItemRepository;
import by.step.thoughts.data.repository.CategoryRepository;
import by.step.thoughts.data.repository.ProductRepository;
import by.step.thoughts.data.repository.PurchaseRepository;
import by.step.thoughts.data.repository.PurseRepository;
import by.step.thoughts.fragment.BasketFragment;
import by.step.thoughts.fragment.ProductDetailsFragment;
import by.step.thoughts.fragment.ShopFragment;
import by.step.thoughts.viewmodel.DataViewModel;
import by.step.thoughts.viewmodel.DataViewModelFactory;

import static by.step.thoughts.Constants.LOG_TAG;

public class MainActivity extends AppCompatActivity {

    public static int activePageId = Constants.DEFAULT_PAGE_ID;

    private ProgressBar progressBar;
    private MaterialToolbar topAppBar;
    private BottomNavigationView bottomNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            init();
        }

        Button dbgBtn = findViewById(R.id.debugBtn);
        dbgBtn.setOnClickListener(v -> {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            Toast.makeText(MainActivity.this, String.valueOf(fragments.size()), Toast.LENGTH_SHORT).show();
        });


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

    private void init() {

        progressBar = findViewById(R.id.progressBar);

        AppDatabase database = AppDatabase.getDatabase(this);
        DataViewModel dataViewModel = new ViewModelProvider(this,
                new DataViewModelFactory(
                        new ProductRepository(database.getProductDao()),
                        new BasketItemRepository(database.getBasketItemDao()),
                        new CategoryRepository(database.getCategoryDao()),
                        new PurchaseRepository(database.getPurchaseDao()),
                        new PurseRepository(database.getPurseDaoDao())))
                .get(DataViewModel.class);

        dataViewModel.getLoadStatus().observe(this, isLoading -> {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.INVISIBLE);
            Toast.makeText(this, "loading: " + isLoading, Toast.LENGTH_SHORT).show();
        });

        initFragments();
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

    private void configureTopAppBar() {
        topAppBar = findViewById(R.id.topAppBar);
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

        BadgeDrawable badge = bottomNavBar.getOrCreateBadge(R.id.basket_page);
        badge.setVisible(true);
        badge.setNumber(88);

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
