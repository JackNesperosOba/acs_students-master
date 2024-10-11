package doorStates;

import baseNoStates.Door;

public class Locked extends DoorStates {
  public Locked(Door door) {
    super(door);
    name = "locked";
  }

  @Override
  public void open() {
    System.out.println("Can`t open the door because it's locked");
  }
  @Override
  public void close() {
    System.out.println("It's already closed");
  }
  @Override
  public void lock() {
    door.setState(new Locked(door));
    name = "locked";
  }
  @Override
  public void unlock() {
    door.setState(new Unlocked(door));
    name = "unlocked";
  }
}

