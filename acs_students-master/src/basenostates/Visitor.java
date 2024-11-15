package basenostates;

@SuppressWarnings("checkstyle:MissingJavadocType")
public interface Visitor {
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  void visitPartition(Partition partition);

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  void visitSpace(Space space);

}
