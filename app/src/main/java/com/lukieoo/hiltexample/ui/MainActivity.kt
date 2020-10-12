package com.lukieoo.hiltexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukieoo.hiltexample.Cat
import com.lukieoo.hiltexample.R
import com.lukieoo.hiltexample.util.AdapterCats
import com.lukieoo.hiltexample.util.DataState
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.StringBuilder
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var catsAdapter: AdapterCats

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManager.reverseLayout=true
        layoutManager.stackFromEnd=true
        recyclerViewCats.layoutManager = layoutManager
        recyclerViewCats.adapter = catsAdapter

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogsEvent)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Cat>> -> {
                    displayProgressBar(false)
//                    appendCatID(dataState.data)
                    catsAdapter.setCats(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
      //  if (message != null) text.text = message else text.text = "Unknown error."
    }

//    private fun appendCatID(cats: List<Cat>) {
//        val sb = StringBuilder()
//        for (cat in cats) {
//            sb.append(cat.id + "\n")
//        }
//        text.text = sb.toString()
//
//        Picasso.get().load(cats[cats.size - 1].url).into(cat_image)
//    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

}