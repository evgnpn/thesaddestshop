package by.step.thoughts.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "BasketItems",
        foreignKeys = @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "productId", onDelete = ForeignKey.CASCADE),
        indices = @Index(value = "productId"))
public class BasketItem {

    // PK & FK
    @PrimaryKey
    @ColumnInfo(name = "productId")
    public int productId;

    @ColumnInfo(name = "amount")
    public int amount;
}
