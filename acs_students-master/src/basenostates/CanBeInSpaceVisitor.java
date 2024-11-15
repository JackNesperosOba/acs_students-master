package basenostates;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class CanBeInSpaceVisitor implements Visitor {
  private Space spaceToFind;
  private boolean found = false;

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public CanBeInSpaceVisitor(Space spaceToFind) {
    this.spaceToFind = spaceToFind;
  }

  public boolean isFound() {
    return found;
  }

  @Override
  public void visitPartition(Partition partition) {
    for (Area area : partition.getAreas()) {
      area.acceptVisitor(this);
      if (found) {
        break;
      }
    }
  }

  @Override
  public void visitSpace(Space space) {
    if (space.equals(spaceToFind)) {
      found = true;
    }
  }
}
