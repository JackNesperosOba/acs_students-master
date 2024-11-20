package doorstates;

import basenostates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Locked door state that inherits the abstract class doorState allowing user only to unlock the door
@SuppressWarnings("checkstyle:MissingJavadocType")
public class Locked extends DoorStates {
  private static final Logger logger = LoggerFactory
      .getLogger("fita1.doorstates.DoorStates.Locked");

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Locked(Door door) {
    super(door);
    name = States.LOCKED;
    logger.info("Door {} is now in Locked state", door.getId());
  }

  @Override
  public void open() {
    logger.warn("Can't open door {} because it's locked", door.getId());
  }

  @Override
  public void close() {
    logger.warn("Attempt to close door {} which is already closed", door.getId());
  }

  //Locks the door even if its already locked meaning that it stays at the same state
  @Override
  public void lock() {
    logger.warn("Can't lock door {} because it's already locked", door.getId());
  }

  //Unlocks the door changing the state changing the state
  @Override
  public void unlock() {
    logger.info("Unlocking door {}", door.getId());
    door.setState(new Unlocked(door));
  }

  //Change the door state to unlock_shortly
  @Override
  public void unlockShortly() {
    logger.info("Setting door {} to unlock shortly", door.getId());
    door.setState(new UnlockedShortly(door));
  }

}

