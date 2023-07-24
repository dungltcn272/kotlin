package com.example.databindingtutorial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingtutorial.databinding.ItemUserBinding

class UserAdapter(private val mListUser: List<UserViewModel>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemUserBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemUserBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userViewModel = mListUser[position]
        holder.bind(userViewModel)
    }

    override fun getItemCount(): Int {
        return mListUser.size
    }

    class UserViewHolder(private val itemUserBinding: ItemUserBinding) : RecyclerView.ViewHolder(itemUserBinding.root) {
        fun bind(userViewModel: UserViewModel) {
            itemUserBinding.userViewModel = userViewModel
            // You can also add any other view-related setup here if needed
        }
    }
}
