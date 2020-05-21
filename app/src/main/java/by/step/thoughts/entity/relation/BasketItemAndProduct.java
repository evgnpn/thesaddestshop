package by.step.thoughts.entity.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.Product;

public class BasketItemAndProduct {
    @Embedded
    public BasketItem basketItem;
    @Relation(
            parentColumn = "productId",
            entityColumn = "id"
    )
    public Product product;
}
