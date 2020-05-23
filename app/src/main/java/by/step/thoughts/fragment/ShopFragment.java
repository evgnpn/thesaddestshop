package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

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
import by.step.thoughts.viewmodel.DataViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class ShopFragment extends Fragment {

    public static final String TAG = UUID.randomUUID().toString();

    private Context context;
    private FragmentActivity activity;
    private View view;


    private ShopExpandableListAdapter adapter;
    private ExpandableListView productsElv;

    private DataViewModel dataViewModel;

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
        dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
        productsElv = view.findViewById(R.id.productsElv);
    }

    private void loadData() {
        dataViewModel.setLoadingStatus(true);
        dataViewModel.getCategoryRepository().getCategoryWithProducts().observe(activity, categoriesWithProducts -> {
            createAdapter(context, categoriesWithProducts);
            productsElv.setAdapter(adapter);
            dataViewModel.setLoadingStatus(false);
        });
    }

    private void createAdapter(Context context, List<CategoryWithProducts> categoryWithProductsList) {
        adapter = new ShopExpandableListAdapter(context, R.layout.category_item, R.layout.product_item, categoryWithProductsList);
        adapter.setOnChildClickAction((category, product) -> {
            FragmentManager manager = getParentFragmentManager();

            manager.beginTransaction()
                    .add(R.id.container, ProductDetailsFragment.newInstance(product), ProductDetailsFragment.TAG)
                    .commit();
        });
    }

}
