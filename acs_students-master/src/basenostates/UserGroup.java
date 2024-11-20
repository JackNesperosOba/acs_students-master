package basenostates;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Class UserGroup that classifies the user in different groups to
// know what actions, areas and time they are allowed doing
@SuppressWarnings({"checkstyle:MissingJavadocType", "checkstyle:LineLength"})
public class UserGroup {
  private final String name;
  private final Schedule schedule;  //A group has its own schedule
  private final ArrayList<String> actions;
  private final ArrayList<User> users;
  private final ArrayList<Area> areas;
  private static final Logger logger = LoggerFactory.getLogger("fita1.basenostates.UserGroup");

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public UserGroup(String name, Schedule schedule, ArrayList<String> actions,
                   ArrayList<Area> areas, ArrayList<User> users) {
    this.name = name;
    this.schedule = schedule;
    this.actions = actions;
    this.users = users;
    this.areas = areas;

    logger.info("Creating UserGroup '{}' with schedule {}, actions {}, and areas {}", name, schedule, actions, areas);

    for (User u : users) {
      u.setGroup(this);
    }
  }

  public String getName() {
    return this.name;
  }

  //Loops the list of users to find the user credential in the group
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public User findUserByCredential(String credential) {
    for (User u : users) {
      if (credential.equals(u.getCredential())) {
        logger.info("User with credential '{}' found in UserGroup '{}'.", credential, name);
        return u;
      }
    }
    logger.warn("User with credential '{}' not found in UserGroup '{}'.", credential, name);
    return null;
  }

  //Loops the area list of the group to know if the space is in the list
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public boolean canBeInSpace(Space sp) {
    CanBeInSpaceVisitor visitor = new CanBeInSpaceVisitor(sp);
    for (Area area : areas) {
      area.acceptVisitor(visitor);
      if (visitor.isFound()) {
        logger.info("UserGroup '{}' is allowed to access Space '{}'.", name, sp);
        return true;
      }
    }
    logger.warn("UserGroup '{}' is not allowed to access Space '{}'.", name, sp);
    return false;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public boolean canSendRequest(LocalDateTime now) {
    return this.schedule.canSendRequest(now);
  }

  // Looks if the action is in the list of actions that the group can do
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public boolean canDoAction(String action) {
    return this.actions.contains(action);
  }
}
