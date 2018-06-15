package com.azaless.cheerball.view.ui.team

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azaless.cheerball.R
import com.azaless.cheerball.databinding.FragmentTeamBinding
import com.azaless.cheerball.glide.GlideApp
import com.azaless.cheerball.glide.SvgSoftwareLayerSetter
import com.azaless.cheerball.util.InjectorUtils
import com.azaless.cheerball.view.adapter.PlayerListAdapter
import com.azaless.cheerball.view.model.Team
import com.azaless.cheerball.viewmodels.TeamViewModel
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions


class TeamFragment : Fragment() {
	private lateinit var viewDataBinding: FragmentTeamBinding
	private lateinit var teamViewModel: TeamViewModel

	private var teamId: Int = 0
	private lateinit var teamName: String
	private lateinit var mContext: Context


	private val requestBuilder: RequestBuilder<PictureDrawable>  by lazy {
		GlideApp.with(this)
			.`as`(PictureDrawable::class.java)
			.listener(SvgSoftwareLayerSetter())
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		teamId = requireNotNull(arguments).getInt(ARG_TEAM_ID)
		teamName = requireNotNull(arguments).getString(ARG_TEAM_NAME)

		viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_team, container, false)
		mContext = context ?: return viewDataBinding.root

		return viewDataBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val factory = InjectorUtils.provideTeamViewModelFactory(mContext, teamId, teamName)
		teamViewModel = ViewModelProviders.of(this, factory).get(TeamViewModel::class.java)
		teamViewModel.getTeam()
			.observe(this, Observer { result ->
				result?.let {
					showDetailFlag(it)
				}
			})

		teamViewModel.getFlagURL()
			.observe(this, Observer { result ->
				result?.let {
					showImgFlag(it)
				}
			})

		val playerListAdapter = PlayerListAdapter()
		view.findViewById<RecyclerView>(R.id.recyclerViewPlayer).adapter = playerListAdapter
		subscribeUi(playerListAdapter)
	}

	private fun showImgFlag(link: String) {
		viewDataBinding.apply {
			val imgOptions = RequestOptions()
				.fitCenter()
				.override(500, 250)

			requestBuilder.load(link)
				.apply(imgOptions)
				.into(imgFlag)
		}
	}

	private fun subscribeUi(adapter: PlayerListAdapter) {
//		viewModel.getPlants().observe(this, Observer { plants ->
//			if (plants != null) adapter.values = plants
//		})
	}

	private fun showDetailFlag(team: Team) {
		viewDataBinding.apply {
			textViewFlagName.text = team.name

//			val imgOptions = RequestOptions()
//				.fitCenter()
//				.override(500, 250)
//
//			Timber.e("link img : ${team.crestUrl}")
//			GlideApp.with(imgFlag.context)
//				.load(team.crestUrl)
//				.apply(imgOptions)
//				.into(imgFlag)
		}
	}

	companion object {
		const val ARG_TEAM_ID = "team_id"
		const val ARG_TEAM_NAME = "team_name"

		fun newInstance(teamId: Int, teamName: String): TeamFragment {
			val bundle = Bundle().apply {
				putInt(ARG_TEAM_ID, teamId)
				putString(ARG_TEAM_NAME, teamName)
			}
			return TeamFragment().apply { arguments = bundle }
		}
	}
}