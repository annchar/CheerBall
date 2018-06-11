package com.azaless.cheerball.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.azaless.cheerball.R
import com.azaless.cheerball.view.model.PlayerModel

class PlayerListAdapter : RecyclerView.Adapter<PlayerListAdapter.ViewHolder>(){
	var values: List<PlayerModel> = ArrayList(0)
		set(items) {
			field = items
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(LayoutInflater.from(parent.context)
			.inflate(R.layout.list_item_player, parent, false))
	}

	override fun getItemCount() = values.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.apply {
			textViewName.text = values[position].name
			textViewPosition.text = values[position].position
			textViewNumber.text = values[position].jerseyNumber
			textViewAge.text = values[position].age
			textViewDateOfBirth.text = values[position].dateOfBirth
		}
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val textViewName: TextView = itemView.findViewById(R.id.textViewName)
		val textViewPosition: TextView = itemView.findViewById(R.id.textViewPosition)
		val textViewNumber: TextView = itemView.findViewById(R.id.textViewNumber)
		val textViewAge: TextView = itemView.findViewById(R.id.textViewAge)
		val textViewDateOfBirth: TextView = itemView.findViewById(R.id.textViewDateOfBirth)
	}
}