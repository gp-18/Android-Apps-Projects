package com.example.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener:NewsItemClicked) : Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.news_rv_items,parent,false)
        val viewHolder=NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder , position: Int) {
        val currentItem=items[position]
        holder.title_view.text=currentItem.title
        holder.author.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.image_view)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<News>)
    {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : ViewHolder(itemView)
{
    val title_view = itemView.findViewById<TextView>(R.id.newsheading)
    val image_view:ImageView=itemView.findViewById(R.id.ivnewsimage)
    val author:TextView=itemView.findViewById(R.id.subheading)

}
interface NewsItemClicked{
    fun onItemClicked(item:News)
}