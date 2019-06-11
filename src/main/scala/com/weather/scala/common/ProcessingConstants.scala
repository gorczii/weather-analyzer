package com.weather.scala.common

object ProcessingConstants {
  val LONDON: City = City("London", Coord(51.509865, -0.118092), None) 
  val LODZ: City = City("Lodz", Coord(51.75, 19.46667), None) 
  val ROME: City = City("Rome", Coord(41.89193, 12.51133), None) 
  val MOSCOW: City = City("Moscow", Coord(55.751244, 37.618423), None) 
  val ATHENS: City = City("Athens", Coord(38.0, 23.716667), None) 
  val CAMBODIA: City = City("Cambodia", Coord(-122.37, 37.75), None) 

  val MRAGOWO: City = City("Mragowo", Coord(53.869167, 21.299722), Some(764312))
  val GDYNIA: City = City("Gdynia", Coord(54.519167, 18.539444), Some(3099424))
  val ZAKOPANE: City = City("Zakopane", Coord(49.299444, 19.951944), Some(3080866))
  val KOLOBRZEG: City = City("Kolobrzeg", Coord(54.177778, 15.576944), Some(3095795))
  val WROCLAW: City = City("Wroclaw", Coord(51.099998, 17.033331), Some(3081368))
  val ZAMOSC: City = City("Zamosc", Coord(50.723141, 23.251961), Some(753866))

  val polishTowns: Array[City] = Array(ZAMOSC, WROCLAW, KOLOBRZEG, ZAKOPANE, GDYNIA, MRAGOWO)

}