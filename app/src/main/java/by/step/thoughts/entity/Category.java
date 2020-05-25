package by.step.thoughts.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Categories",
        indices = @Index(value = "title", unique = true))
public class Category {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "title")
    public String title;

    @Ignore
    public Category() {
    }

    public Category(String title) {
        this.title = title;
    }
}
