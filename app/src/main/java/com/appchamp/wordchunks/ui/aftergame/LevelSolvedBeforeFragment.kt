/*
 * Copyright 2017 Julia Kozhukhovskaya
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appchamp.wordchunks.ui.aftergame

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appchamp.wordchunks.R
import com.appchamp.wordchunks.ui.levels.LevelsActivity
import com.appchamp.wordchunks.util.Constants
import kotlinx.android.synthetic.main.frag_level_solved_before.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor


class LevelSolvedBeforeFragment : LifecycleFragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(activity).get(AfterGameViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.frag_level_solved_before, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rlBackToLevels.setOnClickListener { backToLevelsActivity(viewModel.getPackId()) }

        setFunFact(viewModel.getFunFact())
    }

    private fun setFunFact(fact: String?) {
        tvFunFact.text = fact
    }

    /**
     * Back navigation. Navigates to LevelsActivity passing Pack id by the Intent.
     */
    private fun backToLevelsActivity(packId: String) {
        // Passing pack's id by the Intent.
        startActivity(activity.intentFor<LevelsActivity>(Constants.EXTRA_PACK_ID to packId).clearTop())
        activity.finish()
        activity.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
}
