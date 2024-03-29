package com.app.yubotest.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.yubotest.di.YuboTest
import com.app.yubotest.model.User
import com.app.yubotest.yubotest.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_view_list_item_layout.view.*

class UserRecyclerAdapter(val context :Context, val items : List<User>) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_view_list_item_layout, p0, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: UserViewHolder, p1: Int) {
        p0.bind(items.get(p1))
        Picasso.with(context)
            .load(items.get(p1).photos?.get(0)?.url)
            .placeholder(R.drawable.profile_placeholder)
            .into(p0.picture)
    }

}

class UserViewHolder constructor(v: View) : RecyclerView.ViewHolder(v) {

    var picture = itemView.picture
    fun bind(user : User){
        itemView.info.text = user.info
        itemView.location.text = user.location
        itemView.emojii.text = user.emojis?.joinToString()
    }
}