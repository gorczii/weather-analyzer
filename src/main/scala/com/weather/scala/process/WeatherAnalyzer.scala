package com.weather.scala.process

import com.weather.scala.common.City
import com.weather.scala.service.CurrentWeatherService

object WeatherAnalyzer {

  def getCurrentWeather(cityId: Int) = {
    CurrentWeatherService.fetchWeather(cityId)
  }

  def generateWeatherStatistics(cities: Array[City]) = {
    cities
      .filter(city => city.id.isDefined)
      .map(city => {
        val weatherResponse = getCurrentWeather(city.id.get)
        WeatherAnalysisResult(city, weatherResponse.main, weatherResponse.wind)
      })
  }
}
