package com.example.myapplication

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Request.Method
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.util.Calendar.getInstance
import java.util.Currency.getInstance
import javax.sql.DataSource

class MainActivity2 : AppCompatActivity() {
    var currentimageurl: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        loadmeme()
        val next=findViewById<Button>(R.id.next)
        next.setOnClickListener {
            loadmeme()
        }
        val share=findViewById<Button>(R.id.share)
        share.setOnClickListener {
          val intent=Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"HEY CHECK THIS NEW MEME FROM MEMEZY APP CREATED BY PG18 $currentimageurl")
            val chooser=Intent.createChooser(intent,"MEMZY APP SHARING THE LINK")
            startActivity(chooser)
        }
    }
    private fun loadmeme()
    {
        val progressBar=findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility= View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                currentimageurl=response.getString("url")
                progressBar.visibility= View.GONE
                val memeimage=findViewById<ImageView>(R.id.memeimage)
                Glide.with(this).load(currentimageurl).listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException? ,
                        model: Any? ,
                        target: Target<Drawable>? ,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility= View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable? ,
                        model: Any? ,
                        target: Target<Drawable>? ,
                        dataSource: com.bumptech.glide.load.DataSource? ,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility= View.GONE
                        return false
                    }

                }).into(memeimage)
            } ,
            { error ->
                Toast.makeText(this,"Something Went Wrong..!",Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(jsonObjectRequest)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.logoutdone -> {
                Toast.makeText(this,"LOGGING OUT ...PLEASE WAIT!!",Toast.LENGTH_SHORT).show()
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}