package com.azaless.cheerball.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.azaless.cheerball.R
import com.azaless.cheerball.databinding.ListItemPlayerBinding
import com.azaless.cheerball.view.model.Player


class PlayerListAdapter : DataBindingAdapter<List<Player>, PlayerListAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val binding = DataBindingUtil.inflate<ListItemPlayerBinding>(layoutInflater, R.layout.list_item_player, parent, false)
		return ViewHolder(binding)
	}

	override fun getItemCount() = values?.size ?: 0

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		with(holder) {
			values?.let { values ->
				holder.bind(values[position])
			}
		}
	}

	class ViewHolder(private val binding: ListItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: Player) {
			binding.player = item
			binding.executePendingBindings()
		}
	}
}