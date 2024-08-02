package com.example.teste2.profile_feature


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teste2.R


class UserProfile : AppCompatActivity() {


    val listaPost = arrayListOf(
        "Item A",
        "Item B",
        "Item C",
        "Item D",
        "Item D",
        "Item C",
        )



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(com.example.teste2.R.layout.activity_user_profile)

        val recycleView_userPost = findViewById<RecyclerView>(R.id.recycler_view_posts)
        recycleView_userPost.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recycleView_userPost.setHasFixedSize(true)

        //

        val adapter = PostAdapter(this,listaPost)
        recycleView_userPost.adapter = adapter


    }

}

class PostAdapter(private val context: Context, private val posts: List<String>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemList = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false)
        val holder = PostViewHolder(itemList)
        return holder
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

    }
}