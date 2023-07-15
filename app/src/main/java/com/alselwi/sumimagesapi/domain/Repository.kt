package com.alselwi.sumimagesapi.domain

import com.alselwi.sumimagesapi.data.model.SumPicturesItem

interface Repository {

    suspend fun getPics():List<SumPicturesItem>
}