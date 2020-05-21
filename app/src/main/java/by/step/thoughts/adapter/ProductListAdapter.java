package by.step.thoughts.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import by.step.thoughts.R;
import by.step.thoughts.entity.Product;

public class ProductListAdapter extends ArrayAdapter<Product> {

    private int layoutResource;

    public ProductListAdapter(@NonNull Context context, int layoutResource) {
        super(context, layoutResource);
        this.layoutResource = layoutResource;
    }

    private static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView != null ? convertView : View.inflate(getContext(), layoutResource, null);

        Product product = getItem(position);

        TextView productTitleTv = view.findViewById(R.id.productTitleTv);
        TextView productDescriptionTv = view.findViewById(R.id.productDescriptionTv);
        TextView productPriceTv = view.findViewById(R.id.productPriceTv);
        ImageView productImageIv = view.findViewById(R.id.productImageIv);

        if (product != null) {
            productTitleTv.setText(product.title);
            productDescriptionTv.setText(product.description);
            productPriceTv.setText(String.valueOf(product.price));
            setImageViewWithByteArray(productImageIv, product.image);
        }

        return view;
    }
}
