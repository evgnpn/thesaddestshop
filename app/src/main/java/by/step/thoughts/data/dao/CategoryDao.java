package by.step.thoughts.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.entity.Category;
import by.step.thoughts.entity.CategoryWithProducts;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM Categories")
    List<Category> getAll();

    @Query("SELECT * FROM Categories WHERE id = :id LIMIT 1")
    Category getById(int id);

    @Transaction
    @Query("SELECT * FROM Categories")
    List<CategoryWithProducts> getCategoryWithProducts();

    @Insert
    void insert(Category... categories);

    @Delete
    void delete(Category... categories);

    @Update
    void update(Category... categories);

}
