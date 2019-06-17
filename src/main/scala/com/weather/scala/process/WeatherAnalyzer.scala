package com.weather.scala.process

import com.weather.scala.common.City
import com.weather.scala.model.CurrentWeatherResponse
import com.weather.scala.service.CurrentWeatherService

case class WeatherAnalyzer(currentWeatherService: CurrentWeatherService) {

  def getCurrentWeather(cityId: Int): CurrentWeatherResponse = {
    currentWeatherService.fetchWeather(cityId)
  }

  def generateWeatherStatistics(cities: Array[City]): Array[WeatherAnalysisResult] = {
    cities
      .filter(city => city.id.isDefined)
      .map(city => {
        val weatherResponse = getCurrentWeather(city.id.get)
        WeatherAnalysisResult(city, weatherResponse.main, weatherResponse.wind)
      })
  }
}
