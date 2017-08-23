package sephora.happyshop;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sephora.happyshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityBinding.setFragmentManager(getSupportFragmentManager());
    }
}
