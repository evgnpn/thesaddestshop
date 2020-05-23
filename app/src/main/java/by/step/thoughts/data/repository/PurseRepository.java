package by.step.thoughts.data.repository;

import by.step.thoughts.data.dao.PurchaseDao;
import by.step.thoughts.data.dao.PurseDao;
import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.Purse;

public class PurseRepository extends BaseRepository<Purse> {

    public PurseRepository(PurseDao purseDao) {
        super(purseDao);
    }


}
