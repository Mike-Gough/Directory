package directory

import org.scalatest._

class DirectorySpec extends FlatSpec with Matchers {

  val entriesList: Array[DirectoryEntry] = Array(
    DirectoryEntry(1,1,"A1","Key Type A","System A"),
    DirectoryEntry(2,1,"222222222","Key Type B","System A"),
    DirectoryEntry(3,1,"20170101","Key Type C","System A"),
    DirectoryEntry(4,2,"A2","Key Type A","System B"),
    DirectoryEntry(5,3,"A3","Key Type A","System C"),
    DirectoryEntry(6,3,"111111111","Key Type B","System C"),
    DirectoryEntry(7,3,"20170101","Key Type C","System C")
  )

  "The Directory object" should "allow searching on a single field" in {

    val directory: Directory = new ConcreteDirectory(entriesList)

    val searchParams: Array[SearchEntry] = Array(
      SearchEntry(None,None,Some("System A"))
    )

    val expectedResults: Array[DirectoryEntry] = Array(
      DirectoryEntry(1,1,"A1","Key Type A","System A"),
      DirectoryEntry(2,1,"222222222","Key Type B","System A"),
      DirectoryEntry(3,1,"20170101","Key Type C","System A")
    )

    directory.searchEntries(searchParams) shouldEqual expectedResults
  }

  "The Directory object" should "allow searching on multiple fields" in {

    val directory: Directory = new ConcreteDirectory(entriesList)

    val searchParams: Array[SearchEntry] = Array(
      SearchEntry(Some("222222222"),None,Some("System A"))
    )

    val expectedResults: Array[DirectoryEntry] = Array(
      DirectoryEntry(1,1,"A1","Key Type A","System A"),
      DirectoryEntry(2,1,"222222222","Key Type B","System A"),
      DirectoryEntry(3,1,"20170101","Key Type C","System A")
    )

    directory.searchEntries(searchParams) shouldEqual expectedResults
  }

  "The Directory object" should "find entries by a one search criteria" in {

    val directory: Directory = new ConcreteDirectory(entriesList)

    val searchParams: Array[SearchEntry] = Array(
      SearchEntry(Some("111111111"),Some("Key Type B"),None)
    )

    val expectedResults: Array[DirectoryEntry] = Array(
      DirectoryEntry(5,3,"A3","Key Type A","System C"),
      DirectoryEntry(6,3,"111111111","Key Type B","System C"),
      DirectoryEntry(7,3,"20170101","Key Type C","System C")
    )

    directory.searchEntries(searchParams) shouldEqual expectedResults
  }

  "The Directory object" should "find entries by a multiple search criteria" in {

    val directory: Directory = new ConcreteDirectory(entriesList)

    val searchParams: Array[SearchEntry] = Array(
      SearchEntry(Some("111111111"),Some("Key Type B"),None),
      SearchEntry(Some("20170101"),Some("Key Type C"),None)
    )

    val expectedResults: Array[DirectoryEntry] = Array(
      DirectoryEntry(5,3,"A3","Key Type A","System C"),
      DirectoryEntry(6,3,"111111111","Key Type B","System C"),
      DirectoryEntry(7,3,"20170101","Key Type C","System C")
    )

    directory.searchEntries(searchParams) shouldEqual expectedResults
  }
}
