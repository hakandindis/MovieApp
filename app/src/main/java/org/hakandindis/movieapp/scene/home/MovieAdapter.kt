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
fun mediaOverviewDocumentRecycler(
    recyclerView: RecyclerView,
    listItems: List<Movie>?,
    listener: MovieClickListener
) {
    getOrCreateAdapter(
        recyclerView = recyclerView,
        listener = listener
    ).submitList(listItems.orEmpty().toMutableList())
}

private fun getOrCreateAdapter(
    recyclerView: RecyclerView,
    listener: MovieClickListener
): MovieAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is MovieAdapter) {
        recyclerView.adapter as MovieAdapter
    } else {
        val adapter = MovieAdapter(listener = listener)
        recyclerView.adapter = adapter
        adapter
    }
}


class MovieAdapter(private val listener: MovieClickListener) :
    ListAdapter<Movie, MovieViewHolder>(MovieDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowMovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(currentList[position])
}


class MovieViewHolder(
    private val binding: RowMovieItemBinding,
    private val movieClickListener: MovieClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie?) {

        binding.model = movie

        with(binding) {
            root.setOnClickListener { movieClickListener.onMovieClick(movie?.id) }
        }
    }
}

object MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = (oldItem == newItem)

}

interface MovieClickListener {
    fun onMovieClick(movieId: Int?)
}



