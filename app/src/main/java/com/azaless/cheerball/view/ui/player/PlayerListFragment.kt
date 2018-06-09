package com.azaless.cheerball.view.ui.player

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azaless.cheerball.R
import com.azaless.cheerball.databinding.FragmentPlayerBinding
import com.azaless.cheerball.viewmodels.PlayerListViewModel

class PlayerListFragment: Fragment() {
	private lateinit var viewDataBinding: FragmentPlayerBinding
	private lateinit var playerViewModel: PlayerListViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false)
		return viewDataBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		playerViewModel = ViewModelProviders.of(this).get(PlayerListViewModel::class.java)
		viewDataBinding.apply {
			viewModel = playerViewModel
		}
	}

	companion object {
		fun newInstance(): PlayerListFragment {
			return PlayerListFragment().apply {  }
		}
	}
}