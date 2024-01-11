package com.example.project1birthdaywishes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BirthdayCard extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_card);
        String name = getIntent().getStringExtra("name");
        textView=findViewById(R.id.textView);
        textView.setText("Happy Birthday :" + name);

    }
}