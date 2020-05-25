package by.step.thoughts.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import by.step.thoughts.Constants;

@Entity(tableName = "Purses")
public class Purse {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    public String id = Constants.PURSE_ID;

    @ColumnInfo(name = "money")
    public double money;
}
