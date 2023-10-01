package com.example.kt_less18

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson

class MainActivity: AppCompatActivity() {

    private lateinit var viewModel: CounterViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val clickCounter: TextView = findViewById(R.id.counterView)
        val fab: FloatingActionButton = findViewById(R.id.fabView)
        val button: Button = findViewById(R.id.clearButton)

        viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        gson = Gson()

        val json = sharedPreferences.getString("counterModel", null)
        if (json != null) {
            val counterModel = gson.fromJson(json, CounterModel::class.java)
            viewModel.setModel(counterModel)
        }

        fab.setOnClickListener {
            viewModel.addCounter()
        }
        button.setOnClickListener {
            viewModel.resetCounter()
        }
        viewModel.getCounterLiveData()
            .observe(this, { counterValue -> clickCounter.text = counterValue.toString() })
    }

    override fun onStop() {
        super.onStop()
        val json = gson.toJson(viewModel.getModel())
        sharedPreferences.edit().putString("counterModel", json).apply()
    }
}


