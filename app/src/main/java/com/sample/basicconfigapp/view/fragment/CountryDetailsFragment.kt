package com.sample.basicconfigapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.basicconfigapp.R
import com.sample.basicconfigapp.api.DataHandler
import com.sample.basicconfigapp.databinding.FragmentCountryDetailsBinding
import com.sample.basicconfigapp.databinding.FragmentCountryListBinding
import com.sample.basicconfigapp.databinding.FragmentSplashBinding
import com.sample.basicconfigapp.model.CountryListDetails
import com.sample.basicconfigapp.utils.callbacks.ClickHelper
import com.sample.basicconfigapp.utils.callbacks.OnItemClickListener
import com.sample.basicconfigapp.view.adapters.UniversalRecyclerViewAdapter
import com.sample.basicconfigapp.viewmodel.CountryDetailsViewModel
import com.sample.basicconfigapp.viewmodel.CountryListViewModel
import com.sample.basicconfigapp.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailsFragment: BaseFragment() {
    lateinit var binding: FragmentCountryDetailsBinding
    val viewModel: CountryDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentCountryListBinding.inflate(inflater,container,false)
        binding.fr = this
        //binding.clickHelper = this
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var toolbar = binding?.toolbarLayout.toolbar
        toolbar.title = "CountryList"
        initRecyclerView()
        observeLiveData()

    }

    override fun onClick(vararg data: Any) {

        for (countrylistdetails_ in data) {
            if(countrylistdetails_ is CountryListDetails) {
                // No Explicit Casting needed.
                Log.e("Test","String length is ${countrylistdetails_.region}")
            }
        }

    }

    private fun observeLiveData() {
        viewModel.onLineCountries.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    var data = dataHandler.data
                    dataHandler.data?.let {
                        countryAdapter.setData(dataHandler.data)
                    }
                }
                is DataHandler.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
                is DataHandler.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE

                }
            }
        }
        viewModel.getCountries()
    }

    private fun initRecyclerView() {
        countryAdapter.fr = this
        countryAdapter.setListener(this)
        binding.rvVouchers.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvVouchers.adapter = countryAdapter

    }

}