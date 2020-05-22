package by.step.thoughts.data.repository;

import by.step.thoughts.data.dao.ProductDao;
import by.step.thoughts.entity.Product;

public class ProductRepository extends BaseRepository<Product> {

    public ProductRepository(ProductDao productDao) {
        super(productDao);
    }

//    public LiveData<List<Product>> getAll() {
//        return productDao.getAll();
//    }
//
//    public void insert(Product... products) {
//        new InsertAsyncTask<>(productDao).execute(products);
//    }
//
//    public void delete(Product... products) {
//        new DeleteAsyncTask<>(productDao).execute(products);
//    }
//
//    public void update(Product... products) {
//        new UpdateAsyncTask<>(productDao).execute(products);
//    }
}
