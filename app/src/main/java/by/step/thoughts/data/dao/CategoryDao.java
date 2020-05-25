package by.step.thoughts.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.data.DataAccessObject;
import by.step.thoughts.entity.Category;
import by.step.thoughts.entity.relation.CategoryWithProducts;

@Dao
public interface CategoryDao extends DataAccessObject<Category> {

    @Query("SELECT * FROM Categories")
    List<Category> getAll();

    @Query("SELECT * FROM Categories")
    LiveData<List<Category>> getAllLiveData();

    @Query("SELECT * FROM Categories WHERE id = :id LIMIT 1")
    LiveData<Category> getByIdLiveData(int id);

    @Transaction
    @Query("SELECT * FROM Categories")
    LiveData<List<CategoryWithProducts>> getCategoryWithProducts();

    @Insert
    void insert(Category... categories);

    @Delete
    void delete(Category... categories);

    @Update
    void update(Category... categories);
}
