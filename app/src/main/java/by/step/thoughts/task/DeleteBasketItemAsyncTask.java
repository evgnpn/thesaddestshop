package by.step.thoughts.task;

import android.os.AsyncTask;

import java.util.List;

import by.step.thoughts.data.AppDatabase;
import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.relation.BasketItemAndProduct;
import by.step.thoughts.interfaces.OnAsyncTaskAction;

public class DeleteBasketItemAsyncTask extends AsyncTask<BasketItem, Integer, Void> {

    private OnAsyncTaskAction<Void> onAsyncTaskAction;
    private AppDatabase appDatabase;

    public DeleteBasketItemAsyncTask(AppDatabase appDatabase, OnAsyncTaskAction<Void> onAsyncTaskAction) {
        this.appDatabase = appDatabase;
        this.onAsyncTaskAction = onAsyncTaskAction;
    }

    public DeleteBasketItemAsyncTask(OnAsyncTaskAction<Void> onAsyncTaskAction) {
        this.onAsyncTaskAction = onAsyncTaskAction;
    }

    public OnAsyncTaskAction<Void> getOnAsyncTaskAction() {
        return onAsyncTaskAction;
    }

    public void setOnAsyncTaskAction(OnAsyncTaskAction<Void> onAsyncTaskAction) {
        this.onAsyncTaskAction = onAsyncTaskAction;
    }

    @Override
    protected Void doInBackground(BasketItem... items) {

        for (int i = 0; i < items.length; i++) {
            appDatabase.getBasketItemDao().delete(items[i]);
            publishProgress(i + 1);
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (onAsyncTaskAction != null)
            onAsyncTaskAction.onStart();
    }

    @Override
    protected void onPostExecute(Void basketItemAndProducts) {
        super.onPostExecute(basketItemAndProducts);
        if (onAsyncTaskAction != null)
            onAsyncTaskAction.onFinish(basketItemAndProducts);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (onAsyncTaskAction != null)
            onAsyncTaskAction.onProgress(values[0]);
    }
}
