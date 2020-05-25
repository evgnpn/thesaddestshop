package by.step.thoughts.data.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import by.step.thoughts.data.dao.PurchaseDao;
import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.relation.purchase.PurchaseWithItemsAndProduct;

public class PurchaseRepository extends BaseRepository<Purchase> {

    public PurchaseRepository(PurchaseDao purchaseDao) {
        super(purchaseDao);
    }

    public LiveData<Purchase> getById(int id) {
        return ((PurchaseDao) getDao()).getByIdLiveData(id);
    }

    public LiveData<List<PurchaseWithItemsAndProduct>> purchaseWithItemsAndProducts() {
        return ((PurchaseDao) getDao()).purchaseWithItemsAndProducts();
    }
}
