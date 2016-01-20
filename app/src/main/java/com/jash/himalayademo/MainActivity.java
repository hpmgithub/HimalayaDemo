package com.jash.himalayademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jash.himalayademo.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new HomeFragment()).commit();
    }
}
