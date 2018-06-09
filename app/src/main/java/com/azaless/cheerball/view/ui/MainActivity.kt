package com.azaless.cheerball.view.ui

import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.azaless.cheerball.R
import com.azaless.cheerball.view.ui.groupsball.GroupsBallFragment
import com.azaless.cheerball.view.ui.player.PlayerListFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	private lateinit var drawerLayout: DrawerLayout
	private lateinit var drawerToggle: ActionBarDrawerToggle

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		setupToolbar()
		setupNavigationDrawer()
	}

	private fun setupToolbar() {
		setSupportActionBar(findViewById(R.id.toolbar))
		supportActionBar?.run {
			setDisplayHomeAsUpEnabled(true)
			setHomeButtonEnabled(true)
		}
	}

	private fun setupNavigationDrawer() {
		drawerLayout = findViewById(R.id.drawer_layout)
		drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
		drawerLayout.addDrawerListener(drawerToggle)

		val navController = Navigation.findNavController(this, R.id.main_fragment)
		val navigationView = findViewById<NavigationView>(R.id.navigation_view)
		navigationView.setupWithNavController(navController)
		navigationView.setNavigationItemSelectedListener(this)
		navigationView.setCheckedItem(R.id.nav_home)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		if (drawerToggle.onOptionsItemSelected(item))
			return true

		return super.onOptionsItemSelected(item)
	}

	override fun onPostCreate(savedInstanceState: Bundle?) {
		super.onPostCreate(savedInstanceState)
		drawerToggle.syncState()
	}

	override fun onConfigurationChanged(newConfig: Configuration?) {
		super.onConfigurationChanged(newConfig)
		drawerToggle.onConfigurationChanged(newConfig)
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		val fragment : Fragment = when(item.itemId){
			R.id.nav_groups -> {
				GroupsBallFragment.newInstance()
			}
			R.id.nav_all_teams -> {
				PlayerListFragment.newInstance()
			}
			else -> {
				MainFragment.newInstance()
			}

		}

		fragment.let {
			supportFragmentManager.beginTransaction()
				.replace(R.id.main_fragment, fragment)
				.addToBackStack(null)
				.commit()
		}

		drawerLayout.closeDrawer(GravityCompat.START)
		return true
	}

}
