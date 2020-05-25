package by.step.thoughts.entity.relation.purchase;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import by.step.thoughts.entity.Product;
import by.step.thoughts.entity.PurchaseItem;

public class PurchaseItemAndProduct {

    @Embedded
    public PurchaseItem purchaseItem;

    @Relation(
            parentColumn = "productId",
            entityColumn = "id"
    )
    public Product product;
}
