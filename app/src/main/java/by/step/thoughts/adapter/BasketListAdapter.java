package by.step.thoughts.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import by.step.thoughts.R;
import by.step.thoughts.entity.relation.BasketItemAndProduct;

public class BasketListAdapter extends ArrayAdapter<BasketItemAndProduct> {

    private int layoutResource;
    private OnCloseClickListener onCloseClickListener;

    public BasketListAdapter(@NonNull Context context, int layoutResource, List<BasketItemAndProduct> basketItemAndProducts) {
        super(context, layoutResource, basketItemAndProducts);
        this.layoutResource = layoutResource;
    }

    public OnCloseClickListener getOnCloseClickListener() {
        return onCloseClickListener;
    }

    public void setOnCloseClickListener(OnCloseClickListener onCloseClickListener) {
        this.onCloseClickListener = onCloseClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView != null ? convertView : View.inflate(getContext(), layoutResource, null);

        final BasketItemAndProduct basketItemAndProduct = getItem(position);

        TextView titleTv = view.findViewById(R.id.titleTv);
        TextView amountTv = view.findViewById(R.id.amountTv);
        final Button closeBtn = view.findViewById(R.id.closeBtn);

        if (basketItemAndProduct != null) {
            titleTv.setText(basketItemAndProduct.product.title);
            amountTv.setText(String.valueOf(basketItemAndProduct.basketItem.amount));

            if (onCloseClickListener != null) {
                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCloseClickListener.accept(basketItemAndProduct, closeBtn);
                    }
                });
            }
        }

        return view;
    }

    public interface OnCloseClickListener {
        void accept(BasketItemAndProduct basketItemAndProduct, Button closeButton);
    }
}
