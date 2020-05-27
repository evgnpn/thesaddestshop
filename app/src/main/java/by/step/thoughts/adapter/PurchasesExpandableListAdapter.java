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
    private List<PurchaseWithItemsAndProduct> purchaseWithItemsAndProducts;
    private int groupLayoutResource, childLayoutResource;
    private OnChildClickListener onChildClickAction;


    public PurchasesExpandableListAdapter(
            Context context,
            int groupLayoutResource,
            int childLayoutResource,
            List<PurchaseWithItemsAndProduct> purchaseWithItemsAndProducts) {
        this.context = context;
        this.purchaseWithItemsAndProducts = purchaseWithItemsAndProducts;
        this.groupLayoutResource = groupLayoutResource;
        this.childLayoutResource = childLayoutResource;
    }

    public List<PurchaseWithItemsAndProduct> getPurchaseWithItemsAndProducts() {
        return purchaseWithItemsAndProducts;
    }

    public void setPurchaseWithItemsAndProducts(
            List<PurchaseWithItemsAndProduct> purchaseWithItemsAndProducts) {
        this.purchaseWithItemsAndProducts = purchaseWithItemsAndProducts;
    }

    public void setOnChildClickAction(OnChildClickListener onChildClickAction) {
        this.onChildClickAction = onChildClickAction;
    }

    @Override
    public int getGroupCount() {
        return purchaseWithItemsAndProducts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return purchaseWithItemsAndProducts.get(groupPosition).purchaseItemAndProducts.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return purchaseWithItemsAndProducts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return purchaseWithItemsAndProducts.get(groupPosition)
                .purchaseItemAndProducts.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return purchaseWithItemsAndProducts.get(groupPosition).purchase.id;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        try {
            return purchaseWithItemsAndProducts.get(groupPosition)
                    .purchaseItemAndProducts.get(childPosition).product.id;
        } catch (Exception ex) {
            return 0;
        }
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

        TextView titleTv = view.findViewById(R.id.title);
        TextView priceTv = view.findViewById(R.id.price);
        TextView quantityTv = view.findViewById(R.id.quantity);
        TextView priceSumTv = view.findViewById(R.id.priceSum);
        ImageView imageIv = view.findViewById(R.id.image);

        try {

            final PurchaseItemAndProduct purchaseItemAndProduct =
                    (PurchaseItemAndProduct) getChild(groupPosition, childPosition);

            if (onChildClickAction != null) {
                view.setOnClickListener(v ->
                        onChildClickAction.accept(
                                purchaseWithItemsAndProducts.get(groupPosition).purchase,
                                purchaseWithItemsAndProducts.get(groupPosition)
                                        .purchaseItemAndProducts.get(childPosition).purchaseItem,
                                purchaseWithItemsAndProducts.get(groupPosition)
                                        .purchaseItemAndProducts.get(childPosition).product));
            }

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
        } catch (Exception e) {
            TextView multiplyTv = view.findViewById(R.id.multiply);
            TextView equalsTv = view.findViewById(R.id.equals);

            titleTv.setText("ПРОДУКТА БОЛЬШЕ НЕТ");
            priceTv.setText("");
            quantityTv.setText("");
            priceSumTv.setText("");
            multiplyTv.setText("");
            equalsTv.setText("");
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

