package by.step.thoughts.interfaces;

public interface OnAsyncTaskAction<T> {
    void onStart();

    void onFinish(T result);

    void onProgress(Object... objects);
}
