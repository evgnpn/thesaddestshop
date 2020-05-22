package by.step.thoughts.data.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import by.step.thoughts.data.dao.PurchaseDao;
import by.step.thoughts.entity.Purchase;

public class PurchaseRepository extends BaseRepository<Purchase> {

    public PurchaseRepository(PurchaseDao purchaseDao) {
        super(purchaseDao);
    }

//    public LiveData<List<Purchase>> getAll() {
//        return purchaseDao.getAll();
//    }
//
//    public void insert(Purchase... purchases) {
//        new InsertAsyncTask<>(purchaseDao).execute(purchases);
//    }
//
//    public void delete(Purchase... purchases) {
//        new DeleteAsyncTask<>(purchaseDao).execute(purchases);
//    }
//
//    public void update(Purchase... purchases) {
//        new UpdateAsyncTask<>(purchaseDao).execute(purchases);
//    }
}
