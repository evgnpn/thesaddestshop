package by.step.thoughts.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductDetailsFragmentViewModel extends ViewModel {

    private MutableLiveData<Boolean> productDetailsOpenedLiveData = new MutableLiveData<>();

    public ProductDetailsFragmentViewModel() {
        productDetailsOpenedLiveData.setValue(false);
    }

    public MutableLiveData<Boolean> getProductDetailsOpenedLiveData() {
        return productDetailsOpenedLiveData;
    }

    public void setProductDetailsOpened(boolean opened) {
        productDetailsOpenedLiveData.postValue(opened);
    }

    public boolean productDetailsIsOpened() {
        Boolean opened = productDetailsOpenedLiveData.getValue();
        return opened == null ? false : opened;
    }
}
