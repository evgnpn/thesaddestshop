package by.step.thoughts.task;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import by.step.thoughts.data.repository.BasketItemRepository;
import by.step.thoughts.data.repository.PurchaseRepository;
import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.Purchase;

public class TransferBasketItemsAsync extends AsyncTask<Void, Void, Void> {

    private Listener listener;
    private LifecycleOwner lifecycleOwner;
    private BasketItemRepository basketItemRepository;
    private PurchaseRepository purchaseRepository;

    public TransferBasketItemsAsync(LifecycleOwner lifecycleOwner, BasketItemRepository basketItemRepository, PurchaseRepository purchaseRepository) {
        this.basketItemRepository = basketItemRepository;
        this.purchaseRepository = purchaseRepository;
        this.lifecycleOwner = lifecycleOwner;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        LiveData<List<BasketItem>> basketItemsLiveData = basketItemRepository.getAll();
//
//        basketItemsLiveData.observe(lifecycleOwner, new Observer<List<BasketItem>>() {
//            @Override
//            public void onChanged(List<BasketItem> items) {
//
//            if (items != null) {
//                for (BasketItem basketItem : items) {
//                    purchaseRepository.insert(new Purchase[]{new Purchase(basketItem.productId, basketItem.amount)});
//                    basketItemRepository.delete(new BasketItem[]{basketItem});
//                }
//            }
//                basketItemsLiveData.removeObserver(this);
//            }
//        });

//        List<BasketItem> basketItems = basketItemRepository.getAll().getValue();
//
//        if (basketItems != null) {
//            for (BasketItem basketItem : basketItems) {
//                purchaseRepository.insert(new Purchase[]{new Purchase(basketItem.productId, basketItem.amount)});
//                basketItemRepository.delete(new BasketItem[]{basketItem});
//            }
//        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (listener != null)
            listener.onStart();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (listener != null)
            listener.onFinish();
    }

    public interface Listener {
        void onStart();

        void onFinish();
    }
}
