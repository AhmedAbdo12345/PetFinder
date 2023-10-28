package com.example.petfinder.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.petfinder.App
import com.example.petfinder.ResponseState
import com.example.petfinder.TokenManager
import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse
import com.example.petfinder.data.repository.AnimalRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val animalRepo: AnimalRepo): ViewModel() {

    private var _animals  = MutableStateFlow < ResponseState <AnimalsResponse>> (ResponseState.OnLoading(false))
    val animals: StateFlow<ResponseState<AnimalsResponse>> = _animals.asStateFlow()

    private var _types  = MutableStateFlow < ResponseState <TypeResponse>> (ResponseState.OnLoading(false))
    val types: StateFlow<ResponseState<TypeResponse>> = _types.asStateFlow()

    private var _filterAnimal  = MutableStateFlow < ResponseState <AnimalsResponse>> (ResponseState.OnLoading(false))
    val filterAnimal: StateFlow<ResponseState<AnimalsResponse>> = _filterAnimal.asStateFlow()



    fun getAnimals(){
        viewModelScope.launch{
            TokenManager.getToken(animalRepo)?.let {
                animalRepo.getAnimals(it).catch {
                    _animals.value = ResponseState.OnError(it.localizedMessage)

                }.collect{
                    _animals.value = ResponseState.OnSuccess(it)
                }
            }
        }
    }

    fun getTypes(){
        viewModelScope.launch{
            TokenManager.getToken(animalRepo)?.let {
                animalRepo.getTypes(it).catch {
                    _types.value = ResponseState.OnError(it.localizedMessage)

                }.collect{
                    _types.value = ResponseState.OnSuccess(it)
                }
            }
        }
    }


    fun getAnimalFilter(typeAnimal: String){

        viewModelScope.launch{
            TokenManager.getToken(animalRepo)?.let {

                Log.d("zxcv", "getFilterAnimal: " + it + "    cccc")
                animalRepo.getAnimalForType(it, typeAnimal).catch {
                    _filterAnimal.value = ResponseState.OnError(it.localizedMessage!!)

                }.collect {
                    _filterAnimal.value = ResponseState.OnSuccess(it)
                }
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App
                HomeViewModel(app.appDependencies.animalRepo)
            }
        }
    }
}