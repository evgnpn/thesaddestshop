package by.step.thoughts.data.repository;

import android.os.AsyncTask;

import by.step.thoughts.data.DataAccessObject;

public class BaseRepository<TEntity> {

    protected static class InsertAsyncTask<T, TEntityDao> extends AsyncTask<T, Void, Void> {

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

    protected static class DeleteAsyncTask<T, TEntityDao> extends AsyncTask<T, Void, Void> {

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

    protected static class UpdateAsyncTask<T, TEntityDao> extends AsyncTask<T, Void, Void> {

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
