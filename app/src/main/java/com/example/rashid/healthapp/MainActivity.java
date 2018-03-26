package com.example.rashid.healthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView question_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question_container = findViewById(R.id.question_container);

        try {
            InputStream is = getAssets().open("questionnaire.json");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
