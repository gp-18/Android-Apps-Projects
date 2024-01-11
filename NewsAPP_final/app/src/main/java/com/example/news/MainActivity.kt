package com.example.news


import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.HORIZONTAL
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.intellij.lang.annotations.JdkConstants


class MainActivity : AppCompatActivity() , NewsItemClicked ,PostAdapter.OnItemClickListener{
    private  lateinit var mAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val posts:ArrayList<String> = ArrayList()

            posts.add("Health")
            posts.add("Sports")
            posts.add("Business")
            posts.add("Entertainment")
            posts.add("Technology")

        val horrecyclerView=findViewById<RecyclerView>(R.id.category)
        horrecyclerView.layoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        horrecyclerView.adapter=PostAdapter(posts,this)

        val recyclerView=findViewById<RecyclerView>(R.id.showing)
        recyclerView.layoutManager=LinearLayoutManager(this)
        fatchdata()
        mAdapter=NewsListAdapter(this)
        recyclerView.adapter=mAdapter
    }


    private fun fatchdata() {
        val url = "https://saurav.tech/NewsAPI/everything/cnn.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET ,
            url , null ,
            {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title") ,
                        newsJsonObject.getString("author") ,
                        newsJsonObject.getString("url") ,
                        newsJsonObject.getString("urlToImage") ,
                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            } ,
            {

            }
        )


        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override fun onItemClicked(item: News) {

        val builder =  CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }

    override fun onItemClick(position: Int) {
        if(position==0) {
            Toast.makeText(this , "OPENING PLEASE WAIT !!" , Toast.LENGTH_SHORT).show()
            val intent = Intent(this , HealthActivity::class.java)
            startActivity(intent)
        }
        else if(position==1) {
            Toast.makeText(this , "OPENING PLEASE WAIT !!" , Toast.LENGTH_SHORT).show()
            val intent = Intent(this , AnotherActivity::class.java)
            startActivity(intent)
        }

        else if(position==2) {
            Toast.makeText(this , "OPENING PLEASE WAIT !!" , Toast.LENGTH_SHORT).show()
            val intent = Intent(this , BusinessActivity::class.java)
            startActivity(intent)
        }

        else if(position==3) {
            Toast.makeText(this , "OPENING PLEASE WAIT !!" , Toast.LENGTH_SHORT).show()
            val intent = Intent(this , EntertainmentActivity::class.java)
            startActivity(intent)
        }

        else if(position==4) {
            Toast.makeText(this , "OPENING PLEASE WAIT !!" , Toast.LENGTH_SHORT).show()
            val intent = Intent(this , technologyActivity::class.java)
            startActivity(intent)
        }
    }
}