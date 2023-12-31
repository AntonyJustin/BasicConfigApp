package com.sample.basicconfigapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.basicconfigapp.R
import com.sample.basicconfigapp.api.DataHandler
import com.sample.basicconfigapp.databinding.FragmentCountryListBinding
import com.sample.basicconfigapp.model.CountryDetails
import com.sample.basicconfigapp.model.CountryName
import com.sample.basicconfigapp.view.adapters.UniversalRecyclerViewAdapter
import com.sample.basicconfigapp.viewmodel.CountryListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CountryListFragment: BaseFragment() {
    lateinit var binding: FragmentCountryListBinding
    val viewModel: CountryListViewModel by viewModels()
    var countryAdapter =
        UniversalRecyclerViewAdapter<CountryDetails>(null, R.layout.rv_country_list_item)

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
            if(countrylistdetails_ is CountryDetails) {
                // No Explicit Casting needed.
                Log.e("Test","String length is ${countrylistdetails_.region}")


//                val bundle = Bundle()
//                bundle.putString("CommonName", countrylistdetails_.region)
//                findNavController().navigate(com.sample.basicconfigapp.R.id.action_CountryList_to_CountryDetails,bundle)

//                val action = CountryListFragmentDirections.actionCountryListToCountryDetails("")
//                findNavController().navigate(action)


                val countryName = CountryName("test")
                val action = CountryListFragmentDirections.actionCountryListToCountryDetails(countryName)
                findNavController().navigate(action)

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