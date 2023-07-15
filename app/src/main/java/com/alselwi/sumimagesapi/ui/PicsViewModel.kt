package com.alselwi.sumimagesapi.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alselwi.sumimagesapi.usecase.GetPicsUseCase
import com.alselwi.sumimagesapi.util.ResourcePics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PicsViewModel @Inject constructor(private val getPicsUseCase: GetPicsUseCase) : ViewModel() {

    private val _pics = mutableStateOf(GetPhotosState())
    val pics : State<GetPhotosState> = _pics

    init {
        fetchPics()
    }

    private fun fetchPics(){
        getPicsUseCase().onEach {
            when(it){
                is ResourcePics.Success ->{
                    _pics.value = GetPhotosState(photos = it.data)
                }
                is ResourcePics.Error ->{
                    _pics.value = GetPhotosState(error = it.message ?: "Error occur")
                }
                is ResourcePics.Loading ->{
                    _pics.value = GetPhotosState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

}