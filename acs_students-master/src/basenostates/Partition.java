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
  public Area findAreaById(String id) {
    if (this.id.equals(id)) {
      return this;
    }
    // Loop through all areas inside the current group area.
    for (Area area : areas) {
      Area foundArea = area.findAreaById(id);  //Recursive
      if (foundArea != null) {
        return foundArea;
      }
    }
    // Returns null if there is no area founded.
    return null;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : areas) {
      spaces.addAll(area.getSpaces());
    }
    return spaces;
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();
    for (Area area : areas) {
      //Loops through list of areas in the partition
      // to get the all doors and return it as a list of doors
      doors.addAll(area.getDoorsGivingAccess());
    }
    return doors;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void addArea(Area area) {
    this.areas.add(area);
  }
}
