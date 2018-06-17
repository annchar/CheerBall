package com.azaless.cheerball.view.ui.allteam

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azaless.cheerball.R
import com.azaless.cheerball.view.ui.team.TeamDetailActivity
import com.azaless.cheerball.view.ui.team.TeamDetailFragment
import kotlinx.android.synthetic.main.fragment_all_team.*
import timber.log.Timber

class AllTeamFragment: Fragment() {
//	private lateinit var viewModel: MainViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_all_team, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		//TODO: Change view model
//		viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
		textViewHead.setOnClickListener{
			Timber.e("click")
			val intent = Intent(view.context, TeamDetailActivity::class.java).apply {
				putExtra(TeamDetailFragment.ARG_TEAM_ID, 808)
				putExtra(TeamDetailFragment.ARG_TEAM_NAME, "Brazill")
			}
			startActivity(intent)
		}

	}

	companion object {
		fun newInstance(): AllTeamFragment {
			return AllTeamFragment().apply {  }
		}
	}
}