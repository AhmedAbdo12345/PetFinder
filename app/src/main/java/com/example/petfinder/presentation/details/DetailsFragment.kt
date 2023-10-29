package com.example.petfinder.presentation.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import coil.load
import com.example.petfinder.R
import com.example.petfinder.databinding.FragmentDetailsBinding
import java.lang.Exception


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
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animal = DetailsFragmentArgs.fromBundle(requireArguments()).animalObject
        animal.let {

            if (it.name == null || it.name.isEmpty()) {
                binding.tvName.text = "Name: NA"
            } else {
                binding.tvName.text = "Name: ${it.name}"
            }

            if (it.size == null || it.size.isEmpty()) {
                binding.tvSize.text = "Size: NA"
            } else {
                binding.tvSize.text = "Size: ${it.size}"
            }

            if (it.colors?.primary == null || it.colors?.primary.isEmpty()) {
                binding.tvColor.text = "Primary Color: NA"
            } else {
                binding.tvColor.text = "Primary Color: ${it.colors?.primary}"
            }

            if (it.contact?.address == null) {
                binding.tvAddress.text = "Address: NA"
            } else {
                binding.tvAddress.text =
                    "Address: ${it.contact?.address?.city},${it.contact?.address?.state},${it.contact?.address?.country}"
            }


            it.photos?.getOrNull(0)?.small?.let { imageUrl ->
                binding.imgVAnimal.load(imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                }
            }
            binding.btnOpenUrl.setOnClickListener {

                val webIntent: Intent = Uri.parse("https://www.android.com").let { webpage ->
                    Intent(Intent.ACTION_VIEW, webpage)

                }
                startActivity(webIntent)


                /* animal.url?.toUri().let {
                     val openURL = Intent(Intent.ACTION_VIEW,it)
                     Intent.createChooser(openURL,null)
                     try {
                         startActivity(openURL)
                     }catch (e:Exception){

                     }*/

                  //  if (openURL.resolveActivity(requireContext().packageManager) != null){
                 //   }

                }

            }


        }
    }
