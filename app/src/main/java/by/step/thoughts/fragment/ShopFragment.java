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

    private ShopExpandableListAdapter adapter;
    private ExpandableListView productsElv;

    private DataViewModel dataViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onCreate (savedInstance: " + (savedInstanceState != null) + ")");

        setRetainInstance(true);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        Fragment detailsFragment = getChildFragmentManager().findFragmentByTag(ProductDetailsFragment.TAG);
        if (detailsFragment != null)
            detailsFragment.onHiddenChanged(hidden);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shop_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onActivityCreated (savedInstance: " + (savedInstanceState != null) + ")");

        initVars();
        loadData();
    }

    private void initVars() {
        context = requireContext();
        activity = requireActivity();
        View view = requireView();
        productsElv = view.findViewById(R.id.productsElv);
        dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
    }

    private void loadData() {
        dataViewModel.setLoadingStatus(true);
        dataViewModel.getCategoryRepository().getCategoryWithProducts().observe(activity, items -> {
            createAdapter(context, items);
            productsElv.setAdapter(adapter);
            dataViewModel.setLoadingStatus(false);
        });
    }

    private void createAdapter(Context context, List<CategoryWithProducts> items) {
        adapter = new ShopExpandableListAdapter(context, R.layout.group_item, R.layout.product_item, items);
        adapter.setOnChildClickAction((category, product) -> getChildFragmentManager().beginTransaction()
                .add(R.id.container, ProductDetailsFragment.newInstance(product), ProductDetailsFragment.TAG)
                .commit());
    }
}
