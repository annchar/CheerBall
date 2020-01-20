package com.azaless.cheerball.view.ui.groupsball

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.azaless.cheerball.R
import com.azaless.cheerball.databinding.FragmentGroupsBallBinding
import com.azaless.cheerball.util.InjectorUtils
import com.azaless.cheerball.view.adapter.GroupBallListAdapter
import com.azaless.cheerball.viewmodels.GroupsBallViewModel

class GroupsBallFragment: Fragment() {
	private lateinit var viewDataBinding: FragmentGroupsBallBinding
	private lateinit var mGroupsBallViewModel: GroupsBallViewModel
	private lateinit var mContext: Context

	private var eventId: Int = 0

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		eventId = requireNotNull(arguments).getInt(GroupsBallFragment.ARG_EVENT_ID)

		viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_groups_ball, container, false)
		mContext = context ?: return viewDataBinding.root
		return viewDataBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val factory = InjectorUtils.provideGroupsBallViewModelFactory(mContext, eventId)
		mGroupsBallViewModel = ViewModelProviders.of(this, factory).get(GroupsBallViewModel::class.java)

		val groupBallListAdapter = GroupBallListAdapter()
		view.findViewById<RecyclerView>(R.id.recyclerViewGroup).adapter = groupBallListAdapter
		subscribeUi(groupBallListAdapter)
	}

	private fun subscribeUi(adapter: GroupBallListAdapter) {
		mGroupsBallViewModel.getLeagueGroup()
			.observe(this, Observer { leagueGroup ->
				leagueGroup?.standings?.let {
					adapter.value = it
				}
			})

	}

	companion object {
		const val ARG_EVENT_ID = "event_id"

		fun newInstance(eventId: Int): GroupsBallFragment {
			val bundle = Bundle().apply {
				putInt(GroupsBallFragment.ARG_EVENT_ID, eventId)
			}
			return GroupsBallFragment().apply { arguments = bundle }
		}
	}
}