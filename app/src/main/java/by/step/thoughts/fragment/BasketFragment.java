package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.UUID;

import by.step.thoughts.R;
import by.step.thoughts.adapter.BasketListAdapter;
import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.relation.BasketItemAndProduct;
import by.step.thoughts.viewmodel.DataViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class BasketFragment extends Fragment {

    public static final String TAG = UUID.randomUUID().toString();

    private Context context;
    private FragmentActivity activity;
    private View view;

    private ProgressBar progressBar;
    private ListView basketLv;

    private BasketListAdapter adapter;
    private DataViewModel dataViewModel;

    private String lastDeletedString = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "BasketFragment: onCreate");
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

        init();
        loadData();
    }

    private void init() {
        context = requireContext();
        activity = requireActivity();
        view = requireView();
        dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
        progressBar = activity.findViewById(R.id.progressBar);
        basketLv = view.findViewById(R.id.basketLv);
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

    private void createAdapter(final Context context, List<BasketItemAndProduct> basketItemAndProducts) {
        adapter = new BasketListAdapter(context, R.layout.basket_item, basketItemAndProducts);
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
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction("X", v -> {
        }).show();
        lastDeletedString = null;
    }
}
