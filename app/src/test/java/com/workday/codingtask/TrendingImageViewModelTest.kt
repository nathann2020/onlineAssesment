package com.workday.codingtask

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.workday.codingtask.data.NetworkResponse
import com.workday.codingtask.data.OperationResult
import com.workday.codingtask.data.TrendingImage
import com.workday.codingtask.model.ImagesDataSource
import com.workday.codingtask.viewmodel.ImagesViewModel
import junit.framework.TestCase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.`when`
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class TrendingImageViewModelTest {

    @Mock
    private lateinit var context: Application

    private lateinit var imagesViewModel: ImagesViewModel

    @Mock
    private lateinit var repository: ImagesDataSource
    private lateinit var imagesList: List<TrendingImage>


    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        `when`(context.applicationContext).thenReturn(context)

        Dispatchers.setMain(testDispatcher)

        mockData()

        imagesViewModel = ImagesViewModel(repository)

    }


    @ExperimentalCoroutinesApi
    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve images with ViewModel and Repository returns empty data`() {


        runBlockingTest {

            `when`(repository.retrieveImages()).thenReturn(
                OperationResult.Success(NetworkResponse(emptyList()))
            )

            imagesViewModel.loadImages()

            verify(repository).retrieveImages()

        }

        assertNotNull(imagesViewModel.isLoading.getOrAwaitValue())
        assertNotNull(imagesViewModel.isEmpty.getOrAwaitValue())
        assertTrue(imagesViewModel.images.getOrAwaitValue().isEmpty())

    }


    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve images with ViewModel and Repository returns full data`() {

        runBlockingTest {

            `when`(repository.retrieveImages()).thenReturn(
                OperationResult.Success(NetworkResponse(imagesList))
            )

            imagesViewModel.loadImages()

            verify(repository).retrieveImages()

        }

        assertNotNull(imagesViewModel.isLoading.getOrAwaitValue())
        assertEquals(3, imagesViewModel.images.getOrAwaitValue().size)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve images with ViewModel and Repository returns an error`() {

        runBlockingTest {

            `when`(repository.retrieveImages()).thenReturn(
                OperationResult.Error(
                    Exception("Something went wrong. please try again later!")
                )
            )

            imagesViewModel.loadImages()

            verify(repository).retrieveImages()
        }

        assertNotNull(imagesViewModel.isLoading.getOrAwaitValue())
        assertNotNull(imagesViewModel.onError.getOrAwaitValue())
    }

    private fun mockData() {

        imagesList = listOf(

             TrendingImage("",null,"","","",""),
            TrendingImage("",null,"","","",""),
            TrendingImage("",null,"","","","")
        )
    }
}
