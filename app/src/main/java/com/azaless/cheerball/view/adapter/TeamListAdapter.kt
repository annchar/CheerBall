package com.azaless.cheerball.view.adapter

import android.content.Intent
import android.graphics.drawable.PictureDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azaless.cheerball.R
import com.azaless.cheerball.util.glide.SvgSoftwareLayerSetter
import com.azaless.cheerball.util.AppUtil
import com.azaless.cheerball.view.model.Team
import com.azaless.cheerball.view.ui.teamdetail.TeamDetailActivity
import com.azaless.cheerball.view.ui.teamdetail.TeamDetailFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions

class TeamListAdapter : RecyclerView.Adapter<TeamListAdapter.ViewHolder>(){
	var values: List<Team> = ArrayList(0)
		set(items) {
			field = items
			notifyDataSetChanged()
		}

	private val onClickListener = View.OnClickListener { view ->
		val item = view.tag as Team
		val intent = Intent(view.context, TeamDetailActivity::class.java).apply {
			val link = item.link?.self?.href
			link?.let {
				val teamId = AppUtil().getTeamId(it)
				putExtra(TeamDetailFragment.ARG_TEAM_ID, teamId)
				putExtra(TeamDetailFragment.ARG_TEAM_NAME, item.name)
			}
		}
		view.context.startActivity(intent)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(LayoutInflater.from(parent.context)
			.inflate(R.layout.list_item_team_big, parent, false))
	}

	override fun getItemCount() = values.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.apply {
			textViewName.text = values[position].name

			val imgOptions = RequestOptions()
				.fitCenter()
				.override(imgFlag.width, imgFlag.height)
			requestBuilder.load(values[position].crestUrl)
				.apply(imgOptions)
				.into(imgFlag)

			with(itemView) {
				tag = values[position]
				setOnClickListener(onClickListener)
			}
		}
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val textViewName: TextView = itemView.findViewById(R.id.textViewName)
		val imgFlag: ImageView = itemView.findViewById(R.id.imgFlag)

		val requestBuilder: RequestBuilder<PictureDrawable>  by lazy {
			Glide.with(itemView.context)
				.`as`(PictureDrawable::class.java)
				.listener(SvgSoftwareLayerSetter())
		}
	}
}