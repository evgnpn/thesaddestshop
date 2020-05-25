package by.step.thoughts.task;


import java.util.Date;
import java.util.List;

import by.step.thoughts.Constants;
import by.step.thoughts.data.AppDatabase;
import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.PurchaseItem;
import by.step.thoughts.entity.Purse;
import by.step.thoughts.entity.relation.BasketItemAndProduct;

public class PurchaseTask implements Runnable {

    private Listener listener;
    private final AppDatabase database;

    public PurchaseTask(AppDatabase database) {
        this.database = database;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private double getSum(List<BasketItemAndProduct> basketItemsAndProducts) {
        double sum = 0;
        for (BasketItemAndProduct basketItemAndProduct : basketItemsAndProducts)
            sum += basketItemAndProduct.product.price * basketItemAndProduct.basketItem.amount;
        return sum;
    }

    @Override
    public void run() {
        if (listener != null)
            listener.onStart();

        try {
            Purse purse = database.getPurseDao().getById(Constants.PURSE_ID);
            List<BasketItemAndProduct> basketItemsAndProducts =
                    database.getBasketItemDao().getBasketItemAndProducts();

            double purchaseSum = getSum(basketItemsAndProducts);

            if (purse.money < purchaseSum) {
                listener.onLackOfBalance(purse.money, purchaseSum);
                return;
            }

            Purchase purchase = new Purchase(new Date());
            long[] ids = database.getPurchaseDao().insert(purchase);
            long id = ids[0];

            for (BasketItemAndProduct basketItemAndProduct : basketItemsAndProducts) {
                PurchaseItem purchaseItem =
                        new PurchaseItem(basketItemAndProduct.basketItem.amount,
                                basketItemAndProduct.product.id, id);
                database.getPurchaseItemDao().insert(purchaseItem);
                database.getBasketItemDao().delete(basketItemAndProduct.basketItem);
                purse.money -=
                        basketItemAndProduct.product.price * basketItemAndProduct.basketItem.amount;
                database.getPurseDao().update(purse);
            }
        } catch (Exception e) {
            listener.onError();
        }

        if (listener != null)
            listener.onFinish();
    }

    public interface Listener {
        void onStart();

        void onLackOfBalance(double balance, double purchaseSum);

        void onError();

        void onFinish();
    }
}
