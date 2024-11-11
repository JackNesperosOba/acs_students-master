package basenostates;

import java.util.ArrayList;
import java.util.Arrays;

//Creates partition, areas and doors
@SuppressWarnings("checkstyle:MissingJavadocType")
public class DirectoryAreas {
  private static ArrayList<Door> allDoors;
  private static Area rootArea;

  //Initialize the different partitions with areas and doors
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static void makeAreas() {
    Partition building = new Partition("building", null);
    Partition basement = new Partition("basement", building);
    Partition groundFloor = new Partition("ground_floor", building);
    Partition floor1 = new Partition("floor1", building);
    building.addArea(basement);
    building.addArea(groundFloor);
    building.addArea(floor1);

    Space parking = new Space("parking", basement);
    Space hall = new Space("hall", groundFloor);
    Space room1 = new Space("room1", groundFloor);
    basement.addArea(parking);
    groundFloor.addArea(hall);
    groundFloor.addArea(room1);
    Space room2 = new Space("room2", groundFloor);
    groundFloor.addArea(room2);
    Space room3 = new Space("room3", floor1);
    floor1.addArea(room3);
    Space corridor = new Space("corridor", floor1);
    floor1.addArea(corridor);
    Space it = new Space("IT", floor1);
    floor1.addArea(it);
    Space stairs = new Space("stairs", building);
    building.addArea(stairs);
    Space exterior = new Space("exterior", building);
    building.addArea(exterior);

    rootArea = building;

    //basement
    Door d1 = new Door("D1", exterior, parking);
    Door d2 = new Door("D2", stairs, parking);
    parking.addDoor(d1);
    parking.addDoor(d2);
    //ground_floor
    Door d3 = new Door("D3", exterior, hall);
    Door d4 = new Door("D4", stairs, hall);
    Door d5 = new Door("D5", hall, room1);
    hall.addDoor(d3);
    hall.addDoor(d4);
    room1.addDoor(d5);
    Door d6 = new Door("D6", hall, room2);
    room2.addDoor(d6);
    //first floor
    Door d7 = new Door("D7", stairs, corridor);
    Door d8 = new Door("D8", corridor, room3);
    Door d9 = new Door("D9", corridor, it);
    room3.addDoor(d8);
    corridor.addDoor(d7);
    it.addDoor(d9);
    stairs.addDoor(d2);
    stairs.addDoor(d4);
    stairs.addDoor(d7);
    exterior.addDoor(d1);
    exterior.addDoor(d3);

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static Area findAreaById(String id) {
    return rootArea.findAreaById(id);
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  // this is needed by RequestRefresh
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }

}
