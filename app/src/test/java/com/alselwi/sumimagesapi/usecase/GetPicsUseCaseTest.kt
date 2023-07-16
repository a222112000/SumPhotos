package com.alselwi.sumimagesapi.usecase

import com.alselwi.sumimagesapi.data.model.SumPicturesItem
import com.alselwi.sumimagesapi.domain.Repository
import com.alselwi.sumimagesapi.util.ResourcePics
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import java.lang.Exception

class GetPicsUseCaseTest {

    private  var getPicsUseCase = mockk<GetPicsUseCase>()

    private val repository = mockk<Repository>()
    private val dispatcher = TestCoroutineDispatcher()
    val mockFlow: Flow<ResourcePics<List<SumPicturesItem>>> = flow {
        emit(ResourcePics.Loading())
    }
    private val listPics = listOf(
        SumPicturesItem("", "http://example.com/image1.jpg", 3,"1","",2,true),
        SumPicturesItem("" ,"http://example.com/image2.jpg", 3,"2","",2,true),
        SumPicturesItem("", "http://example.com/image3.jpg", 43,"3","",2,true)
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        getPicsUseCase = GetPicsUseCase(repository)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test(expected = Exception::class)
    fun invokeTest()= runBlocking{
        getPicsUseCase.invoke()

        val mockFlow: Flow<ResourcePics<List<SumPicturesItem>>> = flow {

            emit(ResourcePics.Error("error"))
        }

        every { getPicsUseCase.invoke() } returns mockFlow

        verify { getPicsUseCase.invoke() }
    }

    @Test
    fun invokeTestNoException()= runBlocking{


        val mockFlow: Flow<ResourcePics<List<SumPicturesItem>>> = flow {

            emit(ResourcePics.Loading())
            emit(ResourcePics.Success(listPics))
        }
        getPicsUseCase.invoke()

       Assert.assertNotNull(getPicsUseCase.invoke())
    }
}