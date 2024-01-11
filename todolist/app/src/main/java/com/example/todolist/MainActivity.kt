package com.example.todolist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.model.UserData
import com.example.todolist.view.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private  lateinit var addsBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter:UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userList=ArrayList()
        addsBtn=findViewById(R.id.addingBtn)
        recv=findViewById(R.id.mRecycler)
        userAdapter=UserAdapter(this,userList)
        recv.layoutManager=LinearLayoutManager(this)
        recv.adapter=userAdapter
        addsBtn.setOnClickListener { addInfo() }
    }

    @SuppressLint("InflateParams" , "NotifyDataSetChanged")
    private fun addInfo() {
        val inflter= LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item,null)
        val userName=v.findViewById<EditText>(R.id.userName)
        val userNo=v.findViewById<EditText>(R.id.userNo)

        val addDialog=AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("ok"){
            dialog,_->
            val names=userName.text.toString()
            val numbers=userNo.text.toString()
            userList.add(UserData("Task:$names","Priority :$numbers"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding Information",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
            dailog,_->
            dailog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}