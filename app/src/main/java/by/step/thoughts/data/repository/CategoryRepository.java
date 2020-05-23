package by.step.thoughts.data.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import by.step.thoughts.data.dao.CategoryDao;
import by.step.thoughts.entity.Category;
import by.step.thoughts.entity.relation.CategoryWithProducts;

public class CategoryRepository extends BaseRepository<Category> {

    public CategoryRepository(CategoryDao categoryDao) {
        super(categoryDao);
    }

    public LiveData<List<CategoryWithProducts>> getCategoryWithProducts() {
        return ((CategoryDao) getDao()).getCategoryWithProducts();
    }

//    public LiveData<List<Category>> getAll() {
//        return categoryDao.getAll();
//    }
//
//    public void insert(Category... categories) {
//        new InsertAsyncTask<>(categoryDao).execute(categories);
//    }
//
//    public void delete(Category... categories) {
//        new DeleteAsyncTask<>(categoryDao).execute(categories);
//    }
//
//    public void update(Category... categories) {
//        new UpdateAsyncTask<>(categoryDao).execute(categories);
//    }
}
