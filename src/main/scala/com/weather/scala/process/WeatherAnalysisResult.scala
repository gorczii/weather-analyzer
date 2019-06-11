package com.weather.scala.process

import com.weather.scala.common.City
import com.weather.scala.model.current.{MainWeatherValues, Wind}

case class WeatherAnalysisResult(city: City,
                                 mainValues: MainWeatherValues,
                                 wind: Wind) {

  override def toString = {
    s"city: ${city.name} (${city.coordinate.lon},${city.coordinate.lat}) | temp: ${mainValues.temp}*C | " +
      s"humidity: ${mainValues.humidity}% | pressure: ${mainValues.pressure} hPa | wind ${wind.speed.getOrElse("-")} km/h"
  }

}
