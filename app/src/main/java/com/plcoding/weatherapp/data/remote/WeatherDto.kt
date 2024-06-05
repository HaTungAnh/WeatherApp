package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

// This class is used to store the data from the API
// data transfer object: Dto
// represent json response get from the api
data class WeatherDto(

    // the name need to be the same as the json response
    // but it will make nonsense if we call it hourly
    // so we need to change it to weatherData and specify by @field:Json(name = "hourly")
    // we only care about hourly field because that contains all the data we need
    // https://api.open-meteo.com/v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl&latitude=53&longitude=13
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
