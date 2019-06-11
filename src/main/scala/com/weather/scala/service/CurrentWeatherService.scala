package com.weather.scala.service

import com.weather.scala.common.ApiConstants
import com.weather.scala.model.CurrentWeatherResponse
import com.weather.scala.protocol.JsonProtocols._
import scalaj.http.Http
import spray.json._

object CurrentWeatherService {
  private val resourcePath = "/weather"

  def fetchWeather(cityName: String, countryCode: String = ""): CurrentWeatherResponse = {
    val q = if (countryCode.equals("")) cityName else s"$cityName,$countryCode"
    val url = ApiConstants.getURL(s"$resourcePath/?q=$q&units=metric")
    callApi(url)
  }

  def fetchWeather(lon: Double, lat: Double): CurrentWeatherResponse = {
    val coordQuery = s"lat=$lat&lon=$lon"
    val url = ApiConstants.getURL(s"$resourcePath/?$coordQuery&units=metric")
    callApi(url)
  }

  def fetchWeather(id: Int): CurrentWeatherResponse = {
    val url = ApiConstants.getURL(s"$resourcePath/?id=$id&units=metric")
    callApi(url)
  }

  def fetchWeather(zip: Int, countryCode: String): CurrentWeatherResponse = {
    val url = ApiConstants.getURL(s"$resourcePath/?zip=$zip,$countryCode&units=metric")
    callApi(url)
  }

  private def callApi(url: String): CurrentWeatherResponse = {
    val response = Http(url).asString.body.parseJson
    response.convertTo[CurrentWeatherResponse]
  }
}
