package directory

import scala.collection.parallel.immutable
import scala.collection.mutable.HashMap

class ConcreteDirectory(val newEntries: Array[DirectoryEntry]) extends Directory {

  override val entries: Array[DirectoryEntry] = newEntries
  val entriesByGroup: Map[Int,Array[DirectoryEntry]] = entries.groupBy(_.group)

  override def searchEntries(searchParams: Array[SearchEntry]): Array[DirectoryEntry] = {
    val matchedGroups: Iterable[Int] = entriesByGroup.collect{
      case x if searchParams.forall(y => x._2.exists(_.equals(y))) => x._1
    }
    matchedGroups.flatMap(x => entriesByGroup.get(x)).flatten.toArray
  }
}
