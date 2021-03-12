package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    val globalCounter=MutableLiveData<Int>()
    private var counter:Int

    init { //initialization
        globalCounter.value = 0
        counter = 0
        Log.i("Counter View Model", "Initialized")
    }
    fun incrementCounter(){
        globalCounter.value = globalCounter.value?.plus(1)
        setCounter(globalCounter.value?.toInt())
    }

    fun decreamentCounter(){
        globalCounter.value = globalCounter.value?.minus(1)
        setCounter(globalCounter.value?.toInt())
    }

    fun getCounter():Int{
        return counter
    }

    fun setCounter(counter: Int?){
        if(counter!=null)
            this.counter = counter
    }
}