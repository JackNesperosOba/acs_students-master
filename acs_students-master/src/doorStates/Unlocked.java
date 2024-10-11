package doorStates;

import baseNoStates.Door;

public class Unlocked extends DoorStates {
  public Unlocked(Door door) {
    super(door);
    name = "unlocked";
  }

  @Override
  public void open() {
  }
  @Override
  public void close() {

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
