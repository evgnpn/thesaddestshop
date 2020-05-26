package by.step.thoughts.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;
import java.util.UUID;

import by.step.thoughts.R;
import by.step.thoughts.activity.UpdateProductActivity;
import by.step.thoughts.adapter.ShopExpandableListAdapter;
import by.step.thoughts.entity.Category;
import by.step.thoughts.entity.Product;
import by.step.thoughts.entity.relation.CategoryWithProducts;
import by.step.thoughts.viewmodel.DataViewModel;

import static by.step.thoughts.Constants.LOG_TAG;

public class ShopFragment extends Fragment {

    public static final String TAG = ShopFragment.class.getSimpleName() + " " + UUID.randomUUID().toString();

    private Context context;
    private FragmentActivity activity;

    private ShopExpandableListAdapter adapter;
    private ExpandableListView productsElv;

    private DataViewModel dataViewModel;

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v,
                                    @Nullable ContextMenu.ContextMenuInfo menuInfo) {

        if (v.getId() == R.id.productsElv) {
            ExpandableListView.ExpandableListContextMenuInfo info =
                    (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
            if (info != null) {
                int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
                Intent intent = new Intent();

                switch (ExpandableListView.getPackedPositionType(info.packedPosition)) {
                    case ExpandableListView.PACKED_POSITION_TYPE_GROUP:
                        CategoryWithProducts categoryWithProducts =
                                (CategoryWithProducts) this.adapter.getGroup(groupPos);

                        intent.putExtra("CATEGORY", categoryWithProducts.category);

                        menu.setHeaderTitle("Категория: " + categoryWithProducts.category.title);
                        menu.add(ContextMenu.NONE, 0, ContextMenu.NONE, "Удалить")
                                .setIntent(intent);
                        menu.add(ContextMenu.NONE, 1, ContextMenu.NONE, "Редактировать")
                                .setIntent(intent);
                        break;
                    case ExpandableListView.PACKED_POSITION_TYPE_CHILD:
                        int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);
                        Product product = (Product) this.adapter.getChild(groupPos, childPos);

                        intent.putExtra("PRODUCT", product);

                        menu.setHeaderTitle(product.title);
                        menu.add(ContextMenu.NONE, 2, ContextMenu.NONE, "Удалить")
                                .setIntent(intent);
                        menu.add(ContextMenu.NONE, 3, ContextMenu.NONE, "Редактировать")
                                .setIntent(intent);

                        break;

                    default:
                        break;
                }
            }
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        Bundle bundle = item.getIntent().getExtras();

        if (bundle != null) {
            switch (item.getItemId()) {
                case 0: // del category
                    Category category = bundle.getParcelable("CATEGORY");

                    if (category != null)
                        showCategoryProductDialog(category);
                    break;
                case 1: // edit category
                    Toast.makeText(context, "sss", Toast.LENGTH_SHORT).show();
                    break;
                case 2: // del product
                    Product product = bundle.getParcelable("PRODUCT");
                    if (product != null)
                        showDeleteProductDialog(product);
                    break;
                case 3: // edit product
                    product = bundle.getParcelable("PRODUCT");

                    Intent intent = new Intent(activity, UpdateProductActivity.class);
                    intent.putExtra("PRODUCT", product);

                    startActivityForResult(intent, 1);

                    break;
                default:
                    break;
            }
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(context, resultCode + " result code", Toast.LENGTH_SHORT).show();
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        Product product = bundle.getParcelable("PRODUCT");
                        if (product != null) {
                            dataViewModel.setLoadingStatus(true);
                            dataViewModel.getProductRepository().update(new Product[]{product});
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onCreate (savedInstance: "
                + (savedInstanceState != null) + ")");

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shop_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName()
                + "] onActivityCreated (savedInstance: " + (savedInstanceState != null) + ")");

        initVars();
        loadData();
        registerForContextMenu(productsElv);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onDestroy");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName()
                + "] onHiddenChanged (hidden: " + hidden + ")");

        Fragment detailsFragment = getChildFragmentManager()
                .findFragmentByTag(ProductDetailsFragment.TAG);
        if (detailsFragment != null)
            detailsFragment.onHiddenChanged(hidden);
    }

    private void initVars() {
        context = requireContext();
        activity = requireActivity();
        View view = requireView();
        productsElv = view.findViewById(R.id.productsElv);
        dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
    }

    private void loadData() {
        dataViewModel.setLoadingStatus(true);

        dataViewModel.getCategoryRepository().getCategoryWithProducts().observe(activity, items -> {

            if (adapter == null) {
                createAdapter(context, items);
                productsElv.setAdapter(adapter);
            } else {
                adapter.setCategoryWithProducts(items);
                adapter.notifyDataSetChanged();
            }

            dataViewModel.setLoadingStatus(false);
        });
    }

    private void createAdapter(Context context, List<CategoryWithProducts> items) {
        adapter = new ShopExpandableListAdapter(context, R.layout.group_item,
                R.layout.product_item, items);

        adapter.setOnChildClickAction((category, product) ->
                getChildFragmentManager().beginTransaction()
                        .add(R.id.container, ProductDetailsFragment.newInstance(product),
                                ProductDetailsFragment.TAG)
                        .commit());
    }

    private void showCategoryProductDialog(Category category) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Удаление")
                .setMessage("Удалить категорию '" + category.title + "'?" +
                        "\n\nКатегория и все продукты в этой категории будут удалены.")
                .setPositiveButton("Удалить", (dialog, which) -> {
                    dataViewModel.setLoadingStatus(true);
                    dataViewModel.getCategoryRepository().delete(new Category[]{category});
                })
                .setNegativeButton("Отмена", (dialog, which) ->
                        dialog.cancel())
                .create().show();
    }

    private void showDeleteProductDialog(Product product) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Удаление")
                .setMessage("Удалить продукт '" + product.title + "' ?")
                .setPositiveButton("Удалить", (dialog, which) -> {
                    dataViewModel.setLoadingStatus(true);
                    dataViewModel.getProductRepository().delete(new Product[]{product});
                })
                .setNegativeButton("Отмена", (dialog, which) ->
                        dialog.cancel())
                .create().show();
    }
}
