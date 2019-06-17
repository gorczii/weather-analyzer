import com.weather.scala.common.ProcessingConstants
import com.weather.scala.model.UV
import com.weather.scala.process.{UvAnalyzer, UvStatistics}
import com.weather.scala.service.UVService
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.scalatest.FunSuite

class UvAnalyzerTest extends FunSuite {


  test("UvAnalyzer.findMediumMonthlyUV") {

    val mockUvService: UVService = Mockito.mock(classOf[UVService])
    Mockito.when(mockUvService.fetchHistoricalUV(any[Double], any[Double], any[Long], any[Long]))
      .thenReturn(historicalUvData)

    val uvAnalyzer: UvAnalyzer = UvAnalyzer(mockUvService)

    val actual = uvAnalyzer.findMediumMonthlyUV(ProcessingConstants.LONDON)
    val expectedJuly = UvStatistics(ProcessingConstants.LONDON, 6, None, 7.0)
    val expectedAugust = UvStatistics(ProcessingConstants.LONDON, 7, None, 5.0)

    assert(actual(6) === expectedJuly)
    assert(actual(7) === expectedAugust)

  }

  val historicalUvData: List[UV] = List(
    UV(-0.118092, 51.509865, "2017-07-01T12:00:00Z", 1498910400, 10.0),
    UV(-0.118092, 51.509865, "2017-07-08T12:00:00Z", 1499515200, 6.0),
    UV(-0.118092, 51.509865, "2017-07-12T12:00:00Z", 1499860800, 8.0),
    UV(-0.118092, 51.509865, "2017-07-13T12:00:00Z", 1499947200, 6.5),
    UV(-0.118092, 51.509865, "2017-07-21T12:00:00Z", 1500638400, 4.5),
    UV(-0.118092, 51.509865, "2017-08-02T12:00:00Z", 1501675200, 4.0),
    UV(-0.118092, 51.509865, "2017-08-09T12:00:00Z", 1502280000, 6.5),
    UV(-0.118092, 51.509865, "2017-08-10T12:00:00Z", 1502366400, 7.5),
    UV(-0.118092, 51.509865, "2017-08-21T12:00:00Z", 1503316800, 3.0),
    UV(-0.118092, 51.509865, "2017-08-29T12:00:00Z", 1504008000, 4.0)
  )
}
