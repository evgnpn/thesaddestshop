package by.step.thoughts.data.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import by.step.thoughts.data.DataAccessObject;

public abstract class BaseRepository<TEntity> {

    private DataAccessObject<TEntity> dao;

    BaseRepository(DataAccessObject<TEntity> dao) {
        this.dao = dao;
    }

    public LiveData<List<TEntity>> getAll() {
        return dao.getAll();
    }

    public void insert(TEntity[] entities) {
        new InsertAsyncTask<>(dao).execute(entities);
    }

    public void delete(TEntity[] entities) {
        new DeleteAsyncTask<>(dao).execute(entities);
    }

    public void update(TEntity[] entities) {
        new UpdateAsyncTask<>(dao).execute(entities);
    }

    public DataAccessObject<TEntity> getDao() {
        return dao;
    }

    protected static class InsertAsyncTask<T> extends AsyncTask<T, Void, Void> {

        private DataAccessObject<T> dao;

        InsertAsyncTask(DataAccessObject<T> dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final T[] params) {
            dao.insert(params);
            return null;
        }
    }

    protected static class DeleteAsyncTask<T> extends AsyncTask<T, Void, Void> {

        private DataAccessObject<T> dao;

        DeleteAsyncTask(DataAccessObject<T> dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final T[] params) {
            dao.delete(params);
            return null;
        }
    }

    protected static class UpdateAsyncTask<T> extends AsyncTask<T, Void, Void> {

        private DataAccessObject<T> dao;

        UpdateAsyncTask(DataAccessObject<T> dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final T[] params) {
            dao.update(params);
            return null;
        }
    }
}
