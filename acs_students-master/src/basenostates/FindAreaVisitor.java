package basenostates;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class FindAreaVisitor implements Visitor {
  private String areaId;
  private Area foundArea = null;


  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public FindAreaVisitor(String areaId) {
    this.areaId = areaId;
  }

  public Area getFoundArea() {
    return foundArea;
  }


  @Override
  public void visitPartition(Partition partition) {
    if (partition.id.equals(areaId)) {
      foundArea = partition;
      return;
    }

    for (Area area : partition.getAreas()) {
      area.acceptVisitor(this);
      if (foundArea != null) {
        return;
      }
    }
  }

  @Override
  public void visitSpace(Space space) {
    if (space.id.equals(areaId)) {
      foundArea = space;

    }
  }
}
