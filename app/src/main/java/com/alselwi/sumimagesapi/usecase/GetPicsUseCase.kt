package com.alselwi.sumimagesapi.usecase

import com.alselwi.sumimagesapi.data.model.SumPicturesItem
import com.alselwi.sumimagesapi.domain.Repository
import com.alselwi.sumimagesapi.util.ResourcePics
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetPicsUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<ResourcePics<List<SumPicturesItem>>> = flow {
        try {
            emit(ResourcePics.Loading())
            val pics = repository.getPics()
            emit(ResourcePics.Success(pics))
        }catch (e: Exception){
            emit(ResourcePics.Error(e.localizedMessage ?: "something went wrong"))
        }
    }
}