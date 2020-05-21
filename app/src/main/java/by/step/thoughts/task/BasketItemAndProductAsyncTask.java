package by.step.thoughts.task;

import android.os.AsyncTask;

import java.util.List;

import by.step.thoughts.data.AppDatabase;
import by.step.thoughts.entity.relation.BasketItemAndProduct;
import by.step.thoughts.interfaces.OnAsyncTaskAction;

public class BasketItemAndProductAsyncTask extends AsyncTask<AppDatabase, Void, List<BasketItemAndProduct>> {

    private OnAsyncTaskAction<List<BasketItemAndProduct>> onAsyncTaskAction;

    public BasketItemAndProductAsyncTask(OnAsyncTaskAction<List<BasketItemAndProduct>> onAsyncTaskAction) {
        this.onAsyncTaskAction = onAsyncTaskAction;
    }

    public OnAsyncTaskAction<List<BasketItemAndProduct>> getOnAsyncTaskAction() {
        return onAsyncTaskAction;
    }

    public void setOnAsyncTaskAction(OnAsyncTaskAction<List<BasketItemAndProduct>> onAsyncTaskAction) {
        this.onAsyncTaskAction = onAsyncTaskAction;
    }

    @Override
    protected List<BasketItemAndProduct> doInBackground(AppDatabase... appDatabases) {
        return appDatabases[0].getBasketItemDao().getBasketItemAndProducts();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (onAsyncTaskAction != null)
            onAsyncTaskAction.onStart();
    }

    @Override
    protected void onPostExecute(List<BasketItemAndProduct> basketItemAndProducts) {
        super.onPostExecute(basketItemAndProducts);
        if (onAsyncTaskAction != null)
            onAsyncTaskAction.onFinish(basketItemAndProducts);
    }
}
