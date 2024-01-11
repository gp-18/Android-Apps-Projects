package com.example.project1birthdaywishes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText editText;
Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editname);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= editText.getEditableText().toString();
                Toast.makeText(getApplicationContext(), "Please Wait While We Are Generating Card : " +name, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,BirthdayCard.class);
                intent.putExtra("name" ,name);
                startActivity(intent);
            }
        });
    }
}