package by.step.thoughts.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import by.step.thoughts.BuildConfig;
import by.step.thoughts.R;
import by.step.thoughts.entity.Product;

public class BasketListAdapter extends ArrayAdapter<Product> {

    private int layoutResource;

    public BasketListAdapter(@NonNull Context context, int layoutResource) {
        super(context, layoutResource);
        this.layoutResource = layoutResource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView != null ? convertView : View.inflate(getContext(), layoutResource, null);

        Product product = getItem(position);

        TextView indexTv = view.findViewById(R.id.indexTv);
        TextView titleTv = view.findViewById(R.id.titleTv);
        TextView amountTv = view.findViewById(R.id.amountTv);
        Button closeBtn = view.findViewById(R.id.closeBtn);

        if (product != null) {
            productTitleTv.setText(product.title);
            productDescriptionTv.setText(product.description);
            productPriceTv.setText(String.valueOf(product.price));
            setImageViewWithByteArray(productImageIv, product.image);
        }

        return view;
    }
}
