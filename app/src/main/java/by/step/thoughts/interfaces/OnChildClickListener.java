package by.step.thoughts.interfaces;

import java.util.function.BiConsumer;

import by.step.thoughts.entity.Category;
import by.step.thoughts.entity.Product;

public interface OnChildClickListener {
    void accept(Category t, Product u);
}
