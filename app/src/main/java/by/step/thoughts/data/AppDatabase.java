package by.step.thoughts.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import by.step.thoughts.Constants;
import by.step.thoughts.data.dao.BasketItemDao;
import by.step.thoughts.data.dao.CategoryDao;
import by.step.thoughts.data.dao.ProductDao;
import by.step.thoughts.data.dao.PurchaseDao;
import by.step.thoughts.data.dao.PurseDao;
import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.Category;
import by.step.thoughts.entity.Product;
import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.PurchaseItem;
import by.step.thoughts.entity.Purse;

@Database(version = 1, entities = {Category.class, Product.class, Purchase.class, PurchaseItem.class, BasketItem.class, Purse.class})
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constants.DATABASE_FILENAME)
                            .createFromAsset(Constants.DATABASE_FILENAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    abstract public CategoryDao getCategoryDao();

    abstract public ProductDao getProductDao();

    abstract public PurchaseDao getPurchaseDao();

    abstract public BasketItemDao getBasketItemDao();

    abstract public PurseDao getPurseDaoDao();
}
