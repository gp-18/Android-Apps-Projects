package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button=findViewById<Button>(R.id.button)
            button.setOnClickListener {
            Toast.makeText(this,"Please Wait...",Toast.LENGTH_SHORT).show()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("LOGIN")
            builder.setMessage("ARE YOU SURE YOU WANT TO LOGIN")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes"){dialogInterface, which ->
                Toast.makeText(applicationContext,"CLICKED YES....SIGNING IN",Toast.LENGTH_LONG).show()
                val intent=Intent(this,MainActivity2::class.java)
                startActivity(intent)

            }
            builder.setNegativeButton("No",null)

            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("CLOSE APP")
        builder.setMessage("ARE YOU SURE YOU WANT TO CLOSE APP")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            Toast.makeText(applicationContext,"CLICKED YES....CLOSING APP",Toast.LENGTH_LONG).show()
           super.onBackPressed()
        }
        builder.setNegativeButton("No",null)

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

}