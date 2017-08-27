package sephora.happyshop.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sephora.happyshop.mvvm.Models.ProductObject;
import sephora.happyshop.mvvm.Models.Products;

// Retrofit api client interface
public interface ApiService {
    @GET("products.json")
    Observable<Products> getProducts();

    @GET("products/{id}.json")
    Observable<ProductObject> getProduct(@Path("id") String user);
}
