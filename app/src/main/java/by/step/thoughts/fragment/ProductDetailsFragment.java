package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.UUID;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
import by.step.thoughts.data.repository.BasketItemRepository;
import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.Product;
import by.step.thoughts.viewmodel.DataViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class ProductDetailsFragment extends Fragment {

    public static final String TAG = UUID.randomUUID().toString();
    private static final String ARG_PRODUCT = "product";

    private View view;
    private Context context;
    private FragmentActivity activity;
    private MaterialToolbar topAppBar;
    private MaterialButton addToBasketBtn;

    private DataViewModel dataViewModel;

    private Product product;

    static ProductDetailsFragment newInstance(Product product) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onPause");

        controlsEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onResume");

        controlsEnabled(true);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        controlsEnabled(!hidden);
    }

    private void controlsEnabled(boolean enabled) {

        if (enabled) {
            addToBasketBtn = (MaterialButton) getLayoutInflater().inflate(R.layout.add_to_basket_button, topAppBar, false);
            addToBasketBtn.setOnClickListener(v -> {

                dataViewModel.setLoadingStatus(true);
                BasketItemRepository repository = dataViewModel.getBasketItemRepository();
                LiveData<BasketItem> basketItemLiveData = repository.findByProductId(product.id);

                basketItemLiveData.observe(activity, item -> {

                    if (item == null) {
                        BasketItem basketItem = new BasketItem(product.id, 1);
                        dataViewModel.getBasketItemRepository().insert(new BasketItem[]{basketItem});
                    } else {
                        item.amount++;
                        dataViewModel.getBasketItemRepository().update(new BasketItem[]{item});
                    }

                    basketItemLiveData.removeObservers(activity);

                    Snackbar.make(view, "Добавлено", Snackbar.LENGTH_SHORT)
                            .setAnchorView(R.id.bottomNavBar).show();

                    dataViewModel.setLoadingStatus(false);
                });
            });
            topAppBar.addView(addToBasketBtn);
            topAppBar.setNavigationIcon(R.drawable.ic_back_arrow);
        } else {
            topAppBar.removeView(addToBasketBtn);
            topAppBar.setNavigationIcon(null);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onCreate (savedInstance: " + (savedInstanceState != null) + ")");

        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onCreateView (savedInstance: " + (savedInstanceState != null) + ")");

        return inflater.inflate(R.layout.fragment_details_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onActivityCreated (savedInstance: " + (savedInstanceState != null) + ")");


        initArgs();
        initView();

        setListeners();
    }

    private void initArgs() {

        context = requireContext();
        activity = requireActivity();
        view = requireView();
        product = requireArguments().getParcelable(ARG_PRODUCT);
        dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
        topAppBar = activity.findViewById(R.id.topAppBar);


        //  addToBasketButton = activity.findViewById(R.id.addToBasketBtn);
    }

    private void setListeners() {
        topAppBar.setNavigationOnClickListener(v ->
                getParentFragmentManager().beginTransaction()
                        .remove(ProductDetailsFragment.this)
                        .commit());
    }

    private void initView() {

        TextView productTitleTv = view.findViewById(R.id.productTitleTv);
        TextView productDescriptionTv = view.findViewById(R.id.productDescriptionTv);
        TextView productPriceTv = view.findViewById(R.id.productPriceTv);

        if (product != null) {
            productTitleTv.setText(product.title);
            productDescriptionTv.setText(product.description);
            productPriceTv.setText(String.format("%s%s", product.price, Constants.CURRENCY));
        }
    }
}
