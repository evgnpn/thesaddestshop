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

import java.util.List;
import java.util.UUID;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
import by.step.thoughts.adapter.BasketListAdapter;
import by.step.thoughts.data.repository.BasketItemRepository;
import by.step.thoughts.entity.relation.BasketItemAndProduct;
import by.step.thoughts.viewmodel.DatabaseViewModel;

public class BasketFragment extends Fragment {

    public static final String TAG = UUID.randomUUID().toString();

    private Context context;
    private FragmentActivity activity;
    private View view;

    private ProgressBar progressBar;
    private ListView basketLv;

    private BasketListAdapter adapter;
    private DatabaseViewModel databaseViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Constants.LOG_TAG, "BasketFragment: onCreate");
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
        databaseViewModel = new ViewModelProvider(activity).get(DatabaseViewModel.class);
        progressBar = activity.findViewById(R.id.progressBar);
        basketLv = view.findViewById(R.id.basketLv);
    }

    private void createAdapter(final Context context, List<BasketItemAndProduct> basketItemAndProducts) {
        adapter = new BasketListAdapter(context, R.layout.basket_item, basketItemAndProducts);
        adapter.setOnCloseClickListener((basketItemAndProduct, closeButton) -> new MaterialAlertDialogBuilder(context)
                .setTitle("Подтверждение")
                .setMessage("Удалить продукт '" + basketItemAndProduct.product.title + "' из корзины?")
                .setPositiveButton("Удалить", (dialog, which) -> {
                    toggleProgressBar(progressBar);
                    BasketItemRepository rep = new BasketItemRepository(databaseViewModel.getDatabaseValue().getBasketItemDao());
                    rep.delete(basketItemAndProduct.basketItem);
                })
                .setNegativeButton("Отмена", null)
                .create().show());
    }

    private void loadData() {
        toggleProgressBar(progressBar);
        databaseViewModel.getDatabaseValue()
                .getBasketItemDao()
                .getBasketItemAndProducts()
                .observe(activity, basketProducts -> {
                    toggleProgressBar(progressBar);
                    createAdapter(context, basketProducts);
                    basketLv.setAdapter(adapter);
                });
    }

    private void toggleProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(progressBar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }
}
