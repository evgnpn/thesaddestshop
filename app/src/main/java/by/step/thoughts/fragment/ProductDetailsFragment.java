package by.step.thoughts.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.UUID;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
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
    private Button addToBasketButton;

    private DataViewModel dataViewModel;

    private Product product;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(LOG_TAG, "ProductDetailsFragment onHiddenChanged: " + hidden);
        addToBasketButton.setVisibility(hidden ? View.INVISIBLE : View.VISIBLE);

        if (hidden)
            topAppBar.setNavigationIcon(null);
        else
            topAppBar.setNavigationIcon(R.drawable.ic_back_arrow);



    }


    static ProductDetailsFragment newInstance(Product product) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();

        TextView productTitleTv = view.findViewById(R.id.productTitleTv);
        TextView productDescriptionTv = view.findViewById(R.id.productDescriptionTv);
        TextView productPriceTv = view.findViewById(R.id.productPriceTv);


        if (product != null) {
            productTitleTv.setText(product.title);
            productDescriptionTv.setText(product.description);
            productPriceTv.setText(String.format("%s%s", product.price, Constants.CURRENCY));
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        addToBasketButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        addToBasketButton.setVisibility(View.INVISIBLE);
    }

    private void init() {
        context = requireContext();
        activity = requireActivity();
        view = requireView();
        product = requireArguments().getParcelable(ARG_PRODUCT);
        dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
        addToBasketButton = activity.findViewById(R.id.addToBasketBtn);
        addToBasketButton.setOnClickListener(v -> {


            BasketItem basketItem = new BasketItem();
            basketItem.productId = product.id;
            //   basketItem.amount =


        });

        topAppBar = activity.findViewById(R.id.topAppBar);
        topAppBar.setNavigationIcon(R.drawable.ic_back_arrow);

        topAppBar.setNavigationOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().remove(ProductDetailsFragment.this).commit();
            topAppBar.setNavigationIcon(null);
        });
    }
}
