package com.lukieoo.hiltexample.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.lukieoo.hiltexample.intent.Intent
import com.lukieoo.hiltexample.model.Cat
import com.lukieoo.hiltexample.repository.MainRepository
import com.lukieoo.hiltexample.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    val userIntent = Channel<Intent>(Channel.UNLIMITED)

    private val _dataState= MutableStateFlow<DataState>(DataState.Idle)

    val dataState: StateFlow<DataState>
        get() = _dataState
    init {
        setStateEvent()
    }
    fun setStateEvent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is Intent.GetBlogsEvent -> {
                        mainRepository.getCats()
                            .onEach {
                                _dataState.value = it
                            }
                            .launchIn(viewModelScope)
                    }

                    Intent.None -> {
                        // who cares
                    }
                }

            }


        }
    }

}




















