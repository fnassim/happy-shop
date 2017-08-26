package sephora.happyshop.Api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sephora.happyshop.MVVM.Models.Product;
import sephora.happyshop.MVVM.Models.ProductObject;
import sephora.happyshop.MVVM.Models.Products;

// Retrofit api client interface
public interface ApiService {
    @GET("products.json")
    Observable<Products> getProducts();

    @GET("products/{id}.json")
    Observable<ProductObject> getProduct(@Path("id") String user);
}
