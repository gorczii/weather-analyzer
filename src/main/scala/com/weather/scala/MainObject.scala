package com.weather.scala

import com.weather.scala.common.ProcessingConstants
import com.weather.scala.process.{UvAnalyzer, WeatherAnalyzer}
import com.weather.scala.service.UVService

object MainObject {

  def main(args: Array[String]): Unit = {

    val uvAnalyzer: UvAnalyzer = UvAnalyzer(new UVService)

    println("Monthly UV statistics for London (7/2017 - 5/2019)")
    val londonUvStatistics = uvAnalyzer.findMediumMonthlyUV(ProcessingConstants.LONDON)
    londonUvStatistics.foreach(println)

    println("\n\nMonthly UV statistics for Rome (7/2017 - 5/2019)")
    val romeUvStatistics = uvAnalyzer.findMediumMonthlyUV(ProcessingConstants.ROME)
    romeUvStatistics.foreach(println)

    println("\n\nCurrent weather info for the chosen weekend spots in Poland")
    val weatherAnalysisResults = WeatherAnalyzer.generateWeatherStatistics(ProcessingConstants.polishTowns)
    weatherAnalysisResults.foreach(println)
  }
}
