package com.lukieoo.hiltexample.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukieoo.hiltexample.model.Cat
import com.lukieoo.hiltexample.R
import com.lukieoo.hiltexample.intent.Intent
import com.lukieoo.hiltexample.ui.MainViewModel
import com.lukieoo.hiltexample.util.AdapterCats
import com.lukieoo.hiltexample.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment
constructor(

) : Fragment(R.layout.fragment_main) {

    private val TAG: String = "AppDebug"

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var catsAdapter: AdapterCats

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
//        viewModel.setStateEvent()



        val layoutManager =
            LinearLayoutManager(
                requireActivity().applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerViewCats.layoutManager = layoutManager
        recyclerViewCats.adapter = catsAdapter

        subscribeObservers()
        lifecycleScope.launch {
            viewModel.userIntent.send(Intent.GetBlogsEvent)
        }
    }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            viewModel.dataState.collect {
                when (it) {
                    is DataState.Success -> {
                        displayProgressBar(false)
//                    appendCatID(dataState.data)
                        catsAdapter.setCats(it.cats)
                    }
                    is DataState.Error -> {
                        displayProgressBar(false)
                        displayError(it.exception.message)
                    }
                    is DataState.Loading -> {
                        displayProgressBar(true)
                    }
                }
            }
        }
    }

    private fun displayError(message: String?) {
        //  if (message != null) text.text = message else text.text = "Unknown error."
    }


    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

}