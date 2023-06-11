package org.hakandindis.movieapp.scene.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.hakandindis.movieapp.data.remote.model.people.People
import org.hakandindis.movieapp.databinding.RowPeopleItemBinding

interface PeopleClickListener {
    fun onPeopleClick(peopleId: Int?)
}

class PeopleAdapter(private val peopleClickListener: PeopleClickListener) :
    ListAdapter<People, PeopleViewHolder>(PeopleViewDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowPeopleItemBinding.inflate(inflater, parent, false)
        return PeopleViewHolder(binding, peopleClickListener)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) =
        holder.bind(currentList[position])
}

class PeopleViewHolder(
    private val binding: RowPeopleItemBinding,
    private val peopleClickListener: PeopleClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(people: People) {
        with(binding) {
            binding.model = people

            root.setOnClickListener { peopleClickListener.onPeopleClick(people.id) }
        }
    }
}

object PeopleViewDiffUtil : DiffUtil.ItemCallback<People>() {
    override fun areItemsTheSame(oldItem: People, newItem: People) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: People, newItem: People) = oldItem == newItem
}