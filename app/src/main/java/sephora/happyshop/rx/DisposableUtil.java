package sephora.happyshop.rx;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by fadel on 27/8/17.
 */


// This class is responsible of unsubscribing the Observers in order to avoid memory leaks and crashes.
public class DisposableUtil {

    private static CompositeDisposable compositeDisposable;

    private DisposableUtil() {
    }

    public static void add(Disposable disposable) {
        getCompositeDisposable().add(disposable);
    }

    public static void dispose() {
        getCompositeDisposable().dispose();
    }

    private static CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }
}