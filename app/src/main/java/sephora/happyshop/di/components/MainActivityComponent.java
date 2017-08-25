package sephora.happyshop.di.components;

import dagger.Component;
import sephora.happyshop.Activities.MainActivity;
import sephora.happyshop.databinding.ActivityMainBinding;
import sephora.happyshop.di.modules.MainActivityModule;
import sephora.happyshop.di.scopes.MainActivityScope;

/**
 * Created by fadel on 25/8/17.
 */

@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    ActivityMainBinding activityMainBinding();

    void inject(MainActivity activity);
}
