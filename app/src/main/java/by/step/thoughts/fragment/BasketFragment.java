package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.UUID;

import by.step.thoughts.R;
import by.step.thoughts.adapter.BasketListAdapter;
import by.step.thoughts.data.repository.BasketItemRepository;
import by.step.thoughts.data.repository.PurchaseRepository;
import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.relation.BasketItemAndProduct;
import by.step.thoughts.viewmodel.DataViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class BasketFragment extends Fragment {

    public static final String TAG = UUID.randomUUID().toString();

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
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onPause");

        // controlsEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onResume");

        // controlsEnabled(true);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        controlsEnabled(!hidden);
    }

    private void controlsEnabled(boolean enabled) {

        if (enabled) {
            buyBtn = (MaterialButton) getLayoutInflater().inflate(R.layout.buy_button, topAppBar, false);
            buyBtn.setOnClickListener(v -> {

                transferItems();

                buyBtn.setEnabled(false);

            });
            topAppBar.addView(buyBtn);
            buyBtn.setEnabled(adapter.getCount() > 0);
        } else {
            topAppBar.removeView(buyBtn);
        }
    }

    private void transferItems() {

        dataViewModel.setLoadingStatus(true);

        BasketItemRepository basketItemRepository = dataViewModel.getBasketItemRepository();
        PurchaseRepository purchaseRepository = dataViewModel.getPurchaseRepository();

        LiveData<List<BasketItem>> basketItemsLiveData = basketItemRepository.getAll();

        basketItemsLiveData.observe(getViewLifecycleOwner(), new Observer<List<BasketItem>>() {
            @Override
            public void onChanged(List<BasketItem> items) {

                if (items != null) {
                    for (BasketItem basketItem : items) {

                        // purchaseRepository.getPurchaseAndProductByProductId()

                        //  purchaseRepository.insert(new Purchase[]{new Purchase(basketItem.amount, basketItem.productId)});

                    }

                    // basketItemRepository.delete(items);
                }

                basketItemsLiveData.removeObserver(this);
                dataViewModel.setLoadingStatus(false);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onCreate (savedInstance: " + (savedInstanceState != null) + ")");

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.basket_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onActivityCreated (savedInstance: " + (savedInstanceState != null) + ")");

        initVars();
        loadData();

        controlsEnabled(!isHidden());
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
        adapter.setOnCloseClickListener((basketItemAndProduct, closeButton) -> new MaterialAlertDialogBuilder(context)
                .setTitle("Подтверждение")
                .setMessage("Удалить продукт '" + basketItemAndProduct.product.title + "' из корзины?")
                .setPositiveButton("Удалить", (dialog, which) -> {
                    dataViewModel.setLoadingStatus(true);
                    lastDeletedString = basketItemAndProduct.product.title;
                    dataViewModel.getBasketItemRepository().delete(new BasketItem[]{basketItemAndProduct.basketItem});
                })
                .setNegativeButton("Отмена", null)
                .create().show());
    }

    private void showDeletedMessage(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAnchorView(R.id.bottomNavBar).show();
        lastDeletedString = null;
    }
}
