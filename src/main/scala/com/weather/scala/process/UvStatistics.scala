package com.weather.scala.process

import com.weather.scala.common.City

case class UvStatistics(city: City,
                        month: Int,
                        year: Int,
                        value: Double) {

  override def toString = {
    s"city: ${city.name} (${city.coordinate.lon},${city.coordinate.lat}) | month: $month | year: $year | UV value: $value"
  }
}
