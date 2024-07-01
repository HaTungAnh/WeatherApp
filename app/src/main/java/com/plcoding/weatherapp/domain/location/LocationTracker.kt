package com.plcoding.weatherapp.domain.location

import android.location.Location

interface LocationTracker {
    // for simple we just use the framework location here
    // but for strictly clean architecture we should create a location data class
    suspend fun getCurrentLocation(): Location?
}