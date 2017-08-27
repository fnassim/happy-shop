package sephora.happyshop.ui.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import sephora.happyshop.R;
import sephora.happyshop.application.HappyShopApplication;
import sephora.happyshop.databinding.ActivityMainBinding;
import sephora.happyshop.di.components.DaggerMainActivityComponent;
import sephora.happyshop.di.components.MainActivityComponent;
import sephora.happyshop.di.modules.MainActivityModule;

public class MainActivity extends AppCompatActivity {
    private static MainActivityComponent mComponent;
    @Inject
    protected ActivityMainBinding mainActivityBinding;
    @Inject
    protected SharedPreferences sharedPreferences;

    public static MainActivityComponent getActivityComponent() {
        return mComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .applicationComponent(HappyShopApplication.getApp().getApplicationComponent())
                .build();
        mComponent.inject(this);
        mainActivityBinding.setFragmentManager(getSupportFragmentManager());
        mainActivityBinding.setCartCt(sharedPreferences.getInt("cartArticlesNb", 0));
    }
}
