package com.alselwi.sumimagesapi.data

import com.alselwi.sumimagesapi.data.model.SumPicturesItem
import com.alselwi.sumimagesapi.domain.Repository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryImplTest {

    private val repositoryImpl = spyk<Repository>()
    private val dispatcher = TestCoroutineDispatcher()
    private val api = spyk<ApiPictures>()

    private val listPics = listOf(
        SumPicturesItem("", "http://example.com/image1.jpg", 3,"1","",2,true),
        SumPicturesItem("" ,"http://example.com/image2.jpg", 3,"2","",2,true),
        SumPicturesItem("", "http://example.com/image3.jpg", 43,"3","",2,true)
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Test  getPics`() = runBlocking {

        every { runBlocking { api.getImages() } } returns listPics
        repositoryImpl.getPics()
        coVerify { repositoryImpl.getPics() }
    }
}