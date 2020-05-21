package by.step.thoughts.entity.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import by.step.thoughts.entity.Category;
import by.step.thoughts.entity.Product;

public class CategoryWithProducts {
    @Embedded
    public Category category;
    @Relation(
            parentColumn = "id",
            entityColumn = "categoryId"
    )
    public List<Product> products;
}
