package com.workday.codingtask.model

import com.workday.codingtask.data.NetworkResponse
import com.workday.codingtask.data.OperationResult

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
interface ImagesDataSource {

    suspend fun retrieveImages(): OperationResult<NetworkResponse>
}