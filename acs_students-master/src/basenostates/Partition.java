package basenostates;

import java.util.ArrayList;

//Partition area that inherits the abstract class Area
// including a spaces, group of spaces or partitions
@SuppressWarnings("checkstyle:MissingJavadocType")
public class Partition extends Area {
  private Partition partition;
  private ArrayList<Area> areas = new ArrayList<>();

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Partition(String id, Partition partition) {
    super(id);
    this.partition = partition;
  }

  @Override
  public void acceptVisitor(Visitor vis) {
    vis.visitPartition(this);
  }

  public ArrayList<Area> getAreas() {
    return areas;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void addArea(Area area) {
    this.areas.add(area);
  }
}
