package doorstates;

import basenostates.Door;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

//Unlocked_Shortly door state that inherits the abstract class doorState
// and implements the observer pattern with the clock
//In this state a user can open or close a door during the limited time defined
@SuppressWarnings("checkstyle:MissingJavadocType")
public class UnlockedShortly extends DoorStates implements Observer {
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
    System.out.println("Door " + this.door.getId() + " starts Unlocked Shortly at " + timeUnlocked);
  }

  @Override
  public void update(Observable o, Object arg) {
    LocalDateTime now = (LocalDateTime) arg;
    Duration duration = Duration.between(timeUnlocked, now);

    long diff = Math.abs(duration.toSeconds());
    if (diff > MAX_TIME) {
      System.out.println("Door " + this.door.getId() + " closed is "
          + this.door.isClosed() + MAX_TIME + " seconds unlocked");
      if (door.isClosed()) {
        clock.deleteObserver(this);
        System.out.println("Door " + this.door.getId()
            + " change state from Unlocked Shortly to Locked");
        door.setState(new Locked(door));
      } else {
        clock.deleteObserver(this);
        System.out.println("Door " + this.door.getId()
            + " change state from Unlocked Shortly to Propped");
        door.setState(new Propped(door));
      }
    }
  }

  @Override
  public void open() {
    if (!door.isClosed()) {
      System.out.println("Can't open door " + door.getId() + " because it's already");
    } else {
      door.setClosed(false);
    }
  }

  @Override
  public void close() {
    if (door.isClosed()) {
      System.out.println("Can't close door " + door.getId() + " because it's already closed");
    } else {
      door.setClosed(true);
    }
  }

  @Override
  public void lock() {
    System.out.println("Cannot lock the door while it's in Unlocked Shortly state");
  }

  @Override
  public void unlock() {
    System.out.println("Door is already unlocked temporarily");
  }

  @Override
  public void unlockShortly() {
    System.out.println("Door " + door.getId()
        + " can't be temporarily unlocked because it is already unlocked shortly");
  }
}
