package by.step.thoughts.data.repository;

import androidx.lifecycle.LiveData;

import by.step.thoughts.data.dao.PurchaseItemDao;
import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.PurchaseItem;

public class PurchaseItemRepository extends BaseRepository<Purchase> {

    public PurchaseItemRepository(PurchaseItemDao purchaseItemDao) {
        super(purchaseItemDao);
    }

    public LiveData<PurchaseItem> getById(int id) {
        return ((PurchaseItemDao) getDao()).getByIdLiveData(id);
    }
}
