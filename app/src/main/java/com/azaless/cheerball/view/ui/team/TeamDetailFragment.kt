package com.azaless.cheerball.view.ui.team

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
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
import com.azaless.cheerball.viewmodels.TeamDetailViewModel
import com.bumptech.glide.RequestBuilder
import timber.log.Timber


class TeamDetailFragment : Fragment() {
	private lateinit var viewDataBinding: FragmentTeamBinding
	private lateinit var teamDetailViewModel: TeamDetailViewModel

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


		val appCompatActivity = requireActivity() as AppCompatActivity
		appCompatActivity.setSupportActionBar(viewDataBinding.detailToolbar)

		// Show the Up button in the action bar.
		appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

		return viewDataBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val playerListAdapter = PlayerListAdapter()
		view.findViewById<RecyclerView>(R.id.recyclerViewPlayer).adapter = playerListAdapter
		subscribeUi(playerListAdapter)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		teamDetailViewModel.disposable()
	}

	private fun subscribeUi(adapter: PlayerListAdapter) {
		val factory = InjectorUtils.provideTeamViewModelFactory(mContext, teamId, teamName)
		teamDetailViewModel = ViewModelProviders.of(this, factory).get(TeamDetailViewModel::class.java)
		viewDataBinding.apply {
			viewModel = teamDetailViewModel
		}
//		teamDetailViewModel.getTeam()
//			.observe(this, Observer { result ->
//				result?.let {
//					showDetailFlag(it)
//
//				}
//			})
//
//		teamDetailViewModel.getFlagURL()
//			.observe(this, Observer { result ->
//				result?.let {
//					showImgFlag(it)
//				}
//			})

		teamDetailViewModel.getPlayers().observe(this, Observer { players ->
			Timber.e("result -> $players")
			if (players != null)
				adapter.values = players
		})

	}

//	private fun showDetailFlag(team: Team) {
//		viewDataBinding.apply {
//			textViewFlagName.text = team.name
//		}
//	}

//	private fun showImgFlag(link: String) {
//		viewDataBinding.apply {
//			val imgOptions = RequestOptions()
//				.fitCenter()
//				.override(500, 250)
//
//			requestBuilder.load(link)
//				.apply(imgOptions)
//				.into(imgFlag)
//		}
//	}

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