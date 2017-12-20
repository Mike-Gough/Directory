package directory

import org.scalatest._

class DirectoryPerformanceSpec extends FlatSpec with Matchers {

  val entriesList: Array[DirectoryEntry] = {
    var index: Int = 1
    var entries: Array[DirectoryEntry] = Range(1,2000000).flatMap(x => {
        index += 3
        Array(
          DirectoryEntry(index + 1,x,"A%s".format(x),"Key Type A","System A"),
          DirectoryEntry(index + 2,x,"%s".format(x),"Key Type B","System A"),
          DirectoryEntry(index + 3,x,"20170101".format(x),"Key Type C","System A")
        )
    }).toArray
    entries
  }

  "The Directory object" should "be performant for large collections using a single search term" in {

    val directory: Directory = new ConcreteDirectory(entriesList)

    val searchParams: Array[SearchEntry] = Array(
      SearchEntry(Some("A1"),Some("Key Type A"),Some("System A"))
    )

    val expectedResults: Array[DirectoryEntry] = Array(
      DirectoryEntry(5,1,"A1","Key Type A","System A"),
      DirectoryEntry(6,1,"1","Key Type B","System A"),
      DirectoryEntry(7,1,"20170101","Key Type C","System A")
    )

    val t0 = System.nanoTime()
    val result = directory.searchEntries(searchParams)
    val t1 = System.nanoTime()
    println("Elapsed test time: %sns".format(t1 - t0))
    result shouldEqual expectedResults
  }

  "The Directory object" should "be performant for large collections using a multiple search terms" in {

    val directory: Directory = new ConcreteDirectory(entriesList)

    val searchParams: Array[SearchEntry] = Array(
      SearchEntry(Some("A1"),Some("Key Type A"),Some("System A")),
      SearchEntry(Some("1"),Some("Key Type B"),Some("System A"))
    )

    val expectedResults: Array[DirectoryEntry] = Array(
      DirectoryEntry(5,1,"A1","Key Type A","System A"),
      DirectoryEntry(6,1,"1","Key Type B","System A"),
      DirectoryEntry(7,1,"20170101","Key Type C","System A")
    )

    val t0 = System.nanoTime()
    val result = directory.searchEntries(searchParams)
    val t1 = System.nanoTime()
    println("Elapsed test time: %sns".format(t1 - t0))
    result shouldEqual expectedResults
  }
}
