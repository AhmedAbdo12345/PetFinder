package com.example.petfinder.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
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
                }
            }
            tvAnimalName.text = "Name: ${item.name}"
            tvAnimalGender.text = "Gender: ${item.gender}"
            tvAnimalType.text = "Type: ${item.type}"


            root.setOnClickListener {
                onItemClick(getItem(position))
            }
        }
    }


}
