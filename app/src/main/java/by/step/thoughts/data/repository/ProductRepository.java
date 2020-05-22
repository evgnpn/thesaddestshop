package by.step.thoughts.data.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import by.step.thoughts.data.dao.ProductDao;
import by.step.thoughts.entity.Product;

public class ProductRepository extends BaseRepository<Product> {

    private ProductDao productDao;

    public ProductRepository(ProductDao productDao) {
        this.productDao = productDao;
    }

    public LiveData<List<Product>> getAll() {
        return productDao.getAll();
    }

    public void insert(Product... products) {
        new InsertAsyncTask<Product, ProductDao>(productDao).execute(products);
    }

    public void delete(Product... products) {
        new DeleteAsyncTask<Product, ProductDao>(productDao).execute(products);
    }

    public void update(Product... products) {
        new UpdateAsyncTask<Product, ProductDao>(productDao).execute(products);
    }
}
