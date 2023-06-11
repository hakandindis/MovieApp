package org.hakandindis.movieapp.scene.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.hakandindis.movieapp.data.remote.model.popularmovie.MovieItem
import org.hakandindis.movieapp.databinding.RowMovieItemBinding
import org.hakandindis.movieapp.extension.loadCircleImage

interface MovieClickListener {
    fun onMovieClick(movieId: Int?)
}

class MovieAdapter(private val movieClickListener: MovieClickListener) :
    ListAdapter<MovieItem, MovieViewHolder>(MovieDiffUtil) {
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

    fun bind(movieItem: MovieItem?) {

        binding.model = movieItem

        with(binding) {
            root.setOnClickListener { movieClickListener.onMovieClick(movieItem?.id) }
        }
    }
}

object MovieDiffUtil : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem) =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem) = (oldItem == newItem)

}



