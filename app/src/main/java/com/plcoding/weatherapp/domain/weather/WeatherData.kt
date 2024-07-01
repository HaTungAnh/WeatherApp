package com.plcoding.weatherapp.domain.weather

import java.time.LocalDateTime

// specific data for given hour
// make it easier to deal with data than a dto which contain list of data
data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)