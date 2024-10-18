package doorStates;

import baseNoStates.Door;

//Locked door state that inherits the abstract class doorstate allowing user only to unlock the door
public class Locked extends DoorStates {
  public Locked(Door door) {
    super(door);
    name = "locked";
  }

  //
  @Override
  public void open() {
    System.out.println("Can`t open " + door.getId() + " door because it's locked");
  }
  //
  @Override
  public void close() {
    System.out.println( door.getId() +  " is already closed");
  }
  //Locks the door even if its already locked meaning that it stays at the same state
  @Override
  public void lock() {
    System.out.println("Can`t lock " + door.getId() + " door because it's already locked");
    name = "locked";
  }
  //Unlocks the door changing the state changing the state
  @Override
  public void unlock() {
    door.setState(new Unlocked(door));
    name = "unlocked";
  }
}

