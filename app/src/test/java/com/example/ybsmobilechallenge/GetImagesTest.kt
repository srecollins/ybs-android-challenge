package com.example.ybsmobilechallenge

import com.example.ybsmobilechallenge.network.ApiHelper
import com.example.ybsmobilechallenge.network.ApiService
import com.example.ybsmobilechallenge.network.RetrofitService
import org.junit.Test
import kotlinx.coroutines.test.runTest

class GetImagesTest {

    private lateinit var apiService: ApiService

    private lateinit var apiHelper: ApiHelper

    @Test
    fun `fetchPhotos returns success result when API call is successful not mocked`() = runTest {
        apiService = RetrofitService.apiService
        apiHelper = ApiHelper(apiService)

        apiHelper.fetchPhotos("Yorkshire", false, null)
    }
}
