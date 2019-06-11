package com.weather.scala.model

import com.weather.scala.common.Coord
import com.weather.scala.model.current._

case class CurrentWeatherResponse(coord: Coord,
                                  weather: List[Weather],
                                  base: String,
                                  main: MainWeatherValues,
                                  visibility: Option[Int],
                                  wind: Wind,
                                  clouds: CloudsInfo,
                                  dt: Long,
                                  sys: Sys,
                                  id: Long,
                                  name: String,
                                  cod: Int) {

}
