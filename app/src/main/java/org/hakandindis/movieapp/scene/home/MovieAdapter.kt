package org.hakandindis.movieapp.scene.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.hakandindis.movieapp.data.remote.model.popularmovie.Movie
import org.hakandindis.movieapp.databinding.RowMovieItemBinding


@BindingAdapter("movie_list_items", "movie_list_click_listener")
fun mediaOverviewDocumentRecycler(recyclerView: RecyclerView, listItems: List<Movie>?, listener: MovieClickListener) {
    getOrCreateAdapter(recyclerView = recyclerView, listener = listener).submitList(listItems.orEmpty().toMutableList())
}

private fun getOrCreateAdapter(recyclerView: RecyclerView, listener: MovieClickListener): MovieAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is MovieAdapter) {
        recyclerView.adapter as MovieAdapter
    } else {
        val adapter = MovieAdapter(listener = listener)
        recyclerView.adapter = adapter
        adapter
    }
}


class MovieAdapter(private val listener: MovieClickListener) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(movieDiffUtil) {

    companion object {
        val movieDiffUtil = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = (oldItem.id == newItem.id)
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = (oldItem == newItem)
        }
    }

    inner class MovieViewHolder(val binding: RowMovieItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowMovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.model = currentList[position]
        holder.binding.root.setOnClickListener { listener.onMovieClick(currentList[position].id) }
    }
}

interface MovieClickListener {
    fun onMovieClick(movieId: Int?)
}



