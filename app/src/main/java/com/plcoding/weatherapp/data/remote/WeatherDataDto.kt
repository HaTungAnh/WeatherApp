package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

// https://api.open-meteo.com/v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl&latitude=53&longitude=13
// focus on hourly block which contains all the data we need
data class WeatherDataDto(
    val time: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name = "relativehumidity_2m")
    val humidities: List<Double>,
    @field:Json(name = "windspeed_10m")
    val windSpeeds: List<Double>,
    @field:Json(name = "pressure_msl")
    val pressures: List<Double>
)
