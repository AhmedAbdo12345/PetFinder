package com.example.petfinder.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.petfinder.R
import com.example.petfinder.data.model.animal.Animal
import com.example.petfinder.databinding.RvAnimalBinding
import kotlinx.coroutines.CoroutineScope

class HomeAdapter(var onItemClick: (Animal) -> Unit) :
    ListAdapter<Animal, HomeAdapter.HomeViewHolder>(
        HomeDiffUtils()
    ) {
    class HomeViewHolder(var binding: RvAnimalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding: RvAnimalBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rv_animal,
                parent,
                false
            )
        return HomeViewHolder(binding)

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {

            item.photos?.getOrNull(0)?.small?.let { imageUrl ->
                imgAnimal.load(imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                    transformations(CircleCropTransformation())
                }
            }
            if (item.name == null || item.name.isEmpty()){
                tvAnimalName.text = "Name: NA"
            }else{
                tvAnimalName.text = "Name: ${item.name}"
            }
            if (item.gender == null || item.gender.isEmpty()){
                tvAnimalGender.text = "Gender: NA"
            }else{
                tvAnimalGender.text = "Gender: ${item.gender}"
            }
            if (item.type == null || item.type.isEmpty()){
                tvAnimalType.text = "Type: NA"
            }else{
                tvAnimalType.text = "Type: ${item.type}"
            }


            root.setOnClickListener {
                onItemClick(getItem(position))
            }
        }
    }


}
