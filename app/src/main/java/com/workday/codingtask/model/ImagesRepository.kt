package com.workday.codingtask.model

import android.util.Log
import com.workday.codingtask.data.ApiClient
import com.workday.codingtask.data.NetworkResponse
import com.workday.codingtask.data.OperationResult

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
const val TAG = "CONSOLE"

class ImagesRepository : ImagesDataSource {

    override suspend fun retrieveImages(): OperationResult<NetworkResponse> {

        try {
            //Hardcoding the api_key for now
            val response = ApiClient.build()?.trendingImages("dc6zaTOxFJmzC")

            response?.let {
                return if (it.isSuccessful && it.body() != null) {

                    Log.v(TAG, "data $it")
                    OperationResult.Success(it.body())
                } else {
                    OperationResult.Error(Exception(it.message()))
                }
            } ?: run {
                return OperationResult.Error(Exception("Something went wrong. please try again later!"))
            }
        } catch (e: Exception) {
            return OperationResult.Error(e)
        }
    }
}