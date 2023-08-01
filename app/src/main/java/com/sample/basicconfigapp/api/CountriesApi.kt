package com.sample.basicconfigapp.api


import com.sample.basicconfigapp.model.ApiResponse
import com.sample.basicconfigapp.model.CountryListDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesApi {
    @GET("all")
    suspend fun getCountriesList(): Response<List<CountryListDetails>>

}