package com.sample.basicconfigapp.repository
import com.sample.basicconfigapp.api.CountriesApi
import com.sample.basicconfigapp.model.ApiResponse
import com.sample.basicconfigapp.model.CountryListDetails
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(val countriesApi: CountriesApi) {

    suspend fun getCountriesList(): Response<List<CountryListDetails>> {
        return countriesApi.getCountriesList()
    }

}