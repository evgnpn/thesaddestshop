package by.step.thoughts.entity.relation.purchase;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.PurchaseItem;

public class PurchaseWithItemsAndProduct {

    @Embedded
    public Purchase purchase;

    @Relation(
            entity = PurchaseItem.class,
            parentColumn = "id",
            entityColumn = "purchaseId"
    )
    public List<PurchaseItemAndProduct> purchaseItemAndProducts;
}
