package com.weather.scala.common

object WeatherParamEnum extends Enumeration {
  val Temperature, Humidity, Pressure, Wind = Value
}

object MinMax extends Enumeration {
  val Min, Max = Value
}
