package by.step.thoughts.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import by.step.thoughts.Constants;
import by.step.thoughts.data.AppDatabase;

public class DatabaseViewModel extends ViewModel {

    private final MutableLiveData<AppDatabase> databaseLiveData = new MutableLiveData<>();

    public DatabaseViewModel() {
    }

    public LiveData<AppDatabase> getDatabase() {
        return databaseLiveData;
    }

    public void setContext(Context context) {

        AppDatabase database = Room
                .databaseBuilder(context, AppDatabase.class, Constants.DATABASE_FILENAME)
                .createFromAsset(Constants.DATABASE_FILENAME)
                .build();

        databaseLiveData.setValue(database);
    }

    public AppDatabase getDatabaseValue() {
        return databaseLiveData.getValue();
    }
}
