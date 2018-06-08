package com.azaless.cheerball.view.ui

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.azaless.cheerball.R

class MainActivity : AppCompatActivity() {
	private lateinit var drawerLayout: DrawerLayout
	private lateinit var drawerToggle: ActionBarDrawerToggle

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		if (savedInstanceState == null)
			supportFragmentManager.beginTransaction()
				.add(R.id.main_fragment, MainFragment.newInstance())
				.commit()

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

		// TODO: Set navigator
//		val navController = Navigation.findNavController(this, R.id.main_fragment)
//		findViewById<NavigationView>(R.id.navigation_view).setupWithNavController(navController)
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
}
