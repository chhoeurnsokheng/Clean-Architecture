package com.example.myapplication.presentation.mainactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.Entity.Movie
import com.example.myapplication.databinding.AdapterMovieBinding

class MainAdapter:RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    var movies = mutableListOf<Movie>()
    class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {}
    @JvmName("setMovies1")
    fun setMovies(movie :List<Movie>){
        this.movies=movie.toMutableList()
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie =movies[position]
        holder.binding.name.text=movie.name
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val binding= AdapterMovieBinding.inflate(inflater,parent,false)
        return MainViewHolder(binding)
    }
}