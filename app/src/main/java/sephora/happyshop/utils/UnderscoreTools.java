package sephora.happyshop.utils;

import com.github.underscore.Predicate;
import com.github.underscore._;

import java.util.List;

import sephora.happyshop.mvvm.Models.Product;

/**
 * Created by fadel on 26/8/17.
 */

public class UnderscoreTools {
    public static List<Product> filterProducts(List<Product> list, final String category) {
        return _.filter(list, new Predicate<Product>() {
            @Override
            public Boolean apply(Product arg) {
                return arg.getCategory().equals(category);
            }
        });
    }
}
