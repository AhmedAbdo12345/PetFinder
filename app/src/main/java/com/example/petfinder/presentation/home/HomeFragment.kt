package com.example.petfinder.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.petfinder.R
import com.example.petfinder.ResponseState
import com.example.petfinder.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
private lateinit var binding : FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModel.Factory
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getAnimals()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.animals.collect{
                  when(it){
                      is ResponseState.OnLoading ->{

                      }
                      is ResponseState.OnSuccess ->{


                      }
                      is ResponseState.OnError -> {

                      }

                  }
                }

            }}

    }
}