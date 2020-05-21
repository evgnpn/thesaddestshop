package by.step.thoughts.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import by.step.thoughts.data.dao.BasketProductDao;
import by.step.thoughts.data.dao.CategoryDao;
import by.step.thoughts.data.dao.ProductDao;
import by.step.thoughts.data.dao.PurchaseDao;
import by.step.thoughts.data.dao.PurseDao;
import by.step.thoughts.entity.BasketProduct;
import by.step.thoughts.entity.Category;
import by.step.thoughts.entity.Product;
import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.Purse;

@Database(version = 1, entities = {Category.class, Product.class, Purchase.class, BasketProduct.class, Purse.class})
public abstract class AppDatabase extends RoomDatabase {

    abstract public CategoryDao getCategoryDao();

    abstract public ProductDao getProductDao();

    abstract public PurchaseDao getPurchaseDao();

    abstract public BasketProductDao getBasketProductDao();

    abstract public PurseDao getPurseDaoDao();
}
