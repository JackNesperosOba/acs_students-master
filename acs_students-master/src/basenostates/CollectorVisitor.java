package basenostates;

import java.util.ArrayList;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class CollectorVisitor implements Visitor {
  private ArrayList<Space> spaces = new ArrayList<>();
  private ArrayList<Door> doors = new ArrayList<>();
  private String areaId;

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public CollectorVisitor(String areaId) {
    this.areaId = areaId;
  }

  public ArrayList<Space> getSpaces() {
    return spaces;

  }

  @SuppressWarnings("checkstyle:EmptyLineSeparator")
  public ArrayList<Door> getDoors() {
    return doors;
  }


  @Override
  public void visitPartition(Partition partition) {
    for (Area area : partition.getAreas()) {
      area.acceptVisitor(this);
    }

  }

  @Override
  public void visitSpace(Space space) {
    spaces.add(space);
    doors.addAll(space.getDoors());
  }


}
