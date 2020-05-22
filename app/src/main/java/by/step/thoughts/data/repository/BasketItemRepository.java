package by.step.thoughts.data.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import by.step.thoughts.data.dao.BasketItemDao;
import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.relation.BasketItemAndProduct;

public class BasketItemRepository extends BaseRepository<BasketItem> {

    public BasketItemRepository(BasketItemDao basketItemDao) {
        super(basketItemDao);
    }

//    public LiveData<List<BasketItem>> getAll() {
//        return getDao().getAll();
//    }

    public LiveData<List<BasketItemAndProduct>> getBasketItemAndProducts() {
        return ((BasketItemDao) getDao()).getBasketItemAndProducts();
    }

//    public void insert(BasketItem... basketItems) {
//        new InsertAsyncTask<>(BasketItemDao).execute(basketItems);
//    }
//
//    public void delete(BasketItem... basketItems) {
//        new DeleteAsyncTask<>(BasketItemDao).execute(basketItems);
//    }
//
//    public void update(BasketItem... basketItems) {
//        new UpdateAsyncTask<>(BasketItemDao).execute(basketItems);
//    }
}
