package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class Partition extends Area{
  private Partition partition;
  private ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, Partition partition) {
    super(id);
    this.partition = partition;
  }

  @Override
  public Area findAreaById(String id) {
    if (this.id.equals(id)) {
      return this;
    }
    // 2. Recorremos todas las áreas (sub-áreas) dentro de la partición actual.
    for (Area area : areas) {
      Area foundArea = area.findAreaById(id);  // Llamada recursiva
      if (foundArea != null) {  // Si encuentra el área, la retorna
        return foundArea;
      }
    }
    // 3. Si no se encuentra el área en esta partición ni en sus subáreas, retorna null.
    return null;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    for (Area area : areas) {
       return area.getSpaces();
    }
    return null;
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();
    for (Area area : areas) {
        doors.addAll(area.getDoorsGivingAccess());
    }
    return doors;
  }

  public void addArea(Area area) {
    this.areas.add(area);
  }
}
