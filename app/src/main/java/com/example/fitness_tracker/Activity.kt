package com.example.fitness_tracker

data class Activity(
    val type: String,
    val distance: Double,
    val duration: Double,
    val calories: Double,
    val intensity: Int
)

