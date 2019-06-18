import com.weather.scala.common._
import com.weather.scala.model.CurrentWeatherResponse
import com.weather.scala.model.current._
import com.weather.scala.process.{WeatherAnalysisResult, WeatherAnalyzer}
import com.weather.scala.service.CurrentWeatherService
import org.mockito.Mockito
import org.scalatest.FunSuite

class CurrentWeatherAnalyzerTest extends FunSuite {

  test("WeatherAnalyzer.findMediumMonthlyUV") {

    val mockCurrentWeatherService: CurrentWeatherService = Mockito.mock(classOf[CurrentWeatherService])
    Mockito.when(mockCurrentWeatherService.fetchWeather(ProcessingConstants.ZAMOSC.id.get))
      .thenReturn(zamoscWeather)
    Mockito.when(mockCurrentWeatherService.fetchWeather(ProcessingConstants.WROCLAW.id.get))
      .thenReturn(wroclawWeather)
    Mockito.when(mockCurrentWeatherService.fetchWeather(ProcessingConstants.GDYNIA.id.get))
      .thenReturn(gdyniaWeather)
    val cities: Array[City] = Array(ProcessingConstants.ZAMOSC, ProcessingConstants.WROCLAW, ProcessingConstants.GDYNIA)

    val weatherAnalyzer: WeatherAnalyzer = WeatherAnalyzer(mockCurrentWeatherService)
    val actual = weatherAnalyzer.generateWeatherStatistics(cities)

    val expectedZamoscAnalysis: WeatherAnalysisResult = WeatherAnalysisResult(ProcessingConstants.ZAMOSC,
      MainWeatherValues(19.0, 1020, 77, 19.0, 19.0), Wind(Some(1.5), Some(360)))
    val expectedWroclawAnalysis: WeatherAnalysisResult = WeatherAnalysisResult(ProcessingConstants.WROCLAW,
      MainWeatherValues(16.82, 1020, 56, 13.33, 20.56), Wind(Some(2.1), Some(50)))
    val expectedGdyniaAnalysis: WeatherAnalysisResult = WeatherAnalysisResult(ProcessingConstants.GDYNIA,
      MainWeatherValues(15.75, 1021, 82, 15.0, 16.67), Wind(Some(1.0), None))
    val expected: Array[WeatherAnalysisResult] = Array(expectedZamoscAnalysis, expectedWroclawAnalysis, expectedGdyniaAnalysis)

    assert(expected === actual)
  }

  test("WeatherAnalyzer.findPeakStatistic") {
    val expectedZamoscAnalysis: WeatherAnalysisResult = WeatherAnalysisResult(ProcessingConstants.ZAMOSC,
      MainWeatherValues(19.0, 1025, 77, 19.0, 19.0), Wind(Some(1.5), Some(360)))
    val expectedWroclawAnalysis: WeatherAnalysisResult = WeatherAnalysisResult(ProcessingConstants.WROCLAW,
      MainWeatherValues(16.82, 1020, 56, 13.33, 20.56), Wind(Some(2.1), Some(50)))
    val expectedGdyniaAnalysis: WeatherAnalysisResult = WeatherAnalysisResult(ProcessingConstants.GDYNIA,
      MainWeatherValues(15.75, 1021, 82, 15.0, 16.67), Wind(Some(1.0), None))

    val weatherAnalysisResults: Array[WeatherAnalysisResult] = Array(expectedZamoscAnalysis, expectedWroclawAnalysis, expectedGdyniaAnalysis)
    weatherAnalysisResults.foreach(println)

    val mockCurrentWeatherService: CurrentWeatherService = Mockito.mock(classOf[CurrentWeatherService])
    val weatherAnalyzer: WeatherAnalyzer = WeatherAnalyzer(mockCurrentWeatherService)

    val minWind = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Min, WeatherParamEnum.Wind)
    val maxWind = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Max, WeatherParamEnum.Wind)
    val minPressure = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Min, WeatherParamEnum.Pressure)
    val maxPressure = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Max, WeatherParamEnum.Pressure)
    val minTemp = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Min, WeatherParamEnum.Temperature)
    val maxTemp = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Max, WeatherParamEnum.Temperature)
    val minHumidity = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Min, WeatherParamEnum.Humidity)
    val maxHumidity = weatherAnalyzer.findPeakStatistic(weatherAnalysisResults, MinMax.Max, WeatherParamEnum.Humidity)

    assert(minWind === expectedGdyniaAnalysis)
    assert(maxWind === expectedWroclawAnalysis)
    assert(minPressure === expectedWroclawAnalysis)
    assert(maxPressure === expectedZamoscAnalysis)
    assert(minTemp === expectedGdyniaAnalysis)
    assert(maxTemp === expectedZamoscAnalysis)
    assert(minHumidity === expectedWroclawAnalysis)
    assert(maxHumidity === expectedGdyniaAnalysis)
  }


  val zamoscWeather: CurrentWeatherResponse = CurrentWeatherResponse(
    Coord(23.25, 50.72), List(Weather(800, "Clear", "clear sky", "01n")), "stations",
    MainWeatherValues(19.0, 1020, 77, 19.0, 19.0), Some(10000), Wind(Some(1.5), Some(360)), CloudsInfo(0), 1560805484,
    Sys(1702, 0.0057, "PL", 1560737609, 1560796932), 753866, "Zamosc", 200)
  val wroclawWeather: CurrentWeatherResponse = CurrentWeatherResponse(
    Coord(17.03, 51.1), List(Weather(800, "Clear", "clear sky", "01n")), "stations",
    MainWeatherValues(16.82, 1020, 56, 13.33, 20.56), Some(10000), Wind(Some(2.1), Some(50)), CloudsInfo(0), 1560805603,
    Sys(1715, 0.0081, "PL", 1560738979, 1560798548), 3081368, "Wroclaw", 200)
  val gdyniaWeather: CurrentWeatherResponse = CurrentWeatherResponse(
    Coord(18.53, 54.52), List(Weather(800, "Clear", "clear sky", "01n")), "stations",
    MainWeatherValues(15.75, 1021, 82, 15.0, 16.67), Some(10000), Wind(Some(1.0), None), CloudsInfo(0), 1560805507,
    Sys(1696, 0.0083, "PL", 1560737370, 1560799437), 3099424, "Gdynia", 200)
}
