package com.azaless.cheerball.view.ui.allteam

import android.arch.lifecycle.Observer
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
import com.azaless.cheerball.databinding.FragmentTeamsBinding
import com.azaless.cheerball.util.InjectorUtils
import com.azaless.cheerball.view.adapter.TeamListAdapter
import com.azaless.cheerball.view.ui.team.TeamDetailFragment
import com.azaless.cheerball.viewmodels.TeamsViewModel

class TeamsFragment: Fragment() {
	private lateinit var viewDataBinding: FragmentTeamsBinding
	private lateinit var mTeamsViewModel: TeamsViewModel
	private lateinit var mContext: Context

	private var eventId: Int = 0

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		eventId = requireNotNull(arguments).getInt(TeamDetailFragment.ARG_TEAM_ID)

		viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_teams, container, false)
		mContext = context ?: return viewDataBinding.root
		return viewDataBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val factory = InjectorUtils.provideTeamsViewModelFactory(mContext, eventId)
		mTeamsViewModel = ViewModelProviders.of(this, factory).get(TeamsViewModel::class.java)

		val teamListAdapter = TeamListAdapter()
		view.findViewById<RecyclerView>(R.id.recyclerViewTeam).adapter = teamListAdapter
		subscribeUi(teamListAdapter)
	}

	private fun subscribeUi(adapter: TeamListAdapter) {
		mTeamsViewModel.getTeams()
			.observe(this, Observer { teams ->
				if (teams != null)
					teams.teams?.let {
						adapter.values = it
					}
			})
	}

	companion object {
		fun newInstance(eventId: Int): TeamsFragment {
			val bundle = Bundle().apply {
				putInt(TeamDetailFragment.ARG_TEAM_ID, eventId)
			}
			return TeamsFragment().apply { arguments = bundle }
		}
	}
}