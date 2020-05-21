package by.step.thoughts.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
import by.step.thoughts.entity.Product;
import by.step.thoughts.viewmodel.DatabaseViewModel;

public class ProductFullViewFragment extends Fragment {

    private static final String ARG_PRODUCT = "product";
    private DatabaseViewModel databaseViewModel;

    public ProductFullViewFragment() {
    }

    public static ProductFullViewFragment newInstance(Product product) {
        ProductFullViewFragment fragment = new ProductFullViewFragment();
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
        return inflater.inflate(R.layout.fragment_product_full_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        databaseViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);

        Bundle args = getArguments();

        if (args != null) {


            View view = getView();

            if (view != null) {
                TextView productTitleTv = view.findViewById(R.id.productTitleTv);
                TextView productDescriptionTv = view.findViewById(R.id.productDescriptionTv);
                TextView productPriceTv = view.findViewById(R.id.productPriceTv);

                Product product = args.getParcelable(ARG_PRODUCT);

                if (product != null) {
                    productTitleTv.setText(product.title);
                    productDescriptionTv.setText(product.description);
                    productPriceTv.setText(String.format("%s%s", product.price, Constants.CURRENCY));
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Button btn = getActivity().findViewById(R.id.addToBasketBtn);
        btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        Button btn = getActivity().findViewById(R.id.addToBasketBtn);
        btn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(Constants.LOG_TAG, "ProductFullViewFragment: onDestroy");

    }
}
