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

    public LiveData<BasketItem> findByProductId(int id) {
        return ((BasketItemDao) getDao()).getByProductId(id);
    }

    public LiveData<List<BasketItemAndProduct>> getBasketItemAndProducts() {
        return ((BasketItemDao) getDao()).getBasketItemAndProductsLiveData();
    }
}
