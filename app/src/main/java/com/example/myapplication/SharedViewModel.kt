package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel(){

    private val _selectedInicio= MutableLiveData<String>()
    private val _selectedDestino= MutableLiveData<String>()
    val selectedIncio: LiveData<String> get() = _selectedInicio
    val selectedDestino: LiveData<String> get() = _selectedDestino

    fun selectInicio(item: String){
        _selectedInicio.value = item
    }

    fun selectDestino(item: String){
        _selectedDestino.value = item
    }

}