package by.step.thoughts;

import androidx.fragment.app.Fragment;

import by.step.thoughts.fragment.BasketFragment;
import by.step.thoughts.fragment.ShopFragment;

public class FragmentFactory {
    public static Fragment createFromTag(final String tag) {

        if (tag.equals(ShopFragment.TAG)) {
            return new ShopFragment();
        }

        if (tag.equals(BasketFragment.TAG)) {
            return new BasketFragment();
        }

        return null;
    }
}
