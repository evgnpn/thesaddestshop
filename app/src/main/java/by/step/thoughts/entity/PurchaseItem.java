package by.step.thoughts.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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
    public long id;

    @ColumnInfo(name = "amount")
    public int amount;

    @ColumnInfo(name = "productId")
    public Long productId;

    @ColumnInfo(name = "purchaseId")
    public long purchaseId;

    public PurchaseItem() {
    }

    @Ignore
    public PurchaseItem(int amount, long productId, long purchaseId) {
        this.amount = amount;
        this.productId = productId;
        this.purchaseId = purchaseId;
    }
}
