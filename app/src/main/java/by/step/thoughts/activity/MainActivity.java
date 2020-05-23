package by.step.thoughts.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import by.step.thoughts.R;
import by.step.thoughts.data.AppDatabase;
import by.step.thoughts.data.repository.BasketItemRepository;
import by.step.thoughts.data.repository.CategoryRepository;
import by.step.thoughts.data.repository.ProductRepository;
import by.step.thoughts.data.repository.PurchaseRepository;
import by.step.thoughts.data.repository.PurseRepository;
import by.step.thoughts.fragment.BasketFragment;
import by.step.thoughts.fragment.ProductDetailsFragment;
import by.step.thoughts.fragment.PurseFragment;
import by.step.thoughts.fragment.ShopFragment;
import by.step.thoughts.viewmodel.DataViewModel;
import by.step.thoughts.viewmodel.DataViewModelFactory;

public class MainActivity extends AppCompatActivity {

    public static String activeFragmentTag = ShopFragment.TAG;

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
        configureBottomNavBar();
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
        });

        initFragments();
    }

    private void initFragments() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment shopFragment = new ShopFragment();
        Fragment basketFragment = new BasketFragment();
        Fragment purseFragment = new PurseFragment();

        fragmentTransaction
                .add(R.id.container, shopFragment, ShopFragment.TAG).show(shopFragment)
                .add(R.id.container, basketFragment, BasketFragment.TAG).hide(basketFragment)
                .add(R.id.container, purseFragment, PurseFragment.TAG).hide(purseFragment);

        fragmentTransaction.commit();
    }

    private void switchFragment(String neededFragmentTag) {

        if (activeFragmentTag == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment activeFragment = fragmentManager.findFragmentByTag(activeFragmentTag);
        if (activeFragment != null)
            fragmentTransaction.hide(activeFragment);

        Fragment neededFragment = fragmentManager.findFragmentByTag(neededFragmentTag);
        if (neededFragment != null)
            fragmentTransaction.show(neededFragment);

        fragmentTransaction.commit();

        MainActivity.activeFragmentTag = neededFragmentTag;
    }

    private void configureTopAppBar() {
        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void configureBottomNavBar() {

        bottomNavBar = findViewById(R.id.bottomNavBar);
        bottomNavBar.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.shop_page:
                    setActiveFragment(ShopFragment.TAG);
                    return true;
                case R.id.basket_page:
                    setActiveFragment(BasketFragment.TAG);
                    return true;
                case R.id.purse_page:
                    setActiveFragment(PurseFragment.TAG);
                    return true;
                default:
                    return false;
            }

        });
        bottomNavBar.setSelectedItemId(getPageIdByFragmentTag(activeFragmentTag));

        BadgeDrawable badge = bottomNavBar.getOrCreateBadge(R.id.basket_page);
        badge.setVisible(true);
        badge.setNumber(88);

    }

    private void setActiveFragment(String fragmentTag) {
        switchFragment(fragmentTag);

    }

    private int getPageIdByFragmentTag(final String tag) {
        if (ShopFragment.TAG.equals(tag))
            return R.id.shop_page;
        if (BasketFragment.TAG.equals(tag))
            return R.id.basket_page;
        if (PurseFragment.TAG.equals(tag))
            return R.id.purse_page;
        return -1;
    }
}
