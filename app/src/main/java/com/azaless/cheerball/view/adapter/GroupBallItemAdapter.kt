package com.azaless.cheerball.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.azaless.cheerball.R
import com.azaless.cheerball.view.model.GroupDetail

class GroupBallItemAdapter : RecyclerView.Adapter<GroupBallItemAdapter.ViewHolder>(){
	var values: List<GroupDetail> = listOf()
		set(items) {
			field = items
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(LayoutInflater.from(parent.context)
			.inflate(R.layout.list_item_team_small, parent, false))
	}

	override fun getItemCount() = values.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.apply {
			textViewName.text = values[position].team
			BindingAdapters.loadImage(imgFlag, values[position].crestURI)
		}
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val imgFlag: ImageView = itemView.findViewById(R.id.imgFlag)
		val textViewName: TextView = itemView.findViewById(R.id.textViewName)
	}
}