package doorStates;

import baseNoStates.Door;

public class Unlocked extends DoorStates {
  public Unlocked(Door door) {
    super(door);
    name = "unlocked";
  }

  @Override
  public void open() {
    if (!door.isClosed()) {
      System.out.println("Can't open door " + door.getId() + " because it's already ");
    } else {
      door.setClosed(false);
    }
  }
  @Override
  public void close() {
    if (door.isClosed()) {
      System.out.println("Can't close door " + door.getId() + " because it's already closed");
    } else {
      door.setClosed(true);
    }
  }
  @Override
  public void lock() {
    if (door.isClosed()) {
      door.setState(new Locked(door));
      name = "locked";
    }
  }
  @Override
  public void unlock() {
    door.setState(new Unlocked(door));
    name = "unlocked";
  }
}
