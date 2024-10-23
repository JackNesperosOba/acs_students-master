package doorStates;

import baseNoStates.Door;

//Abstract class doorState used to define common behaviors that must implement the different states of the door, uses the State Pattern with Lock, Unlock, Unlock_Shortly and Propped classes
public abstract class DoorStates {
  protected Door door;
  protected String name;

  public DoorStates(Door door) {
    this.door = door;
  }
  //returns the current door state
  public String getName() {return name;}

  //Abstract funcitions that inherited classes must implement
  public abstract void open();
  public abstract void close();
  public abstract void lock();
  public abstract void unlock();
  public abstract void unlock_shortly();
}
