package com.example.news

import android.content.Intent
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init


class PostAdapter(val posts:ArrayList<String>,private val listener:OnItemClickListener): RecyclerView.Adapter<PostAdapter.ViewHolder>()
{

       inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
       View.OnClickListener{
            val data: TextView = itemView.findViewById(R.id.idtvcatogery)

            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                val position:Int=adapterPosition
                if(position!=RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): PostAdapter.ViewHolder {
        val view:View= LayoutInflater.from(parent.context).inflate(R.layout.catogery_horizontal_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder , position: Int)  {
        holder.data.text=posts[position]

    }

    override fun getItemCount(): Int {
        return posts.size
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}