package doorStates;

import baseNoStates.Door;

public class Locked extends DoorStates {
  public Locked(Door door) {
    super(door);
    name = "locked";
  }

  @Override
  public void open() {
    System.out.println("Can`t open " + door.getId() + " door because it's locked");
  }
  @Override
  public void close() {
    System.out.println( door.getId() +  " is already closed");
  }
  @Override
  public void lock() {
    System.out.println("Can`t lock " + door.getId() + " door because it's already locked");
    name = "locked";
  }
  @Override
  public void unlock() {
    door.setState(new Unlocked(door));
    name = "unlocked";
  }
}

