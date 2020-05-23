package by.step.thoughts.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import by.step.thoughts.data.repository.BasketItemRepository;
import by.step.thoughts.data.repository.CategoryRepository;
import by.step.thoughts.data.repository.ProductRepository;
import by.step.thoughts.data.repository.PurchaseRepository;
import by.step.thoughts.data.repository.PurseRepository;


public class DataViewModel extends ViewModel {

    private MutableLiveData<Boolean> loadStatus = new MutableLiveData<>();

    private ProductRepository productRepository;
    private BasketItemRepository basketItemRepository;
    private CategoryRepository categoryRepository;
    private PurchaseRepository purchaseRepository;
    private PurseRepository purseRepository;

    public DataViewModel(
            @NonNull ProductRepository productRepository,
            @NonNull BasketItemRepository basketItemRepository,
            @NonNull CategoryRepository categoryRepository,
            @NonNull PurchaseRepository purchaseRepository,
            @NonNull PurseRepository purseRepository) {
        this.productRepository = productRepository;
        this.basketItemRepository = basketItemRepository;
        this.categoryRepository = categoryRepository;
        this.purchaseRepository = purchaseRepository;
        this.purseRepository = purseRepository;
        loadStatus.setValue(false);
    }

    public MutableLiveData<Boolean> getLoadStatus() {
        return loadStatus;
    }

    public void setLoadingStatus(boolean enabled) {
        loadStatus.postValue(enabled);
    }

    public boolean isInLoadingProcess() {
        Boolean status = loadStatus.getValue();
        return status == null ? false : status;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public BasketItemRepository getBasketItemRepository() {
        return basketItemRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public PurchaseRepository getPurchaseRepository() {
        return purchaseRepository;
    }

    public PurseRepository getPurseRepository() {
        return purseRepository;
    }
}
