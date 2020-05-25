package by.step.thoughts.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import by.step.thoughts.R;
import by.step.thoughts.data.AppDatabase;
import by.step.thoughts.data.repository.BasketItemRepository;
import by.step.thoughts.data.repository.CategoryRepository;
import by.step.thoughts.data.repository.ProductRepository;
import by.step.thoughts.data.repository.PurchaseRepository;
import by.step.thoughts.data.repository.PurseRepository;
import by.step.thoughts.entity.relation.BasketItemAndProduct;
import by.step.thoughts.fragment.BasketFragment;
import by.step.thoughts.fragment.PurchasesFragment;
import by.step.thoughts.fragment.PurseFragment;
import by.step.thoughts.fragment.ShopFragment;
import by.step.thoughts.viewmodel.DataViewModel;
import by.step.thoughts.viewmodel.DataViewModelFactory;

import static by.step.thoughts.Constants.LOG_TAG;

public class MainActivity extends AppCompatActivity {

    public static String activeFragmentTag = ShopFragment.TAG;

    private ProgressBar progressBar;
    private MaterialToolbar topAppBar;
    private BottomNavigationView bottomNavBar;

    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onCreate (savedInstance: " + (savedInstanceState != null) + ")");

        initVars();

        if (savedInstanceState == null) {
            initFragments();
            navigate();
        }

        initViewModels();
        setObservers();
        configureBottomNavBar();
    }

    private void initVars() {
        topAppBar = findViewById(R.id.topAppBar);
        bottomNavBar = findViewById(R.id.bottomNavBar);
        progressBar = findViewById(R.id.progressBar);
    }

    private void initViewModels() {

        AppDatabase database = AppDatabase.getDatabase(this);

        dataViewModel = new ViewModelProvider(this,
                new DataViewModelFactory(
                        new ProductRepository(database.getProductDao()),
                        new BasketItemRepository(database.getBasketItemDao()),
                        new CategoryRepository(database.getCategoryDao()),
                        new PurchaseRepository(database.getPurchaseDao()),
                        new PurseRepository(database.getPurseDaoDao())))
                .get(DataViewModel.class);
    }

    private void setObservers() {

        dataViewModel.getLoadStatus().observe(this,
                isLoading -> progressBar.setVisibility(isLoading ? View.VISIBLE : View.INVISIBLE));

        dataViewModel.getBasketItemRepository().getBasketItemAndProducts().observe(this, items -> {
            int amountSum = 0;
            for (BasketItemAndProduct item : items)
                amountSum += item.basketItem.amount;
            bottomNavBar.getOrCreateBadge(R.id.basket_page).setNumber(amountSum);
        });

        dataViewModel.getPurchaseRepository().getAll().observe(this, items -> {
            bottomNavBar.getOrCreateBadge(R.id.purchases_page).setNumber(items.size());
        });
    }

    private void initFragments() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment shopFragment = new ShopFragment();
        Fragment basketFragment = new BasketFragment();
        Fragment purchasesFragment = new PurchasesFragment();
        Fragment purseFragment = new PurseFragment();

        fragmentTransaction
                .add(R.id.container, shopFragment, ShopFragment.TAG).show(shopFragment)
                .add(R.id.container, basketFragment, BasketFragment.TAG).hide(basketFragment)
                .add(R.id.container, purchasesFragment, PurchasesFragment.TAG).hide(purchasesFragment)
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

    private void configureBottomNavBar() {

        bottomNavBar.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.shop_page:
                    switchFragment(ShopFragment.TAG);
                    return true;
                case R.id.basket_page:
                    switchFragment(BasketFragment.TAG);
                    return true;
                case R.id.purchases_page:
                    switchFragment(PurchasesFragment.TAG);
                    return true;
                case R.id.purse_page:
                    switchFragment(PurseFragment.TAG);
                    return true;
                default:
                    return false;
            }
        });
    }

    private void navigate() {
        bottomNavBar.setSelectedItemId(getPageIdByFragmentTag(activeFragmentTag));
    }

    private int getPageIdByFragmentTag(final String tag) {
        if (ShopFragment.TAG.equals(tag))
            return R.id.shop_page;
        if (BasketFragment.TAG.equals(tag))
            return R.id.basket_page;
        if (PurchasesFragment.TAG.equals(tag))
            return R.id.purchases_page;
        if (PurseFragment.TAG.equals(tag))
            return R.id.purse_page;
        return -1;
    }
}
