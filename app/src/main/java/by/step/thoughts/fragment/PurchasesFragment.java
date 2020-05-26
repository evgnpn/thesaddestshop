package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.UUID;

import by.step.thoughts.R;
import by.step.thoughts.adapter.PurchasesExpandableListAdapter;
import by.step.thoughts.entity.relation.purchase.PurchaseWithItemsAndProduct;
import by.step.thoughts.viewmodel.DataViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class PurchasesFragment extends Fragment {

    public static final String TAG = UUID.randomUUID().toString();

    private Context context;
    private FragmentActivity activity;
    private View view;

    private ExpandableListView purchasesElv;

    private PurchasesExpandableListAdapter adapter;
    private DataViewModel dataViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName()
                + "] onCreate (savedInstance: " + (savedInstanceState != null) + ")");

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName()
                + "] onCreateView (savedInstance: " + (savedInstanceState != null) + ")");

        return inflater.inflate(R.layout.fragment_purchases, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName()
                + "] onActivityCreated (savedInstance: " + (savedInstanceState != null) + ")");

        initVars();
        loadData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName()
                + "] onHiddenChanged (hidden: " + hidden + ")");

        Fragment detailsFragment = getChildFragmentManager()
                .findFragmentByTag(ProductDetailsFragment.TAG);
        if (detailsFragment != null)
            detailsFragment.onHiddenChanged(hidden);
    }

    private void loadData() {

        dataViewModel.setLoadingStatus(true);
        dataViewModel.getPurchaseRepository().purchaseWithItemsAndProducts()
                .observe(activity, items -> {
                    createAdapter(context, items);
                    purchasesElv.setAdapter(adapter);
                    dataViewModel.setLoadingStatus(false);
                });
    }

    private void initVars() {
        context = requireContext();
        activity = requireActivity();
        view = requireView();
        purchasesElv = view.findViewById(R.id.purchasesElv);
        dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
    }

    private void createAdapter(Context context, List<PurchaseWithItemsAndProduct> items) {
        adapter = new PurchasesExpandableListAdapter(context, R.layout.group_item,
                R.layout.product_item_with_quantity, items);
        adapter.setOnChildClickAction((purchase, purchaseItem, product) ->
                getChildFragmentManager().beginTransaction()
                        .add(R.id.container, ProductDetailsFragment.newInstance(product),
                                ProductDetailsFragment.TAG)
                        .commit());
    }
}
