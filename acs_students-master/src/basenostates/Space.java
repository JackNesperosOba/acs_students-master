package basenostates;

import java.util.ArrayList;
import java.util.Arrays;

//Space area that inherits the abstract class Area creating a specific space
@SuppressWarnings("checkstyle:MissingJavadocType")
public class Space extends Area {
  private ArrayList<Door> doors = new ArrayList<>();
  private Partition group;

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Space(String id, Partition group) {
    super(id);
    this.group = group;
  }

  @Override
  public void acceptVisitor(Visitor vis) {
    vis.visitSpace(this);
  }

  public ArrayList<Door> getDoors() {
    return doors;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void addDoor(Door door) {
    doors.add(door);
  }
}
