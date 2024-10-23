package doorStates;

import baseNoStates.Door;

public class Propped extends DoorStates{

  public Propped(Door door) {
    super(door);
    name = States.PROPPED;
    System.out.println("Door " + door.getId() + " is now Propped");
  }

  @Override
  public void open() {
    System.out.println("Door " + door.getId() + " is already open");
  }

  @Override
  public void close() {
    System.out.println("Door " + door.getId() + " is closed");
    door.setClosed(true);
    door.setState(new Locked(door));
  }

  @Override
  public void lock() {
    System.out.println("Can't lock the door while it's propped");
  }

  @Override
  public void unlock() {
    System.out.println("Door " + door.getId() + " is already unlocked");
  }

  @Override
  public void unlock_shortly() {
    System.out.println("Door " + door.getId() + " can't be temporarily unlocked because it is propped");
  }
}
