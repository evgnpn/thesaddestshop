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
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.UUID;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
import by.step.thoughts.adapter.ShopExpandableListAdapter;
import by.step.thoughts.entity.Category;
import by.step.thoughts.entity.CategoryWithProducts;
import by.step.thoughts.entity.Product;
import by.step.thoughts.interfaces.OnAsyncTaskAction;
import by.step.thoughts.interfaces.OnChildClickListener;
import by.step.thoughts.task.CategoryWithProductsAsyncTask;
import by.step.thoughts.viewmodel.DatabaseViewModel;

public class ShopFragment extends Fragment {

    public static final String TAG = UUID.randomUUID().toString();

    private ShopExpandableListAdapter adapter;
    private DatabaseViewModel databaseViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Constants.LOG_TAG, "ShopFragment: onCreate");
        //   setRetainInstance(true);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shop_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = requireActivity();
        databaseViewModel = new ViewModelProvider(activity).get(DatabaseViewModel.class);
        final Context context = getContext();
        final ProgressBar progressBar = activity.findViewById(R.id.progressBar);

        if (context != null) {

            new CategoryWithProductsAsyncTask(new OnAsyncTaskAction<List<CategoryWithProducts>>() {
                @Override
                public void onStart() {
                    toggleProgressBar(progressBar);
                }

                @Override
                public void onFinish(List<CategoryWithProducts> result) {
                    toggleProgressBar(progressBar);
                    createAdapter(context, result);
                    setAdapter();
                }

                @Override
                public void onProgress(Object... objects) {

                }
            }).execute(databaseViewModel.getDatabase().getValue());
        }

    }

    private void createAdapter(Context context, List<CategoryWithProducts> categoryWithProductsList) {
        adapter = new ShopExpandableListAdapter(context, R.layout.category_item, R.layout.product_item, categoryWithProductsList);
        adapter.setOnChildClickAction(new OnChildClickListener() {
            @Override
            public void accept(Category category, Product product) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, ProductFullViewFragment.newInstance(product))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void setAdapter() {
        View view = getView();
        if (view != null) {
            ExpandableListView productsElv = view.findViewById(R.id.productsElv);
            productsElv.setAdapter(adapter);
        }
    }

    private void toggleProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(progressBar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(Constants.LOG_TAG, "ShopFragment: onDestroy");
    }
}
