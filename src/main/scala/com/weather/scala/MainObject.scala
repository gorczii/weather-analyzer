package com.weather.scala

import com.weather.scala.common.ProcessingConstants
import com.weather.scala.process.{UvAnalyzer, UvStatistics}
import com.weather.scala.service.{CurrentWeatherService, UVService}

object MainObject {

  def main(args: Array[String]): Unit = {
    val response = UVService.fetchHistoricalUV(37.75, -122.37, 1498049953, 1498481991)
    val currentUV = UVService.fetchCurrentUV(ProcessingConstants.LONDON.coordinate.lon, ProcessingConstants.LONDON.coordinate.lat)
    println(response)
    println(currentUV)

    val statistics : List[UvStatistics] = UvAnalyzer.generateUVStatistics(ProcessingConstants.CAMBODIA)
    statistics.foreach(println)
  }
}
