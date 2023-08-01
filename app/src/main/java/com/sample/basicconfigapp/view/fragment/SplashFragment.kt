package com.sample.basicconfigapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sample.basicconfigapp.R
import com.sample.basicconfigapp.databinding.FragmentSplashBinding
import com.sample.basicconfigapp.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment: BaseFragment() {
    lateinit var binding: FragmentSplashBinding
    val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentSplashBinding.inflate(inflater,container,false)

        binding.splashViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding = FragmentSplashBinding.bind(view)

        viewModel.initSplashScreen()

        viewModel.splashlivedata.observe(viewLifecycleOwner) { isUserlistopen ->
            if(isUserlistopen){
                redirectToUserList()
            }
        }
    }

    override fun onClick(vararg data: Any) {
        TODO("Not yet implemented")
    }

    fun redirectToUserList() {
        findNavController().navigate(R.id.action_SplashFragment_to_CountryListFragment)
    }

}