package com.sample.basicconfigapp.repository
import com.sample.basicconfigapp.api.CountriesApi
import com.sample.basicconfigapp.model.CountryDetails
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(val countriesApi: CountriesApi) {

    suspend fun getCountriesList(): Response<List<CountryDetails>> {
        return countriesApi.getCountriesList()
    }

    suspend fun getCountryDetails(fullname:String): Response<List<CountryDetails>> {
        return countriesApi.getCountryDetails(fullname)
    }

}