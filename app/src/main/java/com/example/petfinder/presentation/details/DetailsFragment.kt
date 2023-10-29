package com.example.petfinder.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import coil.load
import com.example.petfinder.R
import com.example.petfinder.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_details, container, false)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      val animal = DetailsFragmentArgs.fromBundle(requireArguments()).animalObject
        animal.let {
            binding.tvName.text = "Name: ${it.name}"
            binding.tvSize.text = "Size: ${it.size}"
            binding.tvColor.text="Primary Color: ${it.colors?.primary}"
            binding.tvAddress.text= "Address: ${it.contact?.address?.city},${it.contact?.address?.state},${it.contact?.address?.country}"

            it.photos?.getOrNull(0)?.small?.let { imageUrl ->
                binding.imgVAnimal.load(imageUrl) {
                    crossfade(true)
                }
            }


        }
    }
}