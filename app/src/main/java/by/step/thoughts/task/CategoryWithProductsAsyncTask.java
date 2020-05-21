package by.step.thoughts.task;

import android.os.AsyncTask;

import java.util.List;

import by.step.thoughts.data.AppDatabase;
import by.step.thoughts.entity.CategoryWithProducts;
import by.step.thoughts.interfaces.OnAsyncTaskAction;

public class CategoryWithProductsAsyncTask extends AsyncTask<AppDatabase, Void, List<CategoryWithProducts>> {

    private OnAsyncTaskAction<List<CategoryWithProducts>> onAsyncTaskAction;

    public CategoryWithProductsAsyncTask(OnAsyncTaskAction<List<CategoryWithProducts>> onAsyncTaskAction) {
        this.onAsyncTaskAction = onAsyncTaskAction;
    }

    public OnAsyncTaskAction<List<CategoryWithProducts>> getOnAsyncTaskAction() {
        return onAsyncTaskAction;
    }

    public void setOnAsyncTaskAction(OnAsyncTaskAction<List<CategoryWithProducts>> onAsyncTaskAction) {
        this.onAsyncTaskAction = onAsyncTaskAction;
    }

    @Override
    protected List<CategoryWithProducts> doInBackground(AppDatabase... appDatabases) {
        return appDatabases[0].getCategoryDao().getCategoryWithProducts();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (onAsyncTaskAction != null)
            onAsyncTaskAction.onStart();
    }

    @Override
    protected void onPostExecute(List<CategoryWithProducts> categoryWithProducts) {
        super.onPostExecute(categoryWithProducts);
        if (onAsyncTaskAction != null)
            onAsyncTaskAction.onFinish(categoryWithProducts);
    }
}
