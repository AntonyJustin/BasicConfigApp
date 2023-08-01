package com.sample.basicconfigapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sample.basicconfigapp.databinding.FragmentCountryDetailsBinding
import com.sample.basicconfigapp.model.CountryDetails
import com.sample.basicconfigapp.viewmodel.CountryDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailsFragment: BaseFragment() {
    lateinit var binding: FragmentCountryDetailsBinding
    val viewModel: CountryDetailsViewModel by viewModels()

    private val args : CountryDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentCountryDetailsBinding.inflate(inflater,container,false)
        binding.fr = this
        //binding.clickHelper = this
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var toolbar = binding?.toolbarLayout.toolbar
        toolbar.title = "CountryList"

        // Receive the arguments in a variable
        val countryName = args.commonName

        Log.e("Test","name = ${countryName.CommonName}")
        // set the values to respective textViews


        //observeLiveData()

    }

    override fun onClick(vararg data: Any) {

        for (countrylistdetails_ in data) {
            if(countrylistdetails_ is CountryDetails) {
                // No Explicit Casting needed.
                Log.e("Test","String length is ${countrylistdetails_.region}")
            }
        }

    }

//    private fun observeLiveData() {
//        viewModel.onLineCountries.observe(viewLifecycleOwner) { dataHandler ->
//            when (dataHandler) {
//                is DataHandler.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE
//                    var data = dataHandler.data
//                    dataHandler.data?.let {
//                        countryAdapter.setData(dataHandler.data)
//                    }
//                }
//                is DataHandler.ERROR -> {
//                    binding.progressBar.visibility = View.GONE
//                }
//                is DataHandler.LOADING -> {
//                    binding.progressBar.visibility = View.VISIBLE
//
//                }
//            }
//        }
//        viewModel.getCountries()
//    }

}