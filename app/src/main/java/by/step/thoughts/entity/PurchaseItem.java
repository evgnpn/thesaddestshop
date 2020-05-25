package by.step.thoughts.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "PurchaseItems",
        foreignKeys = {
                @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "productId", onDelete = ForeignKey.SET_NULL),
                @ForeignKey(entity = Purchase.class, parentColumns = "id", childColumns = "purchaseId", onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = "productId"),
                @Index(value = "purchaseId")
        })
public class PurchaseItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "amount")
    public int amount;

    @ColumnInfo(name = "productId")
    public int productId;

    @ColumnInfo(name = "purchaseId")
    public int purchaseId;
}
