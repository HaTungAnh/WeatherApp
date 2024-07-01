package com.plcoding.weatherapp.domain.weather

// mapper level which make sure the domain layer don't know anything about the data layer
data class WeatherInfo(
    // contain weather data per day
    // 7 days api return == number key
    val weatherDataPerDay: Map<Int, List<WeatherData>>,

    // weather data for current hour
    val currentWeatherData: WeatherData?
)
