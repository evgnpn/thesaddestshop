package by.step.thoughts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import by.step.thoughts.R;
import by.step.thoughts.entity.Product;

public class UpdateProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            Product product = bundle.getParcelable("PRODUCT");

            if (product != null) {

                TextInputEditText titleEt = findViewById(R.id.title);
                TextInputEditText descriptionEt = findViewById(R.id.description);
                TextInputEditText priceEt = findViewById(R.id.price);

                titleEt.setText(product.title);
                descriptionEt.setText(product.description);
                priceEt.setText(String.valueOf(product.price));

                MaterialButton cancelBtn = findViewById(R.id.cancel);
                MaterialButton sendBtn = findViewById(R.id.send);

                cancelBtn.setOnClickListener(v -> finish());
                sendBtn.setOnClickListener(v -> {

                    Editable title = titleEt.getText();
                    if (title != null)
                        product.title = title.toString();

                    Editable description = descriptionEt.getText();
                    if (description != null)
                        product.description = description.toString();

                    Editable price = priceEt.getText();
                    if (price != null) {

                        try {
                            product.price = Double.parseDouble(price.toString());
                        } catch (NumberFormatException ex) {
                            Snackbar.make(findViewById(R.id.container), "+",
                                    Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    Intent intent = new Intent();
                    intent.putExtra("PRODUCT", product);
                    setResult(RESULT_OK, intent);
                    finish();
                });

            }
        }
    }
}
