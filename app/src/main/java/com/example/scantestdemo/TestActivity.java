package com.example.scantestdemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView tv_test = findViewById(R.id.tv_test);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.get("name").toString();
        tv_test.setText(name);

    }
}
