package baseNoStates;

import java.util.ArrayList;

public class User {
  private final String name;
  private final String credential;
  private ArrayList<Area> areas = new ArrayList<>();

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public String getCredential() {
    return credential;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }

  private ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : areas) {
      spaces.addAll(area.getSpaces());
    }
    return spaces;
  }

  public boolean canBeInSpace(Space sp) {
    return this.areas.contains(sp);
  }

  public void addArea(Area area) {
    areas.add(area);
  }
}
