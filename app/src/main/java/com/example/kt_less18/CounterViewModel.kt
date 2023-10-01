package com.example.kt_less18

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    private var model = CounterModel()


    private val counterLiveData = MutableLiveData<Int>()
    init {
        counterLiveData.value = model.counter
    }

    fun getCounterLiveData(): LiveData<Int> {
        return counterLiveData
    }

    fun addCounter() {
        model.counter++
        counterLiveData.value = model.counter
    }

    fun resetCounter() {
        model.counter = 0
        counterLiveData.value = model.counter
    }

    fun getModel(): CounterModel {
        return model
    }

    fun setModel(counterModel: CounterModel) {
        model = counterModel
        counterLiveData.value = model.counter
    }

}