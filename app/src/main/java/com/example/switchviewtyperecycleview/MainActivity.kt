package com.example.switchviewtyperecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var mRcvFlower : RecyclerView
    private lateinit var mFlowerAdapter: FlowerAdapter

    private lateinit var mListFlower : List<Flower>

    private lateinit var mGridLayoutManager: GridLayoutManager
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    private lateinit var mStaggeredGridLayoutManager: StaggeredGridLayoutManager
    private var mCurrentTypeDisplay =Flower.TYPE_GRID
    private lateinit var mMenu : Menu
    private var mCurrentPosition :Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRcvFlower =findViewById(R.id.rev_flower)
        mGridLayoutManager = GridLayoutManager(this, 2)
        mLinearLayoutManager = LinearLayoutManager(this)
        mStaggeredGridLayoutManager =StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        mRcvFlower.layoutManager =mGridLayoutManager

        mListFlower=getListFlower()
        setTypeDisplayRecycleView(mCurrentTypeDisplay)
        mFlowerAdapter= FlowerAdapter(mListFlower)
        mRcvFlower.adapter = mFlowerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        mMenu =menu!!
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id :Int = item.itemId
        if(id==R.id.action_menu){
            setCurrentPosition()
            onClickChangeTypeDisplay()
        }
        return true
    }

    private fun onClickChangeTypeDisplay() {
        when (mCurrentTypeDisplay) {
            Flower.TYPE_GRID -> {
                setTypeDisplayRecycleView(Flower.TYPE_LIST)
                mRcvFlower.layoutManager = mLinearLayoutManager
            }
            Flower.TYPE_LIST -> {
                setTypeDisplayRecycleView(Flower.TYPE_STAGGERED)
                mRcvFlower.layoutManager =mStaggeredGridLayoutManager
            }
            Flower.TYPE_STAGGERED -> {
                setTypeDisplayRecycleView(Flower.TYPE_GRID)
                mRcvFlower.layoutManager =mGridLayoutManager
            }
        }
        mFlowerAdapter.notifyDataSetChanged()
        setIconMenu()
        mRcvFlower.scrollToPosition(mCurrentPosition)
    }

    private fun setIconMenu() {
        when(mCurrentTypeDisplay){
            Flower.TYPE_GRID -> mMenu.getItem(0).setIcon(R.drawable.ic_list)
            Flower.TYPE_LIST -> mMenu.getItem(0).setIcon(R.drawable.ic_staggered)
            Flower.TYPE_STAGGERED -> mMenu.getItem(0).setIcon(R.drawable.ic_grid)
        }
    }

    private fun setTypeDisplayRecycleView(typeDisplay: Int) {
        if (mListFlower.isEmpty()) {
            return
        }
        for (flower in mListFlower) {
            flower.setTypeDisplay(typeDisplay)
            mCurrentTypeDisplay=typeDisplay
        }
    }

    private fun setCurrentPosition(){
        val layoutManager: RecyclerView.LayoutManager? = mRcvFlower.layoutManager
        when(mCurrentTypeDisplay){
            Flower.TYPE_GRID -> mCurrentPosition =(layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
            Flower.TYPE_LIST -> mCurrentPosition =(layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            Flower.TYPE_STAGGERED -> {
                val tempPosition = IntArray(2)
                (layoutManager as StaggeredGridLayoutManager).findFirstVisibleItemPositions(tempPosition)
                mCurrentPosition=tempPosition[0]
            }
        }
    }

    private fun getListFlower(): List<Flower> {
        val list : MutableList<Flower> = ArrayList()
        list.add(Flower(R.drawable.img_flower_1))
        list.add(Flower(R.drawable.img_flower_2))
        list.add(Flower(R.drawable.img_flower_3))
        list.add(Flower(R.drawable.img_flower_4))
        list.add(Flower(R.drawable.img_flower_5))
        list.add(Flower(R.drawable.img_flower_6))
        list.add(Flower(R.drawable.img_flower_7))
        list.add(Flower(R.drawable.img_flower_8))
        list.add(Flower(R.drawable.img_flower_9))
        list.add(Flower(R.drawable.img_flower_10))
        return list
    }
}