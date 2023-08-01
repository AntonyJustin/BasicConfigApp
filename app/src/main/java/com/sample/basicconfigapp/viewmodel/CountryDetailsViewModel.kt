package com.sample.basicconfigapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.basicconfigapp.MyApplication
import com.sample.basicconfigapp.api.DataHandler
import com.sample.basicconfigapp.model.CountryDetails
import com.sample.basicconfigapp.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(private val application: MyApplication, private val networkRepository: NetworkRepository) :
    BaseViewModel(application) {

    private val countries = MutableLiveData<DataHandler<List<CountryDetails>>>()
    val onLineCountries:LiveData<DataHandler<List<CountryDetails>>> = countries

    fun getCountries() {
        countries.postValue(DataHandler.LOADING())
        viewModelScope.launch {
            val response = networkRepository.getCountriesList()
            countries.postValue(handleResponse(response))
        }
    }

    private fun handleResponse(response: Response<List<CountryDetails>>):DataHandler<List<CountryDetails>> {
        if(response.isSuccessful){
            response.body()?.let {
                return DataHandler.SUCCESS(it)
            }
        }
        return DataHandler.ERROR(message = response.errorBody().toString())
    }

}