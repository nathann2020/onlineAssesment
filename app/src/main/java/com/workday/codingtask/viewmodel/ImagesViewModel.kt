package com.workday.codingtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.workday.codingtask.data.NetworkResponse
import com.workday.codingtask.data.OperationResult
import com.workday.codingtask.data.TrendingImage
import com.workday.codingtask.model.ImagesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
class ImagesViewModel(private val repository: ImagesDataSource) : ViewModel() {

    private val _images =
        MutableLiveData<List<TrendingImage>>().apply { value = emptyList() }

    val images: LiveData<List<TrendingImage>> = _images

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onError = MutableLiveData<Any>()
    val onError: LiveData<Any> = _onError

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    fun loadImages() {
        _isLoading.value = true
        viewModelScope.launch {
            val result: OperationResult<NetworkResponse> = withContext(Dispatchers.IO) {
                repository.retrieveImages()
            }
            _isLoading.value = false
            when (result) {
                is OperationResult.Success -> {
                    if (result.response?.data.isNullOrEmpty()) {
                        _isEmpty.value = true
                    } else {
                        _images.value = result.response?.data
                    }
                }
                is OperationResult.Error -> {
                    _onError.value = result.exception

                }
            }
        }
    }

}