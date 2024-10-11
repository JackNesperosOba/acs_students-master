package doorStates;

import baseNoStates.Door;

public abstract class DoorStates {
  protected Door door;
  protected String name;

  public DoorStates(Door door) {
    this.door = door;
  }

  public String getName() {return name;}

  public abstract void open();
  public abstract void close();
  public abstract void lock();
  public abstract void unlock();
}
