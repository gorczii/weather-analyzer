package com.weather.scala.common

object ApiConstants {
  val BASE_URL: String = "http://api.openweathermap.org/data/2.5"
  val API_KEY: String = "84b09a0c0afa9235dfbc7e0fc212c15e"
  val APP_ID_PARAM: String = "APPID=" + API_KEY
  val QUERY_PARAM_DELIMITER: String = "/?"

  val getURL: String => String = (resourcePath: String) => {
    if (resourcePath.contains(QUERY_PARAM_DELIMITER) || resourcePath.contains("?"))
      s"$BASE_URL$resourcePath&$APP_ID_PARAM"
    else
      s"$BASE_URL$resourcePath$QUERY_PARAM_DELIMITER$APP_ID_PARAM"
  }
}
