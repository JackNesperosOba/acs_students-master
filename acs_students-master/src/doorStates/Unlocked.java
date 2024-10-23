package doorStates;

import baseNoStates.Door;

//Unlocked door state that inherits the abstract class doorState allowing user to open, close or lock the door
public class Unlocked extends DoorStates {
  public Unlocked(Door door) {
    super(door);
    name = States.UNLOCkED;
  }

  //opens the door if it's closed
  @Override
  public void open() {
    if (!door.isClosed()) {
      System.out.println("Can't open door " + door.getId() + " because it's already ");
    } else {
      door.setClosed(false);
    }
  }
  //closes the door if it's open
  @Override
  public void close() {
    if (door.isClosed()) {
      System.out.println("Can't close door " + door.getId() + " because it's already closed");
    } else {
      door.setClosed(true);
    }
  }
  //Locks the door if the doors is closed changing the state
  @Override
  public void lock() {
    if (door.isClosed()) {
      door.setState(new Locked(door));
      name = "locked";
    }
  }
  //Unlocks the door even if its already unlocked meaning that it stays at the same state
  @Override
  public void unlock() {
    door.setState(new Unlocked(door));
    name = "unlocked";
  }

  @Override
  public void unlock_shortly() {

  }
}
