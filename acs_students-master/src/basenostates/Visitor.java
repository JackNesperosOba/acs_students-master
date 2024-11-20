package basenostates;


//Defines the visit operations that
//can be performed on different types of areas (Partition and Space).

@SuppressWarnings("checkstyle:MissingJavadocType")
public interface Visitor {
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  void visitPartition(Partition partition);

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  void visitSpace(Space space);

}
