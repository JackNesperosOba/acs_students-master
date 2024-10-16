package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class Space extends Area{
  private ArrayList<Door> doors = new ArrayList<>();
  private Partition group;

  public Space(String id, Partition group) {
    super(id);
    this.group = group;
  }

  @Override
  public Area findAreaById(String id) {
    if (this.id.equals(id)) {
      return this;  // Si el ID coincide con el espacio actual, retorna este espacio.
    }
    return null;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    return new ArrayList<>(Arrays.asList(this));
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return this.doors;
  }

  public void addDoor(Door door) {
    doors.add(door);
  }
}
