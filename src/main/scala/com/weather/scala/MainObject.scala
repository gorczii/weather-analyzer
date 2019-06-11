package com.weather.scala

import com.weather.scala.common.ProcessingConstants
import com.weather.scala.process.{UvAnalyzer, WeatherAnalyzer}

object MainObject {

  def main(args: Array[String]): Unit = {
    println("Monthly UV statistics for London (7/2017 - 5/2019)")
    val londonUvStatistics = UvAnalyzer.findMediumMonthlyUV(ProcessingConstants.LONDON)
    londonUvStatistics.foreach(println)

    println("\n\nMonthly UV statistics for Rome (7/2017 - 5/2019)")
    val romeUvStatistics = UvAnalyzer.findMediumMonthlyUV(ProcessingConstants.ROME)
    romeUvStatistics.foreach(println)

    println("\n\nCurrent weather info for the chosen weekend spots in Poland")
    val weatherAnalysisResults = WeatherAnalyzer.generateWeatherStatistics(ProcessingConstants.polishTowns)
    weatherAnalysisResults.foreach(println)
  }
}
