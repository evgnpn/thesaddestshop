package by.step.thoughts.fragment;

import android.content.Context;
import android.content.DialogInterface;
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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.UUID;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
import by.step.thoughts.adapter.BasketListAdapter;
import by.step.thoughts.entity.relation.BasketItemAndProduct;
import by.step.thoughts.interfaces.OnAsyncTaskAction;
import by.step.thoughts.task.BasketItemAndProductAsyncTask;
import by.step.thoughts.task.DeleteBasketItemAsyncTask;
import by.step.thoughts.viewmodel.DatabaseViewModel;

public class BasketFragment extends Fragment {

    public static final String TAG = UUID.randomUUID().toString();

    private BasketListAdapter adapter;
    private DatabaseViewModel databaseViewModel;

    private FragmentActivity activity;
    private View view;

    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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

        activity = requireActivity();
        view = getView();

        databaseViewModel = new ViewModelProvider(activity).get(DatabaseViewModel.class);
        final Context context = getContext();
        progressBar = activity.findViewById(R.id.progressBar);

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
            }).execute(databaseViewModel.getDatabaseValue());
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
            public void accept(final BasketItemAndProduct basketItemAndProduct, Button closeButton) {

                new MaterialAlertDialogBuilder(context)
                        .setTitle("Подтверждение")
                        .setMessage("Удалить продукт '" + basketItemAndProduct.product.title + "' из корзины?")
                        .setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                new DeleteBasketItemAsyncTask(databaseViewModel.getDatabaseValue(), new OnAsyncTaskAction<Void>() {
                                    @Override
                                    public void onStart() {
                                        toggleProgressBar(progressBar);
                                    }

                                    @Override
                                    public void onFinish(Void result) {
                                        toggleProgressBar(progressBar);
                                        adapter.remove(basketItemAndProduct);
                                        adapter.notifyDataSetChanged();


                                        CharSequence qwe = "Продукт из корзины успешно удален";
                                        Snackbar.make(view, qwe, Snackbar.LENGTH_SHORT)
                                                .setAction("X", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                    }
                                                })
                                                .show();

                                    }

                                    @Override
                                    public void onProgress(Object... objects) {

                                    }
                                }).execute(basketItemAndProduct.basketItem);


                            }
                        })
                        .setNegativeButton("Отмена", null)
                        .create().show();
            }
        });
    }

    private void setAdapter() {
        ListView basketLv = view.findViewById(R.id.basketLv);
        basketLv.setAdapter(adapter);
    }

    private void toggleProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(progressBar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }
}