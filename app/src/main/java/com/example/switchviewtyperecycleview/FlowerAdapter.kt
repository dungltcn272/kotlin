package com.example.switchviewtyperecycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class FlowerAdapter(private val mListFlower: List<Flower>) : RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val flower = mListFlower[position]
        return flower.getTypeDisplay()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val view: View = when (viewType) {
            Flower.TYPE_GRID -> LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
            Flower.TYPE_LIST -> LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
            Flower.TYPE_STAGGERED -> LayoutInflater.from(parent.context).inflate(R.layout.item_staggered, parent, false)
            else -> throw IllegalArgumentException("Invalid view type")
        }
        return FlowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        val flower = mListFlower[position]
        holder.imgFlower.setImageResource(flower.resourceImage)
    }

    override fun getItemCount(): Int {
        return mListFlower.size
    }

    class FlowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFlower: ImageView = itemView.findViewById(R.id.img_flower)

    }
}
