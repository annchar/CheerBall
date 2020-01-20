package com.azaless.cheerball.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.azaless.cheerball.R
import com.azaless.cheerball.databinding.FragmentMainBinding
import com.azaless.cheerball.viewmodels.MainViewModel

class MainFragment: Fragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
		val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)
			.apply {
				mainViewModel.initTime()
				viewModel = mainViewModel
//			setLifecycleOwner(this@PlantDetailFragment)
			}
		return binding.root
	}

	companion object {
		fun newInstance(): MainFragment {
			return MainFragment().apply {  }
		}
	}
}