package basenostates;

import doorstates.Actions;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

//Creates the groups of users
@SuppressWarnings("checkstyle:MissingJavadocType")
public class DirectoryUserGroups {
  private static ArrayList<UserGroup> usersGroups;
  private static final int THIS_YEAR = 2024;

  //Initialize the actions and schedules that the different users can do
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static void makeUsersGroups() {
    final Area ground_floor = DirectoryAreas.findAreaById("ground_floor");
    final Area floor1 = DirectoryAreas.findAreaById("floor1");
    final Area exterior = DirectoryAreas.findAreaById("exterior");
    final Area stairs = DirectoryAreas.findAreaById("stairs");
    final Area building = DirectoryAreas.findAreaById("building");

    ArrayList<DayOfWeek> monToFri = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
    ArrayList<DayOfWeek> monToSat = (ArrayList<DayOfWeek>) monToFri.clone();
    monToSat.add(DayOfWeek.SATURDAY);
    ArrayList<DayOfWeek> weekEnd = new ArrayList<>(
        Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    ArrayList<DayOfWeek> allDays = (ArrayList<DayOfWeek>) monToFri.clone();
    allDays.addAll(weekEnd);

    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later
    User bernat = new User("Bernat", "12345");
    User blai = new User("Blai", "77532");

    ArrayList<User> usersBlank = new ArrayList<>(Arrays.asList(bernat, blai));
    ArrayList<String> actionsBlank = new ArrayList<>();
    ArrayList<Area> areasBlank = new ArrayList<>();
    UserGroup blank = new UserGroup("blank", null, actionsBlank, areasBlank, usersBlank);

    // employees :
    // Sep. 1 this year to Mar. 1 next year
    // week days 9-17h
    // just shortly unlock
    // ground_floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    User ernest = new User("Ernest", "74984");
    User eulalia = new User("Eulalia", "43295");

    LocalDate firstDayEmployees = LocalDate.of(THIS_YEAR, 9, 1);
    LocalDate lastDayEmployees = LocalDate.of(THIS_YEAR + 1, 3, 1);
    Schedule scheduleEmployees = new Schedule(firstDayEmployees,
        lastDayEmployees, monToFri, LocalTime.of(9, 0), LocalTime.of(17, 0));
    ArrayList<String> actionsEmployees = new ArrayList<>(Arrays.asList(
        Actions.OPEN, Actions.CLOSE, Actions.UNLOCK_SHORTLY));
    ArrayList<Area> areasEmployees = new ArrayList<>(Arrays.asList(
        ground_floor, floor1, stairs, exterior));
    ArrayList<User> usersEmployees = new ArrayList<>(Arrays.asList(ernest, eulalia));
    UserGroup employees = new UserGroup("employees",
        scheduleEmployees, actionsEmployees, areasEmployees, usersEmployees);

    // managers :
    // Sep. 1 this year to Mar. 1 next year
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    User manel = new User("Manel", "95783");
    User marta = new User("Marta", "05827");

    LocalDate firstDayManagers = LocalDate.of(THIS_YEAR, 9, 1);
    LocalDate lastDayManagers = LocalDate.of(THIS_YEAR + 1, 3, 1);
    Schedule scheduleManagers = new Schedule(firstDayManagers,
        lastDayManagers, monToSat, LocalTime.of(8, 0), LocalTime.of(20, 0));
    ArrayList<String> actionsManagers = new ArrayList<>(Arrays.asList(
        Actions.OPEN, Actions.CLOSE, Actions.LOCK, Actions.UNLOCK, Actions.UNLOCK_SHORTLY));
    ArrayList<Area> areasManagers = new ArrayList<>(Arrays.asList(building));
    ArrayList<User> usersManagers = new ArrayList<>(Arrays.asList(manel, marta));
    UserGroup managers = new UserGroup("managers", scheduleManagers,
        actionsManagers, areasManagers, usersManagers);

    // admin :
    // always=Jan. 1 this year to 2100
    // all days of the week
    // all actions
    // all spaces
    User ana = new User("Ana", "11343");

    LocalDate firstDayAdmin = LocalDate.of(THIS_YEAR, 1, 1);
    LocalDate lastDayAdmin = LocalDate.of(2100, 1, 1);
    Schedule scheduleAdmin = new Schedule(firstDayAdmin,
        lastDayAdmin, allDays, LocalTime.of(0, 0), LocalTime.of(23, 59));
    ArrayList<String> actionsAdmin = new ArrayList<>(Arrays.asList(Actions.OPEN,
        Actions.CLOSE, Actions.LOCK, Actions.UNLOCK, Actions.UNLOCK_SHORTLY));
    ArrayList<Area> areasAdmin = new ArrayList<>(Arrays.asList(building));
    ArrayList<User> usersAdmin = new ArrayList<>(Arrays.asList(ana));
    UserGroup admins = new UserGroup("admins", scheduleAdmin, actionsAdmin, areasAdmin, usersAdmin);

    usersGroups = new ArrayList<>(Arrays.asList(blank, employees, managers, admins));
  }


  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static User findUserByCredential(String credential) {
    for (UserGroup groups : usersGroups) {
      if (null != groups.findUserByCredential(credential)) {
        return groups.findUserByCredential(credential);
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }


}


