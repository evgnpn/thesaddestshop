package by.step.thoughts.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface DataAccessObject<TEntity> {

    List<TEntity> getAll();

    LiveData<List<TEntity>> getAllLiveData();

    void insert(TEntity[] entities);

    void delete(TEntity[] entities);

    void update(TEntity[] entities);
}
