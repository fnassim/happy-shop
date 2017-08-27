package sephora.happyshop.mvvm.DataBindingHelpers;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by fadel on 25/8/17.
 */

// This helper attaches the image to the desired view.
public final class ImageBindingHelper {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Picasso.with(context).load(url).into(imageView);
    }
}
