package com.app.yubotest.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.app.yubotest.yubotest.R
import com.meetic.shuffle.Shuffle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup
import com.app.yubotest.model.User
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager





class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViewModel(){
        this.mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        this.mainViewModel.let { lifecycle.addObserver(it) }
    }

    private fun initViews(){
        val users = mainViewModel.list()
        val manager = CardStackLayoutManager(this)
        val adapter = UserRecyclerAdapter(this, users?.value!!)
        list.layoutManager = (manager!!)
        list.adapter = (adapter)
    }
}
