package com.app.yubotest.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.app.yubotest.yubotest.R
import com.meetic.shuffle.Shuffle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup
import android.widget.Toast
import com.app.yubotest.model.User
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction


class MainActivity : AppCompatActivity(), CardStackListener {

    lateinit var mainViewModel: MainViewModel
    lateinit var items: List<User>
    private val manager by lazy { CardStackLayoutManager(this, this) }

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
        mainViewModel.list()
        mainViewModel.liveResponseData.observeForever(object : Observer<List<User>?> {
            override fun onChanged(someData: List<User>?) {
                // do something with someData
                if (someData == null)
                    return
                items = someData
                val adapter = UserRecyclerAdapter(applicationContext, items)
                list.layoutManager = (manager)
                list.adapter = (adapter)
                mainViewModel.liveResponseData.removeObserver(this)
            }
        })
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (direction?.equals("right")!!)
            mainViewModel.like(items[(manager.topPosition)])
        else
            mainViewModel.dislike(items[(manager.topPosition)])
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardRewound() {
    }
}
