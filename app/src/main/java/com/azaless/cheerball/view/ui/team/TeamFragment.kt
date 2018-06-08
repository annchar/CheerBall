package com.azaless.cheerball.view.ui.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azaless.cheerball.R

class TeamFragment: Fragment() {
//	private lateinit var viewModel: MainViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_team, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		//TODO: Change view model
//		viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
	}

	companion object {
		fun newInstance(): TeamFragment {
			return TeamFragment().apply {  }
		}
	}
}