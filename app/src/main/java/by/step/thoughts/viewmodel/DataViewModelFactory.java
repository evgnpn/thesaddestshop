package by.step.thoughts.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

import by.step.thoughts.data.repository.BasketItemRepository;
import by.step.thoughts.data.repository.CategoryRepository;
import by.step.thoughts.data.repository.ProductRepository;
import by.step.thoughts.data.repository.PurchaseRepository;
import by.step.thoughts.data.repository.PurseRepository;

public class DataViewModelFactory implements ViewModelProvider.Factory {

    private ProductRepository productRepository;
    private BasketItemRepository basketItemRepository;
    private CategoryRepository categoryRepository;
    private PurchaseRepository purchaseRepository;
    private PurseRepository purseRepository;

    public DataViewModelFactory(
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
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (DataViewModel.class.isAssignableFrom(modelClass)) {

            try {
                return modelClass.getConstructor(ProductRepository.class, BasketItemRepository.class,
                        CategoryRepository.class, PurchaseRepository.class,
                        PurseRepository.class).newInstance(
                        productRepository,
                        basketItemRepository,
                        categoryRepository,
                        purchaseRepository,
                        purseRepository);
            } catch (NoSuchMethodException | IllegalAccessException
                    | InvocationTargetException | InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }
        throw new RuntimeException("modelClass should be SharedViewModel");
    }
}
