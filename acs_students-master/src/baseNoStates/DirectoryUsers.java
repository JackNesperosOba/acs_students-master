package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryUsers {
  private static final ArrayList<User> users = new ArrayList<>();

  public static void makeUsers() {
    //TODO: make user groups according to the specifications in the comments, because
    // now all are the same

    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later
    users.add(new User("Bernat", "12345"));
    users.add(new User("Blai", "77532"));

    // employees :
    // Sep. 1 this year to Mar. 1 next year
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    users.add(new User("Ernest", "74984"));
    users.add(new User("Eulalia", "43295"));

    // managers :
    // Sep. 1 this year to Mar. 1 next year
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    users.add(new User("Manel", "95783"));
    users.add(new User("Marta", "05827"));

    // admin :
    // always=Jan. 1 this year to 2100
    // all days of the week
    // all actions
    // all spaces
    users.add(new User("Ana", "11343"));

    ArrayList<String> areas = new ArrayList<>(Arrays.asList("building", "basement", "ground_floor", "floor1", "stairs", "exterior", "parking", "hall", "room1", "room2", "room3", "corridor", "IT"));
    for (User user : users) {
      if (user.getCredential().equals("11343") || user.getCredential().equals("95783") || user.getCredential().equals("05827")) {
        for (String id : areas) {
          Area space = DirectoryAreas.findAreaById(id);
          user.addArea(space);
        }
      }
      else if (user.getCredential().equals("74984") || user.getCredential().equals("43295"))
      {
        for (String id : areas) {
          if (!id.equals("parking")) {
            Area space = DirectoryAreas.findAreaById(id);
            user.addArea(space);
          }
        }
      }
    }
  }

  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }

}
