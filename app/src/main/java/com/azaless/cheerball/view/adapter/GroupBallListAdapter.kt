package com.azaless.cheerball.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.azaless.cheerball.R
import com.azaless.cheerball.view.model.GroupDetail
import kotlin.properties.Delegates

class GroupBallListAdapter : RecyclerView.Adapter<GroupBallListAdapter.ViewHolder>() {

//	var value: Map<String, List<GroupDetail>> = mutableMapOf()
//		set(group) {
//			field = group
//			notifyDataSetChanged()
//		}

	var value: Map<String, List<GroupDetail>> by Delegates.observable(mapOf()) { _, _, _ ->
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(LayoutInflater.from(parent.context)
			.inflate(R.layout.list_item_group, parent, false))
	}

	override fun getItemCount() = value.keys.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		with(holder) {
			value.keys.filterIndexed { index, _ -> index == position }
				.firstOrNull()?.let { key ->
					textViewGroupName.text = key.toUpperCase()
					val groupList = value[key]
					val groupBallItemAdapter = GroupBallItemAdapter()
					groupBallItemAdapter.values = groupList ?: listOf()
					recyclerViewGroupTeam.adapter = groupBallItemAdapter
				}
		}
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val textViewGroupName: TextView = itemView.findViewById(R.id.textViewGroupName)
		val recyclerViewGroupTeam: RecyclerView = itemView.findViewById(R.id.recyclerViewGroupTeam)
	}
}