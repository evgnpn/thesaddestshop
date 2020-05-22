package by.step.thoughts.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface DataAccessObject<TEntity> {
    LiveData<List<TEntity>> getAll();

    void insert(TEntity[] entities);

    void delete(TEntity[] entities);

    void update(TEntity[] entities);
}
