package by.step.thoughts.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.entity.Product;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM Products")
    List<Product> getAll();

    @Query("SELECT * FROM Products WHERE id = :id LIMIT 1")
    Product getById(int id);

    @Insert
    void insert(Product... products);

    @Delete
    void delete(Product... products);

    @Update
    void update(Product... products);
}
