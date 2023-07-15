package com.alselwi.sumimagesapi.data

import com.alselwi.sumimagesapi.data.model.SumPicturesItem
import retrofit2.http.GET

interface ApiPictures {

    @GET("v2/list")
    suspend fun getImages():List<SumPicturesItem>
}