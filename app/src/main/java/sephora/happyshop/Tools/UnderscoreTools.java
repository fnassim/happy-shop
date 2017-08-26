package sephora.happyshop.Tools;

import com.github.underscore.Predicate;
import com.github.underscore._;

import java.util.List;

import sephora.happyshop.MVVM.Models.Product;

/**
 * Created by fadel on 26/8/17.
 */

public class UnderscoreTools {
    public static List<Product> filterProducts(List<Product> list, final String category) {
        return _.filter(list, new Predicate<Product>() {
            @Override
            public Boolean apply(Product arg) {
                if (arg.getCategory().equals(category))
                    return true;
                return false;
            }
        });
    }
}
