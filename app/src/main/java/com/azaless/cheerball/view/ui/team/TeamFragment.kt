package com.azaless.cheerball.view.ui.team

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azaless.cheerball.R
import com.azaless.cheerball.databinding.FragmentTeamBinding
import com.azaless.cheerball.util.InjectorUtils
import com.azaless.cheerball.view.adapter.PlayerListAdapter
import com.azaless.cheerball.viewmodels.TeamViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class TeamFragment: Fragment() {
	private lateinit var viewDataBinding: FragmentTeamBinding
	private lateinit var teamViewModel: TeamViewModel

	private lateinit var teamId: String
	private lateinit var teamName: String
	private lateinit var mContext: Context


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		teamId = requireNotNull(arguments).getString(ARG_TEAM_ID)
		teamName = requireNotNull(arguments).getString(ARG_TEAM_NAME)

		viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_team, container, false)
		mContext = context?: return viewDataBinding.root

		return viewDataBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val factory = InjectorUtils.provideTeamViewModelFactory(mContext, "808", "Brazill")
		teamViewModel = ViewModelProviders.of(this, factory).get(TeamViewModel::class.java)
		viewDataBinding.apply {

			textViewFlagName.text = teamName
			Glide.with(imgFlag.context)
				.load(teamViewModel.getFlagURL())
				.transition(DrawableTransitionOptions.withCrossFade())
				.into(imgFlag)
		}

		val playerListAdapter = PlayerListAdapter()
		view.findViewById<RecyclerView>(R.id.recyclerViewPlayer).adapter = playerListAdapter
		subscribeUi(playerListAdapter)
	}

	private fun subscribeUi(adapter: PlayerListAdapter) {
//		viewModel.getPlants().observe(this, Observer { plants ->
//			if (plants != null) adapter.values = plants
//		})
	}

	companion object {
		const val ARG_TEAM_ID = "team_id"
		const val ARG_TEAM_NAME = "team_name"

		fun newInstance(teamId: String, teamName: String): TeamFragment {
			val bundle = Bundle().apply {
				putString(ARG_TEAM_ID, teamId)
				putString(ARG_TEAM_NAME, teamName)
			}
			return TeamFragment().apply { arguments = bundle }
		}
	}
}