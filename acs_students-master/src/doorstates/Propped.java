package doorstates;

import basenostates.Door;

//Propped door state that inherits the abstract class doorState allowing
// user only to close and lock the door because it was Unlocked Shortly
@SuppressWarnings("checkstyle:MissingJavadocType")
public class Propped extends DoorStates {
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Propped(Door door) {
    super(door);
    name = States.PROPPED;
    System.out.println("Door " + door.getId() + " is now Propped");
  }

  @Override
  public void open() {
    System.out.println("Door " + door.getId() + " is already open");
  }

  //Closes and locks the door
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
  public void unlockShortly() {
    System.out.println("Door " + door.getId()
        + " can't be temporarily unlocked because it is propped");
  }
}
