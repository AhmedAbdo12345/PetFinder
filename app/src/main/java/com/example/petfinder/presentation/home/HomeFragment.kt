package com.example.petfinder.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petfinder.R
import com.example.petfinder.data.model.ResponseState
import com.example.petfinder.data.model.animal.Animal
import com.example.petfinder.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var homeAdapter: HomeAdapter
    private var currentPage = 1
    private var totalPages = 0

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

        homeAdapter = HomeAdapter(::onItemClick)
        binding.recyclerViewAnimals.apply {
            adapter = homeAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 1).apply {
                orientation = RecyclerView.VERTICAL
            }
        }

        tabLayout = binding.tablayoutTypes

        homeViewModel.getTypes()
        fetchTypesFromAPi()

        homeViewModel.getAnimals(currentPage)

        getAnimalFilterForEachTab()


    }

    fun fetchTypesFromAPi() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.types.collect {
                    when (it) {
                        is ResponseState.OnLoading -> {
                            stateOnLoading()
                        }

                        is ResponseState.OnSuccess -> {
                            stateOnSuccess()
                            tabLayout.removeAllTabs()

                            val firstTab = tabLayout.newTab()
                                .setText("All")

                            tabLayout.addTab(firstTab)
                            for (x in 0 until it.response.types.size) {
                                val typeObj = it.response.types[x]
                                val myTab = tabLayout.newTab()
                                    .setText(typeObj.name)
                                tabLayout.addTab(myTab)

                            }
                        }

                        is ResponseState.OnError -> {
                            stateOnError()
                        }

                    }
                }


            }
        }
    }

    fun fetchAllAnimalsFromApi() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.animals.collect {
                    when (it) {
                        is ResponseState.OnLoading -> {
                            stateOnLoading()
                        }

                        is ResponseState.OnSuccess -> {
                            stateOnSuccess()

                            currentPage = it.response.pagination?.current_page!!
                            totalPages = it.response.pagination?.total_pages!!

                            // homeAdapter.submitList(null)
                            homeAdapter.submitList(it.response.animals)
                            binding.recyclerViewAnimals.apply {
                                adapter = homeAdapter
                            }

                        }

                        is ResponseState.OnError -> {
                            stateOnError()
                        }

                    }
                }


            }
        }
    }


    fun getAnimalFilterForEachTab() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                val animalType = tab.text as? String
                if (animalType != null) {

                    if (animalType != "All") {
                        animalType.let {
                            homeViewModel.getAnimalFilter(it)
                        }

                        viewLifecycleOwner.lifecycleScope.launch {
                            repeatOnLifecycle(Lifecycle.State.STARTED) {
                                homeViewModel.filterAnimal.collect {

                                    when (it) {
                                        is ResponseState.OnLoading -> {
                                            stateOnLoading()
                                        }

                                        is ResponseState.OnSuccess -> {
                                            stateOnSuccess()
                                            homeAdapter.submitList(null)

                                            homeAdapter.submitList(it.response.animals)
                                            binding.recyclerViewAnimals.apply {
                                                adapter = homeAdapter
                                            }
                                        }

                                        is ResponseState.OnError -> {
                                            stateOnError()
                                        }

                                    }
                                }
                            }
                        }
                    } else {
                        fetchAllAnimalsFromApi()
                     //   fetchMoreDataWhenScroll(animalType)

                    }
                }


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    fun onItemClick(animal: Animal) {
        animal.let {
            val action: HomeFragmentDirections.ActionHomeFragmentToDetailsFragment =
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(animal)
            findNavController().navigate(action)
        }

    }


    fun stateOnLoading() {
        binding.progressbar.visibility = View.VISIBLE
        binding.recyclerViewAnimals.visibility = View.GONE
        binding.imgvConnectionError.visibility = View.GONE
    }

    fun stateOnSuccess() {
        binding.progressbar.visibility = View.GONE
        binding.recyclerViewAnimals.visibility = View.VISIBLE
        binding.imgvConnectionError.visibility = View.GONE
    }

    fun stateOnError() {
        binding.progressbar.visibility = View.GONE
        binding.recyclerViewAnimals.visibility = View.GONE
        binding.imgvConnectionError.visibility = View.VISIBLE
    }

    fun fetchMoreDataWhenScroll(type:String) {
        binding.recyclerViewAnimals.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (type == "All"){
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                val totalItemCount = layoutManager!!.itemCount
                val lastVisibleItem = layoutManager!!.findLastVisibleItemPosition()

                if (lastVisibleItem == totalItemCount - 1) {


                    currentPage++
                    // Fetch more data
                    if (currentPage < totalPages) {
                        Toast.makeText(activity, "$currentPage page", Toast.LENGTH_SHORT).show();

                        homeViewModel.getAnimals(currentPage)
                        fetchAllAnimalsFromApi()
                    }

                }
            }
            }
        })
    }
}

