package com.azaless.cheerball.view.ui.teamdetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.azaless.cheerball.R
import com.azaless.cheerball.databinding.FragmentTeamDetailBinding
import com.azaless.cheerball.util.InjectorUtils
import com.azaless.cheerball.view.adapter.PlayerListAdapter
import com.azaless.cheerball.viewmodels.TeamDetailViewModel


class TeamDetailFragment : Fragment() {
	private lateinit var viewDataBinding: FragmentTeamDetailBinding
	private lateinit var teamDetailViewModel: TeamDetailViewModel

	private var teamId: Int = 0
	private lateinit var teamName: String

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		teamId = requireNotNull(arguments).getInt(ARG_TEAM_ID)
		teamName = requireNotNull(arguments).getString(ARG_TEAM_NAME)

		viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_team_detail, container, false)
		viewDataBinding.setLifecycleOwner(this)

		val appCompatActivity = requireActivity() as AppCompatActivity
		appCompatActivity.setSupportActionBar(viewDataBinding.detailToolbar)
		appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

		return viewDataBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val factory = InjectorUtils.provideTeamViewModelFactory(context!!, teamId, teamName)
		teamDetailViewModel = ViewModelProviders.of(this, factory).get(TeamDetailViewModel::class.java)
		viewDataBinding.viewModel = teamDetailViewModel

		teamDetailViewModel.adapter.value = PlayerListAdapter()
		val linearLayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
		linearLayoutManager.scrollToPosition(0)
		teamDetailViewModel.layoutManager.value = linearLayoutManager
	}

	override fun onDestroyView() {
		super.onDestroyView()
		teamDetailViewModel.disposable()
	}

	companion object {
		const val ARG_TEAM_ID = "team_id"
		const val ARG_TEAM_NAME = "team_name"

		fun newInstance(teamId: Int, teamName: String): TeamDetailFragment {
			val bundle = Bundle().apply {
				putInt(ARG_TEAM_ID, teamId)
				putString(ARG_TEAM_NAME, teamName)
			}
			return TeamDetailFragment().apply { arguments = bundle }
		}
	}
}