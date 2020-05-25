package by.step.thoughts.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Purchases")
public class Purchase {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "date")
    public Date date;

    public Purchase() {
    }

    @Ignore
    public Purchase(Date date) {
        this.date = date;
    }
}
