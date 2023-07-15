package com.alselwi.sumimagesapi.data.model

data class SumPicturesItem(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int,
    var isFavor: Boolean
)