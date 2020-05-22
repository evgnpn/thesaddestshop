package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.UUID;

import by.step.thoughts.R;
import by.step.thoughts.adapter.ShopExpandableListAdapter;
import by.step.thoughts.entity.relation.CategoryWithProducts;
import by.step.thoughts.viewmodel.DatabaseViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class ShopFragment extends Fragment {

    public static final String TAG = UUID.randomUUID().toString();

    private Context context;
    private FragmentActivity activity;
    private View view;

    private DatabaseViewModel databaseViewModel;
    private ShopExpandableListAdapter adapter;
    private ProgressBar progressBar;
    private ExpandableListView productsElv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "ShopFragment: onCreate");
        //setRetainInstance(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shop_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "ShopFragment: onActivityCreated (has savedInstance: " + (savedInstanceState != null) + ")");

        init();
        loadData();
    }

    private void init() {
        context = requireContext();
        activity = requireActivity();
        view = requireView();
        databaseViewModel = new ViewModelProvider(activity).get(DatabaseViewModel.class);
        progressBar = activity.findViewById(R.id.progressBar);
        productsElv = view.findViewById(R.id.productsElv);
    }

    private void loadData() {
        toggleProgressBar(progressBar);
        databaseViewModel.getDatabaseValue()
                .getCategoryDao()
                .getCategoryWithProducts()
                .observe(activity, categoriesWithProducts -> {
                    createAdapter(context, categoriesWithProducts);
                    productsElv.setAdapter(adapter);
                    toggleProgressBar(progressBar);
                });
    }

    private void createAdapter(Context context, List<CategoryWithProducts> categoryWithProductsList) {
        adapter = new ShopExpandableListAdapter(context, R.layout.category_item, R.layout.product_item, categoryWithProductsList);
        adapter.setOnChildClickAction((category, product) -> {
            FragmentManager manager = getChildFragmentManager();

            manager.beginTransaction()
                    .replace(R.id.container, ProductDetailsFragment.newInstance(product), ProductDetailsFragment.TAG)
                    .commit();
        });
    }

    private void toggleProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(progressBar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }
}
