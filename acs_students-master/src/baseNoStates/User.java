package baseNoStates;

import java.time.LocalDateTime;

//Class User that can open or not certain partitions, areas or doors, they know in what group they are
public class User {
  private final String name;
  private final String credential;
  private UserGroup group;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public String getCredential() { return credential; }

  @Override
  public String toString() { return "User{name=" + name + ", credential=" + credential + "}"; }

  public boolean canBeInSpace(Space sp) {
    return this.group.canBeInSpace(sp); //If the user has the space in his list
  }

  public boolean canSendRequest(LocalDateTime now) {
    return this.group.canSendRequest(now);  //If the user can is between the range of time allowed
  }

  public boolean canDoAction(String action) {
    return this.group.canDoAction(action); //If the user can do the action
  }

  public void setGroup(UserGroup group) { this.group = group; }
}
