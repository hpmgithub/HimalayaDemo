package com.jash.himalayademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jash.himalayademo.fragments.CategoryFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new CategoryFragment()).commit();
    }
}
