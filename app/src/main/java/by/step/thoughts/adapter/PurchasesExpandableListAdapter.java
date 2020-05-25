package by.step.thoughts.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import by.step.thoughts.Constants;
import by.step.thoughts.Converters;
import by.step.thoughts.R;
import by.step.thoughts.entity.Product;
import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.PurchaseItem;
import by.step.thoughts.entity.relation.purchase.PurchaseItemAndProduct;
import by.step.thoughts.entity.relation.purchase.PurchaseWithItemsAndProduct;


public class PurchasesExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<PurchaseWithItemsAndProduct> items;
    private int groupLayoutResource, childLayoutResource;
    private OnChildClickListener onChildClickAction;


    public PurchasesExpandableListAdapter(
            Context context,
            int groupLayoutResource,
            int childLayoutResource,
            List<PurchaseWithItemsAndProduct> items) {
        this.context = context;
        this.items = items;
        this.groupLayoutResource = groupLayoutResource;
        this.childLayoutResource = childLayoutResource;
    }

    public void setOnChildClickAction(OnChildClickListener onChildClickAction) {
        this.onChildClickAction = onChildClickAction;
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return items.get(groupPosition).purchaseItemAndProducts.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).purchaseItemAndProducts.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return items.get(groupPosition).purchase.id;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return items.get(groupPosition).purchaseItemAndProducts.get(childPosition).product.id;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        View view = convertView != null ?
                convertView : View.inflate(context, groupLayoutResource, null);

        PurchaseWithItemsAndProduct purchaseWithItemsAndProduct =
                (PurchaseWithItemsAndProduct) getGroup(groupPosition);

        TextView titleTv = view.findViewById(R.id.title);
        TextView quantityTv = view.findViewById(R.id.quantity);

        titleTv.setText(Converters.dateToString(purchaseWithItemsAndProduct.purchase.date));
        quantityTv.setText(String.valueOf(
                purchaseWithItemsAndProduct.purchaseItemAndProducts.size()));

        return view;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View view = convertView != null ?
                convertView : View.inflate(context, childLayoutResource, null);

        final PurchaseItemAndProduct purchaseItemAndProduct =
                (PurchaseItemAndProduct) getChild(groupPosition, childPosition);


        if (onChildClickAction != null) {
            view.setOnClickListener(v ->
                    onChildClickAction.accept(
                            items.get(groupPosition).purchase,
                            items.get(groupPosition).purchaseItemAndProducts
                                    .get(childPosition).purchaseItem,
                            items.get(groupPosition).purchaseItemAndProducts
                                    .get(childPosition).product));
        }

        TextView titleTv = view.findViewById(R.id.title);
        TextView priceTv = view.findViewById(R.id.price);
        TextView quantityTv = view.findViewById(R.id.quantity);
        TextView priceSumTv = view.findViewById(R.id.priceSum);
        ImageView imageIv = view.findViewById(R.id.image);

        if (purchaseItemAndProduct != null) {
            titleTv.setText(purchaseItemAndProduct.product.title);
            priceTv.setText(String.format("%s%s", String.valueOf(
                    purchaseItemAndProduct.product.price), Constants.CURRENCY));
            quantityTv.setText(String.valueOf(purchaseItemAndProduct.purchaseItem.amount));
            priceSumTv.setText(String.format(Locale.getDefault(), "%.2f%s",
                    purchaseItemAndProduct.product.price
                            * purchaseItemAndProduct.purchaseItem.amount, Constants.CURRENCY));


            //setImageViewWithByteArray(productImageIv, product.image);
        }


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public interface OnChildClickListener {
        void accept(Purchase purchase, PurchaseItem purchaseItem, Product product);
    }

    private static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }
}

