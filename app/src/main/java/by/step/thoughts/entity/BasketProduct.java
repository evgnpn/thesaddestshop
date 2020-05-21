package by.step.thoughts.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
        tableName = "BasketProducts",
        primaryKeys = {"id", "productId"},
        foreignKeys = @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "productId", onDelete = ForeignKey.CASCADE),
        indices = @Index(value = "productId"))
public class BasketProduct {

    @NonNull
    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "amount")
    public int amount;

    @ColumnInfo(name = "productId")
    public int productId;
}
