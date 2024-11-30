package com.example.fitness_tracker

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()
    private var activityList = mutableListOf<Activity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("fitness_tracker", MODE_PRIVATE)

        loadData()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        myAdapter = MyAdapter(activityList)
        recyclerView.adapter = myAdapter

        val confirmButton = findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            addActivity()
        }
    }

    private fun addActivity() {
        val distance = findViewById<EditText>(R.id.editText_distance).text.toString().toDoubleOrNull()
        val duration = findViewById<EditText>(R.id.editText_duration).text.toString().toDoubleOrNull()
        val calories = findViewById<EditText>(R.id.editText_calories).text.toString().toDoubleOrNull()
        val intensity = findViewById<SeekBar>(R.id.seekBar_intensity).progress
        val activityType = when {
            findViewById<RadioButton>(R.id.radioButton_walk).isChecked -> "Spacer"
            findViewById<RadioButton>(R.id.radioButton_run).isChecked -> "Bieg"
            findViewById<RadioButton>(R.id.radioButton_strength).isChecked -> "Trening Siłowy"
            else -> "Nie wybrano"
        }

        if (distance != null && duration != null && calories != null) {
            val activity = Activity(activityType, distance, duration, calories, intensity)
            activityList.add(activity)
            myAdapter.notifyDataSetChanged()
            saveData()
            Toast.makeText(this, "Dodano trening", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveData() {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(activityList)
        editor.putString("fitness_list", json)
        editor.apply()
    }

    private fun loadData() {
        val json = sharedPreferences.getString("fitness_list", null)
        if (json != null) {
            val type = object : TypeToken<List<Activity>>() {}.type
            activityList = gson.fromJson(json, type)
        }
    }
}
