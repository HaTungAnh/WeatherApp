package com.plcoding.weatherapp.data.mappers

import com.plcoding.weatherapp.data.remote.WeatherDataDto
import com.plcoding.weatherapp.data.remote.WeatherDto
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// serve as a temporary class which used to forward index parameter
private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

// map the dto to the domain model
// map between the day and the list of weather data for that day per hour
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    // map the time get from weather data dto to the weather data object
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        // index / 24 always get rounded
        // so day 1 have 24 index, day 2 have 24 index and so on
        // which when / 24 will always return the day number like 0 is today, 2 is tomorrow and so on
        it.index / 24
    }.mapValues {
        // so basically when we call groupBy, we actually map the index with the Int keys
        // but we want to map the Int keys with the list of weather data
        // so we map it again to the data of IndexedWeatherData
        it.value.map { it.data }
    }
 }

// in WeatherInfo, we already can calculate the weatherDataPerDay with previous function
// but now we need to corresponding weather data for the current hour
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()

    // access the current day and find the closest hour to the current time
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}