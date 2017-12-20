package directory

trait Directory {
  val entries: Array[DirectoryEntry] = Array()
  def searchEntries(searchParams: Array[SearchEntry]): Array[DirectoryEntry] = ???
}
