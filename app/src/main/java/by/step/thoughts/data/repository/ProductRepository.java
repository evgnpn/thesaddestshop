package by.step.thoughts.data.repository;

import by.step.thoughts.data.dao.ProductDao;
import by.step.thoughts.entity.Product;

public class ProductRepository extends BaseRepository<Product> {

    public ProductRepository(ProductDao productDao) {
        super(productDao);
    }


}
