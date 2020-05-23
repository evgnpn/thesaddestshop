package by.step.thoughts.data.repository;

import by.step.thoughts.data.dao.PurchaseDao;
import by.step.thoughts.entity.Purchase;

public class PurchaseRepository extends BaseRepository<Purchase> {

    public PurchaseRepository(PurchaseDao purchaseDao) {
        super(purchaseDao);
    }

}
