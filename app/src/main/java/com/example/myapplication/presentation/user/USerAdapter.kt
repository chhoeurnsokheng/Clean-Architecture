package com.example.myapplication.presentation.user

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Entity.ListUser
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.utils.extension.layoutInflater
import com.example.myapplication.utils.extension.loadImageUrl

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    var users = mutableListOf<ListUser>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(parent.context.layoutInflater, parent, false))
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = users[position]
        holder.bind(data)
    }
    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ListUser) {
            with(binding) {
                firstName.text = user.first_name
                lastName.text = user.last_name
                email.text = user.email
                imageview.loadImageUrl(user.avatar)
            }
        }
    }
    override fun getItemCount(): Int {
        return users.size
    }
}
