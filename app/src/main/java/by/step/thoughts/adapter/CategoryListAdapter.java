package by.step.thoughts.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import by.step.thoughts.R;
import by.step.thoughts.entity.Category;

public class CategoryListAdapter extends ArrayAdapter<Category> {

    private int layoutResource;

    public CategoryListAdapter(@NonNull Context context, int layoutResource) {
        super(context, layoutResource);
        this.layoutResource = layoutResource;
    }

    public CategoryListAdapter(@NonNull Context context, int layoutResource, List<Category> categories) {
        super(context, layoutResource, categories);
        this.layoutResource = layoutResource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View view = convertView != null ? convertView : View.inflate(getContext(), layoutResource, null);

        Category category = getItem(position);

        TextView categoryTitleTv = view.findViewById(R.id.categoryTitleTv);
        // TextView categoryProductCount = convertView.findViewById(R.id.categoryProductCount);

        if (category != null) {
            categoryTitleTv.setText(category.title);
            // categoryProductCount.setText(category.co);
        }

        return view;
    }
}
