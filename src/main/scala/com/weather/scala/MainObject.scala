package com.weather.scala

import com.weather.scala.common.{MinMax, ProcessingConstants, WeatherParamEnum}
import com.weather.scala.process.{UvAnalyzer, WeatherAnalysisResult, WeatherAnalyzer}
import com.weather.scala.service.{CurrentWeatherService, UVService}

object MainObject {

  def main(args: Array[String]): Unit = {

    val uvAnalyzer: UvAnalyzer = UvAnalyzer(new UVService)
    val weatherAnalyzer: WeatherAnalyzer = WeatherAnalyzer(new CurrentWeatherService)

    println("Monthly UV statistics for London (7/2017 - 5/2019)")
    val londonUvStatistics = uvAnalyzer.findMediumMonthlyUV(ProcessingConstants.LONDON)
    londonUvStatistics.foreach(println)

    println("\n\nMonthly UV statistics for Rome (7/2017 - 5/2019)")
    val romeUvStatistics = uvAnalyzer.findMediumMonthlyUV(ProcessingConstants.ROME)
    romeUvStatistics.foreach(println)

    println("\n\nCurrent weather info for the chosen weekend spots in Poland")
    val weatherAnalysisResults = weatherAnalyzer.generateWeatherStatistics(ProcessingConstants.polishTowns)
    weatherAnalysisResults.foreach(println)

    val minHum: WeatherAnalysisResult = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Min, WeatherParamEnum.Humidity)
    println(s"\n\nMin humidity for the chosen weekend spots in Poland $minHum")

    val maxTemp: WeatherAnalysisResult = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Max, WeatherParamEnum.Temperature)
    println(s"Max temperature for the chosen weekend spots in Poland $maxTemp")

    val maxPressure: WeatherAnalysisResult = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Max, WeatherParamEnum.Pressure)
    println(s"Max pressure for the chosen weekend spots in Poland $maxPressure")

    val minWind: WeatherAnalysisResult = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Min, WeatherParamEnum.Wind)
    println(s"Min wind for the chosen weekend spots in Poland $minWind")
  }
}
