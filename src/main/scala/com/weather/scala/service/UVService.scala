package com.weather.scala.service

import com.weather.scala.protocol.JsonProtocols._
import com.weather.scala.common.ApiConstants
import com.weather.scala.model.UV
import scalaj.http.Http
import spray.json.DefaultJsonProtocol._
import spray.json._

class UVService {
  val RESOURCE_PATH = "/uvi"
  val UV_FORECAST_PATH = "/forecast"
  val UV_HISTORY_PATH = "/history"

  def fetchCurrentUV(lat: Double, lon: Double): UV = {
    val query = s"?lat=$lat&lon=$lon"
    val url = ApiConstants.getURL(s"$RESOURCE_PATH$query")
    callApi(url)
  }

  def fetchForecastUV(lat: Double, lon: Double, cnt: Int = 0): List[UV] = {
    val baseQuery = s"?lat=$lat&lon=$lon"
    val query = if (cnt == 0) baseQuery else s"$baseQuery&cnt=$cnt"
    val url = ApiConstants.getURL(s"$RESOURCE_PATH$UV_FORECAST_PATH$query")
    callApiList(url)
  }

  def fetchHistoricalUV(lat: Double, lon: Double, start: Long, end: Long): List[UV] = {
    val query = s"?lat=$lat&lon=$lon&start=$start&end=$end"
    val url = ApiConstants.getURL(s"$RESOURCE_PATH$UV_HISTORY_PATH$query")
    callApiList(url)
  }

  private def callApi(url: String): UV = {
    val response = Http(url).asString.body.parseJson
    response.convertTo[UV]
  }

  private def callApiList(url: String): List[UV] = {
    val response = Http(url).asString.body.parseJson
    response.convertTo[List[UV]]
  }
}
