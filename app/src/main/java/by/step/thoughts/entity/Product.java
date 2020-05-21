package by.step.thoughts.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Products",
        foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "categoryId", onDelete = ForeignKey.CASCADE),
        indices = @Index(value = "categoryId"))
public class Product implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "price")
    public double price;

    @ColumnInfo(name = "image")
    public byte[] image;

    @ColumnInfo(name = "categoryId")
    public int categoryId;

    public Product() {
    }

    @Ignore
    protected Product(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        price = in.readDouble();
        image = in.createByteArray();
        categoryId = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }

    @Ignore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeByteArray(image);
        dest.writeInt(categoryId);
    }
}
