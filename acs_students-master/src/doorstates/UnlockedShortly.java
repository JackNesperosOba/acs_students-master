package doorstates;

import basenostates.Door;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Unlocked_Shortly door state that inherits the abstract class doorState
//and implements the observer pattern with the clock
//In this state a user can open or close a door during the limited time defined
@SuppressWarnings("checkstyle:MissingJavadocType")
public class UnlockedShortly extends DoorStates implements Observer {
  private static final Logger logger = LoggerFactory
      .getLogger("fita1.doorstates.DoorStates.UnlockedShortly");
  private static final int MAX_TIME = 10;
  private static final Clock clock = Clock.getInstance(MAX_TIME); // a unique clock is shared by all
  // X objects because of static
  private final LocalDateTime timeUnlocked;

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public UnlockedShortly(Door door) {
    super(door);
    name = States.UNCLOKED_SHORTLY;
    timeUnlocked = LocalDateTime.now();
    clock.addObserver(this);
    clock.start();
    logger.info("Door {} starts Unlocked Shortly at {}", door.getId(), timeUnlocked);
  }

  @Override
  public void update(Observable o, Object arg) {
    LocalDateTime now = (LocalDateTime) arg;
    Duration duration = Duration.between(timeUnlocked, now);

    long diff = Math.abs(duration.toSeconds());
    if (diff > MAX_TIME) {
      logger.info("Door {} has been unlocked shortly for {} seconds", door.getId(), MAX_TIME);
      if (door.isClosed()) {
        clock.deleteObserver(this);
        logger.info("Door {} is closed, transitioning from "
            + "Unlocked Shortly to Locked state", door.getId());
        door.setState(new Locked(door));
      } else {
        clock.deleteObserver(this);
        logger.warn("Door {} is open, transitioning from "
            + "Unlocked Shortly to Propped state", door.getId());
        door.setState(new Propped(door));
      }
    }
  }

  @Override
  public void open() {
    if (!door.isClosed()) {
      logger.warn("Can't open door {} because it's already open", door.getId());
    } else {
      door.setClosed(false);
      logger.info("Door {} is now open", door.getId());
    }
  }

  @Override
  public void close() {
    if (door.isClosed()) {
      logger.warn("Can't close door {} because it's already closed", door.getId());
    } else {
      door.setClosed(true);
      logger.info("Door {} is now closed", door.getId());
    }
  }

  @Override
  public void lock() {
    logger.warn("Cannot lock door {} while it's in Unlocked Shortly state", door.getId());
  }

  @Override
  public void unlock() {
    logger.info("Door {} is already temporarily unlocked", door.getId());
  }

  @Override
  public void unlockShortly() {
    logger.warn("Door {} can't be temporarily unlocked "
        + "because it is already in Unlocked Shortly state", door.getId());
  }
}
