package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import by.step.thoughts.R;
import by.step.thoughts.adapter.CategoryListAdapter;
import by.step.thoughts.entity.Category;
import by.step.thoughts.viewmodel.DatabaseViewModel;

public class CategoriesFragment extends Fragment {

    private CategoryListAdapter adapter;
    private DatabaseViewModel databaseViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.categories_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        databaseViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);


        Context context = getContext();

        if (context != null) {
            adapter = new CategoryListAdapter(context, R.layout.category_item);

            View view = getView();

            if (view != null) {

                ListView categoriesLv = view.findViewById(R.id.categoriesLv);
                categoriesLv.setAdapter(adapter);


                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Category> categories = databaseViewModel
                                .getDatabase()
                                .getValue()
                                .getCategoryDao()
                                .getAll();

                        for (Category category : categories) {
                            adapter.add(category);
                        }
                    }
                });
                th.start();


            }

        }

        // mViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        // TODO: Use the ViewModel
    }

}
