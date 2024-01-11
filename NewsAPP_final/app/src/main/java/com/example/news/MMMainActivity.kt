package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MMMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mmmain)
        val button:Button=findViewById(R.id.button)
        button.setOnClickListener {
            Toast.makeText(this , "OPENING PLEASE WAIT !!" , Toast.LENGTH_SHORT).show()
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
    }
}