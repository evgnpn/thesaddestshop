package by.step.thoughts.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import by.step.thoughts.Constants;
import by.step.thoughts.R;
import by.step.thoughts.entity.Category;
import by.step.thoughts.entity.relation.CategoryWithProducts;
import by.step.thoughts.entity.Product;

public class ShopExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<CategoryWithProducts> categoryWithProducts;
    private int groupLayoutResource, childLayoutResource;
    private OnChildClickListener onChildClickAction;


    public ShopExpandableListAdapter(
            Context context,
            int groupLayoutResource,
            int childLayoutResource,
            List<CategoryWithProducts> categoryWithProducts) {
        this.context = context;
        this.categoryWithProducts = categoryWithProducts;
        this.groupLayoutResource = groupLayoutResource;
        this.childLayoutResource = childLayoutResource;
    }

    public void setOnChildClickAction(OnChildClickListener onChildClickAction) {
        this.onChildClickAction = onChildClickAction;
    }

    @Override
    public int getGroupCount() {
        return categoryWithProducts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categoryWithProducts.get(groupPosition).products.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoryWithProducts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categoryWithProducts.get(groupPosition).products.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return categoryWithProducts.get(groupPosition).category.id;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return categoryWithProducts.get(groupPosition).products.get(childPosition).id;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View view = convertView != null ? convertView : View.inflate(context, groupLayoutResource, null);

        CategoryWithProducts categoryWithProducts = (CategoryWithProducts) getGroup(groupPosition);

        TextView categoryTitleTv = view.findViewById(R.id.categoryTitleTv);
        TextView categoryProductCount = view.findViewById(R.id.categoryProductCount);

        categoryTitleTv.setText(categoryWithProducts.category.title);
        categoryProductCount.setText(String.valueOf(categoryWithProducts.products.size()));

        return view;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View view = convertView != null ? convertView : View.inflate(context, childLayoutResource, null);
        final Product product = (Product) getChild(groupPosition, childPosition);
        final int SHORT_TEXT_SIZE = 100;

        if (onChildClickAction != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChildClickAction.accept(categoryWithProducts.get(groupPosition).category, product);
                }
            });
        }

        TextView productTitleTv = view.findViewById(R.id.productTitleTv);
        TextView productPriceTv = view.findViewById(R.id.productPriceTv);
        TextView productDescriptionTv = view.findViewById(R.id.productDescriptionTv);

        productTitleTv.setText(product.title);
        productPriceTv.setText(String.format("%s%s", product.price, Constants.CURRENCY));
        productDescriptionTv.setText(product.description.length() > SHORT_TEXT_SIZE ? product.description.substring(0, SHORT_TEXT_SIZE) + "..." : product.description);

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public interface OnChildClickListener {
        void accept(Category t, Product u);
    }
}
