package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.UUID;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
import by.step.thoughts.adapter.BasketListAdapter;
import by.step.thoughts.data.AppDatabase;
import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.relation.BasketItemAndProduct;
import by.step.thoughts.task.PurchaseTask;
import by.step.thoughts.viewmodel.DataViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class BasketFragment extends Fragment {

    public static final String TAG = BasketFragment.class.getSimpleName() + " " + UUID.randomUUID().toString();

    private Context context;
    private FragmentActivity activity;
    private View view;
    private MaterialToolbar topAppBar;
    private MaterialButton buyBtn;

    private ListView basketLv;

    private BasketListAdapter adapter;
    private DataViewModel dataViewModel;

    private String lastDeletedString = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onCreate (savedInstance: " + (savedInstanceState != null) + ")");

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onCreateView (savedInstance: " + (savedInstanceState != null) + ")");
        return inflater.inflate(R.layout.basket_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onActivityCreated (savedInstance: " + (savedInstanceState != null) + ")");

        initVars();
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onResume");

        if (!isHidden())
            configureTopAppBar(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onPause");

        configureTopAppBar(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onDestroy");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onHiddenChanged (hidden: " + hidden + ")");

        configureTopAppBar(!hidden);
    }

    private void initVars() {
        context = requireContext();
        activity = requireActivity();
        view = requireView();
        topAppBar = activity.findViewById(R.id.topAppBar);
        basketLv = view.findViewById(R.id.basketLv);
        dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
    }

    private void loadData() {
        dataViewModel.setLoadingStatus(true);
        dataViewModel.getBasketItemRepository().getBasketItemAndProducts()
                .observe(activity, basketProducts -> {
                    createAdapter(context, basketProducts);
                    basketLv.setAdapter(adapter);
                    if (lastDeletedString != null) {
                        showDeletedMessage("Продукт '" + lastDeletedString + "' удален из корзины");
                    }
                    dataViewModel.setLoadingStatus(false);
                });
    }

    private void createAdapter(final Context context, List<BasketItemAndProduct> items) {
        adapter = new BasketListAdapter(context, R.layout.basket_item, items);
        adapter.setOnCloseClickListener((basketItemAndProduct, closeButton) ->
                showDialog(basketItemAndProduct));
    }

    private void showDeletedMessage(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAnchorView(R.id.bottomNavBar).show();
        lastDeletedString = null;
    }

    private void configureTopAppBar(boolean visible) {

        if (visible) {
            buyBtn = (MaterialButton) getLayoutInflater()
                    .inflate(R.layout.buy_button, topAppBar, false);
            buyBtn.setOnClickListener(v -> transferItems());
            topAppBar.addView(buyBtn);

            buyBtn.setEnabled(adapter.getCount() > 0);

            dataViewModel.getBasketItemRepository().getAll().observe(activity, basketItems -> {

                buyBtn.setEnabled(basketItems != null && basketItems.size() > 0);

            });

        } else {
            topAppBar.removeView(buyBtn);
        }
    }

    private void transferItems() {

        AppDatabase database = AppDatabase.getDatabase(activity);

        PurchaseTask purchaseTask = new PurchaseTask(database);

        purchaseTask.setListener(new PurchaseTask.Listener() {
            @Override
            public void onStart() {
                activity.runOnUiThread(() -> {
                    dataViewModel.setLoadingStatus(true);
                });
            }

            @Override
            public void onLackOfBalance(double balance, double purchaseSum) {
                activity.runOnUiThread(() -> {
                    new MaterialAlertDialogBuilder(activity)
                            .setTitle("Баланс")
                            .setPositiveButton("ОК", (dialog, which) -> {
                                dialog.cancel();
                            }).setMessage("Не хватает денежных средств ("
                            + (purchaseSum - balance) + " " + Constants.CURRENCY + " )")
                            .create().show();
                    dataViewModel.setLoadingStatus(false);
                });
            }

            @Override
            public void onError() {
                activity.runOnUiThread(() -> {
                    dataViewModel.setLoadingStatus(false);
                    Toast.makeText(context, "Что то пошло не так", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onFinish() {

                activity.runOnUiThread(() -> {
                    dataViewModel.setLoadingStatus(false);

                    new MaterialAlertDialogBuilder(activity)
                            .setPositiveButton("ОК", (dialog, which) -> {
                                dialog.cancel();
                            }).setMessage("Покупка успешно совершена!").create().show();

                });
            }
        });

        database.getTransactionExecutor().execute(purchaseTask);
    }

    private void showDialog(BasketItemAndProduct basketItemAndProduct) {
        new MaterialAlertDialogBuilder(context)
                .setTitle("Подтверждение")
                .setMessage("Удалить продукт '" + basketItemAndProduct.product.title + "' из корзины?")
                .setPositiveButton("Удалить", (dialog, which) -> {
                    dataViewModel.setLoadingStatus(true);
                    lastDeletedString = basketItemAndProduct.product.title;
                    dataViewModel.getBasketItemRepository().delete(new BasketItem[]{basketItemAndProduct.basketItem});
                })
                .setNegativeButton("Отмена", null)
                .create().show();
    }
}
