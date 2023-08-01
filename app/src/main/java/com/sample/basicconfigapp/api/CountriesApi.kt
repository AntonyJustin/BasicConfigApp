package com.sample.basicconfigapp.api


import com.sample.basicconfigapp.model.CountryDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {
    @GET("all")
    suspend fun getCountriesList(): Response<List<CountryDetails>>

    @GET("name/{countryName}?fullText=true")
    suspend fun getCountryDetails(@Path("countryName") countryName: String?): Response<List<CountryDetails>>

}