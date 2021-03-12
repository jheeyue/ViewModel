package com.example.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    //private var counter:Int=0
    private lateinit var counterViewModel: CounterViewModel
    private lateinit var textViewCounter:TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Main", "This is onCreate")

        sharedPreferences = getSharedPreferences(getString(R.string.preferenceFile), Context.MODE_PRIVATE)
        val counter = sharedPreferences.getInt("counter", 0)

        //this is initialize of view model
        counterViewModel=ViewModelProvider(this).get(CounterViewModel::class.java)
        textViewCounter=findViewById(R.id.textViewCounter)

        //Create an observer
        val counterObserver = Observer<Int>{
            textViewCounter.text=counterViewModel.globalCounter.value.toString()
        }

        //Assign observer to live data
        counterViewModel.globalCounter.observe(this, counterObserver)
        counterViewModel.setCounter(counter)

        val buttonIncrement=findViewById<Button>(R.id.buttonIncrement).setOnClickListener {
            counterViewModel.incrementCounter()
        }

        val buttonDecreament=findViewById<Button>(R.id.buttonDecreament).setOnClickListener{
            counterViewModel.decreamentCounter()
        }


    }

    override fun onStart() {
        super.onStart()
        Log.d("Main", "This is onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Main", "This is onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Main", "This is onPause")
        with(sharedPreferences.edit()){
            putInt("counter", counterViewModel.getCounter())
            apply()
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("Main", "This is onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Main", "This is onDestroy")
    }

}