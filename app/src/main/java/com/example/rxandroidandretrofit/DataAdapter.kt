package com.example.rxandroidandretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter (private val mListData : List<ObjectData>): RecyclerView.Adapter<DataAdapter.DataViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val objectData= mListData[position]
        if(objectData==null){
            return
        }
        holder.tvTitle.text = objectData.title
        holder.tvBody.text = objectData.body
    }

    override fun getItemCount(): Int {
        if (mListData!=null){
            return mListData.size
        }
        return 0
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvBody: TextView = itemView.findViewById(R.id.tv_body)
    }
}