package com.weather.scala.process

import com.weather.scala.common.{City, MinMax, WeatherParamEnum}
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

  def findPeakStatistic(weatherAnalysis: Array[WeatherAnalysisResult], minMax: MinMax.Value, weatherParamEnum: WeatherParamEnum.Value): WeatherAnalysisResult = {
    var extract: WeatherAnalysisResult => Double = null
    weatherParamEnum match {
      case WeatherParamEnum.Temperature => extract = (w: WeatherAnalysisResult) => w.mainValues.temp
      case WeatherParamEnum.Humidity => extract = (w: WeatherAnalysisResult) => w.mainValues.humidity.toDouble
      case WeatherParamEnum.Pressure => extract = (w: WeatherAnalysisResult) => w.mainValues.pressure.toDouble
      case WeatherParamEnum.Wind => extract = (w: WeatherAnalysisResult) => w.wind.speed.get
    }
    val weatherStatistic = findCustomizedWeatherStatistic(weatherAnalysis, minMax, extract)
    WeatherAnalysisResult(weatherStatistic.city, weatherStatistic.mainValues, weatherStatistic.wind)
  }

  def findCustomizedWeatherStatistic(weatherAnalysis: Array[WeatherAnalysisResult], minMax: MinMax.Value,
                                     analysisFieldExtract: WeatherAnalysisResult => Double): WeatherAnalysisResult = {
    minMax match {
      case MinMax.Max => weatherAnalysis.max(Ordering.by(analysisFieldExtract))
      case MinMax.Min => weatherAnalysis.min(Ordering.by(analysisFieldExtract))
    }
  }
}
