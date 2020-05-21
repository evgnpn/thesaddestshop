package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.UUID;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
import by.step.thoughts.adapter.BasketListAdapter;
import by.step.thoughts.entity.relation.BasketItemAndProduct;
import by.step.thoughts.interfaces.OnAsyncTaskAction;
import by.step.thoughts.task.BasketItemAndProductAsyncTask;
import by.step.thoughts.viewmodel.DatabaseViewModel;

public class BasketFragment extends Fragment {

    public static final String TAG = UUID.randomUUID().toString();

    private BasketListAdapter adapter;
    private DatabaseViewModel databaseViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Constants.LOG_TAG, "BasketFragment: onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.basket_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = requireActivity();
        databaseViewModel = new ViewModelProvider(activity).get(DatabaseViewModel.class);
        final Context context = getContext();
        final ProgressBar progressBar = activity.findViewById(R.id.progressBar);

        if (context != null) {

            new BasketItemAndProductAsyncTask(new OnAsyncTaskAction<List<BasketItemAndProduct>>() {
                @Override
                public void onStart() {
                    toggleProgressBar(progressBar);
                }

                @Override
                public void onFinish(List<BasketItemAndProduct> result) {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(Constants.LOG_TAG, "BasketFragment: onDestroy");
    }

    private void createAdapter(final Context context, List<BasketItemAndProduct> basketItemAndProducts) {
        adapter = new BasketListAdapter(context, R.layout.basket_item, basketItemAndProducts);
        adapter.setOnCloseClickListener(new BasketListAdapter.OnCloseClickListener() {
            @Override
            public void accept(BasketItemAndProduct basketItemAndProduct, Button closeButton) {
                Toast.makeText(context, "Basket OnClose", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter() {
        View view = getView();
        if (view != null) {
            ListView basketLv = view.findViewById(R.id.basketLv);
            basketLv.setAdapter(adapter);
        }
    }

    private void toggleProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(progressBar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }
}
