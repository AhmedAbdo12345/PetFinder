package com.example.petfinder.presentation.home

import androidx.recyclerview.widget.DiffUtil
import com.example.petfinder.data.model.animal.Animal

class HomeDiffUtils  : DiffUtil.ItemCallback<Animal>(){

    override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return oldItem == newItem
    }
}