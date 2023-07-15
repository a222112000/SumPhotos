package com.alselwi.sumimagesapi.data

import com.alselwi.sumimagesapi.data.model.SumPicturesItem
import com.alselwi.sumimagesapi.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: ApiPictures) : Repository {
    override suspend fun getPics(): List<SumPicturesItem> {
        return api.getImages()
    }
}