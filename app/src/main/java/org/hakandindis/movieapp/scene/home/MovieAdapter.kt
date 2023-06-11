package org.hakandindis.movieapp.scene.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.hakandindis.movieapp.data.remote.model.popularmovie.Movie
import org.hakandindis.movieapp.databinding.RowMovieItemBinding

interface MovieClickListener {
    fun onMovieClick(movieId: Int?)
}

class MovieAdapter(private val movieClickListener: MovieClickListener) :
    ListAdapter<Movie, MovieViewHolder>(MovieDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowMovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding, movieClickListener)
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



