package org.hakandindis.movieapp.scene.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.hakandindis.movieapp.data.remote.model.people.People
import org.hakandindis.movieapp.databinding.RowPeopleItemBinding


@BindingAdapter("people_list_items", "people_list_click_listener")
fun mediaOverviewDocumentRecycler(
    recyclerView: RecyclerView,
    listItems: List<People>?,
    listener: PeopleClickListener
) {
    getOrCreateAdapter(
        recyclerView = recyclerView,
        listener = listener
    ).submitList(listItems.orEmpty().toMutableList())
}

private fun getOrCreateAdapter(
    recyclerView: RecyclerView,
    listener: PeopleClickListener
): PeopleAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is PeopleAdapter) {
        recyclerView.adapter as PeopleAdapter
    } else {
        val adapter = PeopleAdapter(listener = listener)
        recyclerView.adapter = adapter
        adapter
    }
}

class PeopleAdapter(private val listener: PeopleClickListener) :
    ListAdapter<People, PeopleViewHolder>(PeopleViewDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowPeopleItemBinding.inflate(inflater, parent, false)
        return PeopleViewHolder(binding, listener)
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

interface PeopleClickListener {
    fun onPeopleClick(peopleId: Int?)
}