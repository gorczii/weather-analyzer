package com.weather.scala.protocol

import com.weather.scala.common.Coord
import com.weather.scala.model.{CurrentWeatherResponse, UV}
import com.weather.scala.model.current.{CloudsInfo, MainWeatherValues, Sys, Weather, Wind}
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

object JsonProtocols {

  //Current weather
  implicit val coordFormat: RootJsonFormat[Coord] = jsonFormat2(Coord)
  implicit val cloudsInfoFormat: RootJsonFormat[CloudsInfo] = jsonFormat1(CloudsInfo)
  implicit val mainWeatherValuesFormat: RootJsonFormat[MainWeatherValues] = jsonFormat5(MainWeatherValues)
  implicit val weatherFormat: RootJsonFormat[Weather] = jsonFormat4(Weather)
  implicit val windFormat: RootJsonFormat[Wind] = jsonFormat2(Wind)
  implicit val sysFormat: RootJsonFormat[Sys] = jsonFormat5(Sys)
  implicit val currentWeatherFormat: RootJsonFormat[CurrentWeatherResponse] = jsonFormat12(CurrentWeatherResponse)

  //UV
  implicit val uvFormat: RootJsonFormat[UV] = jsonFormat5(UV)
}
