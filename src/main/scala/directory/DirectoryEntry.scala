package directory

case class DirectoryEntry (id: Int, group: Int, key: String, kind: String, origin: String) {
  override def equals(o: Any) = o match {
    case that: SearchEntry =>
      that.key.map(_.equalsIgnoreCase(this.key)).getOrElse(true) &
      that.kind.map(_.equalsIgnoreCase(this.kind)).getOrElse(true) &
      that.origin.map(_.equalsIgnoreCase(this.origin)).getOrElse(true)
    case that: DirectoryEntry =>
      that.id.equals(this.id) &
      that.group.equals(this.group) &
      that.key.equalsIgnoreCase(this.key) &
      that.kind.equalsIgnoreCase(this.kind) &
      that.origin.equalsIgnoreCase(this.origin)
    case _ => false
  }
  override def hashCode = ("%s%s%s".format(key, kind, origin)).hashCode
}
