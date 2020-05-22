package by.step.thoughts.data.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import by.step.thoughts.data.dao.BasketItemDao;
import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.relation.BasketItemAndProduct;

public class BasketItemRepository extends BaseRepository<BasketItem> {

    private BasketItemDao BasketItemDao;

    public BasketItemRepository(BasketItemDao BasketItemDao) {
        this.BasketItemDao = BasketItemDao;
    }

    public LiveData<List<BasketItem>> getAll() {
        return BasketItemDao.getAll();
    }

    public LiveData<List<BasketItemAndProduct>> getBasketItemAndProducts() {
        return BasketItemDao.getBasketItemAndProducts();
    }

    public void insert(BasketItem... basketItems) {
        new InsertAsyncTask<BasketItem, BasketItemDao>(BasketItemDao).execute(basketItems);
    }

    public void delete(BasketItem... basketItems) {
        new DeleteAsyncTask<BasketItem, BasketItemDao>(BasketItemDao).execute(basketItems);
    }

    public void update(BasketItem... basketItems) {
        new UpdateAsyncTask<BasketItem, BasketItemDao>(BasketItemDao).execute(basketItems);
    }
}
