package com.example.fitness_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val activityList: List<Activity>) : RecyclerView.Adapter<MyAdapter.ActivityViewHolder>() {

    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activityType: TextView = itemView.findViewById(R.id.activity_type)
        val activityDistance: TextView = itemView.findViewById(R.id.activity_distance)
        val activityDuration: TextView = itemView.findViewById(R.id.activity_duration)
        val activityCalories: TextView = itemView.findViewById(R.id.activity_calories)
        val activityIntensity: TextView = itemView.findViewById(R.id.activity_intensity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ActivityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val currentActivity = activityList[position]
        holder.activityType.text = currentActivity.type
        holder.activityDistance.text = "Dystans: ${currentActivity.distance} km"
        holder.activityDuration.text = "Czas: ${currentActivity.duration} min"
        holder.activityCalories.text = "Kalorie: ${currentActivity.calories} kcal"
        holder.activityIntensity.text = "Intensywność: ${currentActivity.intensity}"
    }

    override fun getItemCount() = activityList.size
}
