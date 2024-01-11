package com.example.todolist.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.model.UserData
import com.example.todolist.view.UserAdapter.UserViewHolder as UserViewHolder1
class UserAdapter(val c:Context,val userList:ArrayList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    inner class UserViewHolder(val v:View):RecyclerView.ViewHolder(v) {
        var name:TextView
        var nbNum:TextView
        var mMenus:ImageView
        init {
             name=v.findViewById<TextView>(R.id.mTitle)
            nbNum=v.findViewById<TextView>(R.id.mSubTitle)
            mMenus=v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener { popupMenus(it) }
        }

        @SuppressLint("DiscouragedPrivateApi" , "InflateParams" , "NotifyDataSetChanged")
        private fun popupMenus(v:View) {
            val position= userList[adapterPosition]
            val popupMenus=PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editText -> {
                        val v=LayoutInflater.from(c).inflate(R.layout.add_item,null)
                        val name=v.findViewById<EditText>(R.id.userName)
                        val number=v.findViewById<EditText>(R.id.userNo)
                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok"){
                                dialog,_->
                                position.userName=name.text.toString()
                                position.userMb=number.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(c , "Information is Edited" , Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("Cancel"){
                                dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    R.id.delete -> {
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Are You Sure You Want To Delete This Information")
                            .setPositiveButton("Yes"){
                                dialog,_->
                                userList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c , "User Information Deleted" , Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
            val popup=PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible=true
            val menu=popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): UserViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.list_items,parent,false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder , position: Int) {
        val newList=userList[position]
        holder.name.text=newList.userName
        holder.nbNum.text=newList.userMb
    }

    override fun getItemCount(): Int {
        return  userList.size
    }
}