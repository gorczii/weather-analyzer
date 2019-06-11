package com.weather.scala.process

import java.time.{LocalDate, LocalDateTime, ZoneOffset}

import com.weather.scala.common.City
import com.weather.scala.service.UVService
import java.util.Calendar

object UvAnalyzer {

  val start: LocalDateTime = LocalDateTime.of(2017, 7, 1, 0, 0)
  val end: LocalDateTime = LocalDateTime.of(2018, 6, 30, 0, 0)
  val cal : Calendar = Calendar.getInstance


  def convertToMillis(date: LocalDateTime) = date.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli/1000

  var extractMonth: Long => Int = (epoch: Long) => {
    cal.setTimeInMillis(epoch*1000)
    cal.get(Calendar.MONTH)
  }

  var extractYear: Long => Int = (epoch: Long) => {
    cal.setTimeInMillis(epoch*1000)
    cal.get(Calendar.YEAR)
  }


  def generateUVStatistics(city: City): List[UvStatistics] = {
    val uvHistory = UVService.fetchHistoricalUV(city.coordinate.lat, city.coordinate.lon,
      convertToMillis(start), convertToMillis(end))

    uvHistory.map(uvHis => UvStatistics(city, extractMonth(uvHis.date), extractYear(uvHis.date), uvHis.value))
  }

  var lowestUv: List[UvStatistics] => UvStatistics = (uvStatistics: List[UvStatistics]) => {
    uvStatistics.min(Ordering.by((uv:UvStatistics) => uv.value))
  }

  var highestUv: List[UvStatistics] => UvStatistics = (uvStatistics: List[UvStatistics]) => {
    uvStatistics.max(Ordering.by((uv:UvStatistics) => uv.value))
  }
}
