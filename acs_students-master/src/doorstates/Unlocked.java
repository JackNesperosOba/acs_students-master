package doorstates;


import basenostates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Unlocked door state that inherits the abstract class doorState allowing
// user to open, close or lock the door
@SuppressWarnings("checkstyle:MissingJavadocType")
public class Unlocked extends DoorStates {
  private static final Logger logger = LoggerFactory
      .getLogger("fita1.doorstates.DoorStates.Unlocked");

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Unlocked(Door door) {
    super(door);
    name = States.UNLOCkED;
    if (door.getId() != null) {
      logger.info("Door {} is now in Unlocked state", door.getId());
    }
  }

  //opens the door if it's closed
  @Override
  public void open() {
    if (!door.isClosed()) {
      logger.warn("Attempt to open door {} which is already open", door.getId());
    } else {
      door.setClosed(false);
      logger.info("Door {} is now open", door.getId());
    }
  }

  //closes the door if it's open
  @Override
  public void close() {
    if (door.isClosed()) {
      logger.warn("Attempt to close door {} which is already closed", door.getId());
    } else {
      door.setClosed(true);
      logger.info("Door {} is now closed", door.getId());
    }
  }

  //Locks the door if the doors is closed changing the state
  @Override
  public void lock() {
    if (door.isClosed()) {
      door.setState(new Locked(door));
      logger.info("Door {} is now locked", door.getId());
    } else {
      logger.warn("Attempt to lock door {} which is open", door.getId());
    }
  }

  //Unlocks the door even if its already unlocked meaning that it stays at the same state
  @Override
  public void unlock() {
    logger.warn("Attempt to unlock door {} which is already unlocked", door.getId());
  }

  @Override
  public void unlockShortly() {
    logger.warn("Attempt to unlock door {} which is already unlocked shortly", door.getId());
  }
}
