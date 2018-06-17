/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.azaless.cheerball.view.ui.team

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.azaless.cheerball.R

class TeamDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        if (savedInstanceState == null) {
            val teamId = intent.getIntExtra(TeamDetailFragment.ARG_TEAM_ID, 0)
            val teamName = intent.getStringExtra(TeamDetailFragment.ARG_TEAM_NAME)
            val fragment = TeamDetailFragment.newInstance(teamId, teamName)
            supportFragmentManager.beginTransaction()
                    .add(R.id.team_detail_container, fragment)
                    .commit()
        }
    }

    // According to the Principles of Navigation*, Up and Back should function identically when the
    // back button does not exit the app.
    // * https://developer.android.com/topic/libraries/architecture/navigation/navigation-principles
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
