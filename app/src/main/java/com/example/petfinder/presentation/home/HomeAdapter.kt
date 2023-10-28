package com.example.petfinder.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petfinder.R
import com.example.petfinder.data.model.animal.Animal
import com.example.petfinder.databinding.RvAnimalBinding
import kotlinx.coroutines.CoroutineScope

class HomeAdapter(var onItemClick: (Animal) -> Unit, private val scope: CoroutineScope) :
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

            tvAnimalName.text = item.name
            tvAnimalGender.text = item.gender
            tvAnimalType.text = item.type


            root.setOnClickListener {
                onItemClick(getItem(position))
            }
        }
    }


}
