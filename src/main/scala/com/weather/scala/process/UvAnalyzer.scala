package com.weather.scala.process

import java.time.{LocalDateTime, ZoneOffset}
import java.util.Calendar

import com.weather.scala.common.City
import com.weather.scala.service.UVService

import scala.collection.mutable.ListBuffer

case class UvAnalyzer(uvService: UVService) {

  val start: LocalDateTime = LocalDateTime.of(2017, 7, 1, 0, 0)
  val end: LocalDateTime = LocalDateTime.of(2017, 8, 31, 0, 0)
  val cal: Calendar = Calendar.getInstance

  def generateUVStatistics(city: City): List[UvStatistics] = {
    val uvHistory = uvService.fetchHistoricalUV(city.coordinate.lat, city.coordinate.lon,
      convertToMillis(start), convertToMillis(end))

    uvHistory.map(uvHis => UvStatistics(city, extractMonth(uvHis.date), Some(extractYear(uvHis.date)), uvHis.value))
  }

  def findMediumMonthlyUV(city: City): List[UvStatistics] = {
    val statistics = generateUVStatistics(city)

    val result = new ListBuffer[UvStatistics]()

    for (month <- 0 to 11) {
      val monthlyStatistics = statistics
        .filter(_.month.equals(month))
        .map(_.value)
      val avg = monthlyStatistics.sum / monthlyStatistics.length
      result += UvStatistics(city, month, None, avg)
    }
    result.toList
  }

  var lowestUv: List[UvStatistics] => UvStatistics = (uvStatistics: List[UvStatistics]) => {
    uvStatistics.min(Ordering.by((uv: UvStatistics) => uv.value))
  }

  var highestUv: List[UvStatistics] => UvStatistics = (uvStatistics: List[UvStatistics]) => {
    uvStatistics.max(Ordering.by((uv: UvStatistics) => uv.value))
  }

  def average(list: List[Double]): Double = {
    list.sum / list.length
  }

  private def convertToMillis(date: LocalDateTime) = date.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli / 1000

  var extractMonth: Long => Int = (epoch: Long) => {
    cal.setTimeInMillis(epoch * 1000)
    cal.get(Calendar.MONTH)
  }

  var extractYear: Long => Int = (epoch: Long) => {
    cal.setTimeInMillis(epoch * 1000)
    cal.get(Calendar.YEAR)
  }
}
