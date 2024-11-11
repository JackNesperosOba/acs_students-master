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
  public Area findAreaById(String id) {
    if (this.id.equals(id)) {
      return this;  // If this space ID is the same has the ID parameter return this space
    }
    return null;
  }

  @Override
  public ArrayList<Space> getSpaces() {   //returns this space as an arrayList
    return new ArrayList<>(Arrays.asList(this));
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {   //returns the doors of the space
    return this.doors;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void addDoor(Door door) {
    doors.add(door);
  }
}
