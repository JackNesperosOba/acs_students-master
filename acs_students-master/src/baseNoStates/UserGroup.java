package baseNoStates;

import java.time.LocalDateTime;
import java.util.ArrayList;

//Class UserGroup that classifies the user in different groups to know what actions, areas and time they are allowed doing
public class UserGroup {
  private final String name;
  private final Schedule schedule;
  private final ArrayList<String> actions;
  private final ArrayList<User> users;
  private final ArrayList<Area> areas;

  public UserGroup(String name, Schedule schedule, ArrayList<String> actions, ArrayList<Area> areas, ArrayList<User> users) {
    this.name = name;
    this.schedule = schedule;
    this.actions = actions;
    this.users = users;
    this.areas = areas;

    for (User u : users) {
      u.setGroup(this);
    }
  }

  public String getName() { return this.name; }

  //Loops the list of users to find the user credential in the group
  public User findUserByCredential(String credential) {
    for (User u : users) {
      if(credential.equals(u.getCredential()))
        return u;
    }
    return null;
  }

  private ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : areas) {
      spaces.addAll(area.getSpaces());
    }
    return spaces;
  }

  //Loops the area list of the group to know if the space is in the list
  public boolean canBeInSpace(Space sp) {
    for (Area area : areas) {
      if (area.getSpaces().contains(sp))
        return true;
    }
    return false;
  }

  public boolean canSendRequest(LocalDateTime now) {
    return this.schedule.canSendRequest(now);
  }

  // Looks if the action is in the list of actions that the group can do
  public boolean canDoAction(String action) {
    return this.actions.contains(action);
  }
}
