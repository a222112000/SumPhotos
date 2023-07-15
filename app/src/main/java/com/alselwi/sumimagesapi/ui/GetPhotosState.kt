package com.alselwi.sumimagesapi.ui

import com.alselwi.sumimagesapi.data.model.SumPicturesItem

data class GetPhotosState(
    val isLoading: Boolean = false,
    var photos: List<SumPicturesItem>? = null,
    val error: String = ""
)
