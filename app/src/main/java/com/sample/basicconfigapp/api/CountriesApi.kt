package com.sample.basicconfigapp.api


import com.sample.basicconfigapp.model.CountryDetails
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {
    @GET("all")
    suspend fun getCountriesList(): Response<List<CountryDetails>>

}