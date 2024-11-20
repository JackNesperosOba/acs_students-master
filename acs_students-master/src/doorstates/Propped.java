package doorstates;

import basenostates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Propped door state that inherits the abstract class doorState allowing
// user only to close and lock the door because it was Unlocked Shortly
@SuppressWarnings("checkstyle:MissingJavadocType")
public class Propped extends DoorStates {
  private static final Logger logger = LoggerFactory
      .getLogger("fita1.doorstates.DoorStates.Propped");

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Propped(Door door) {
    super(door);
    name = States.PROPPED;
    logger.warn("Door {} is now in Propped state", door.getId());
  }

  @Override
  public void open() {
    logger.info("Door {} is already open", door.getId());
  }

  //Closes and locks the door
  @Override
  public void close() {
    logger.info("Door {} is being closed and will transition to Locked state", door.getId());
    door.setClosed(true);
    door.setState(new Locked(door));
  }

  @Override
  public void lock() {
    logger.warn("Cannot lock door {} while it's propped", door.getId());
  }

  @Override
  public void unlock() {
    logger.info("Door {} is already unlocked", door.getId());
  }

  @Override
  public void unlockShortly() {
    logger.warn("Door {} cannot be temporarily unlocked because it is propped", door.getId());
  }
}
