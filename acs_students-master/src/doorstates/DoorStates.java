package doorstates;

import basenostates.Door;

//Abstract class doorState used to define common behaviors that
// must implement the different states of the door
//uses the State Pattern with Lock, Unlock, Unlock_Shortly and Propped classes
@SuppressWarnings("checkstyle:MissingJavadocType")
public abstract class DoorStates {
  protected Door door;
  protected String name;

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public DoorStates(Door door) {
    this.door = door;
  }
  //returns the current door state
  @SuppressWarnings("checkstyle:EmptyLineSeparator")
  public String getName() {
    return name;
  }

  //Abstract funcitions that inherited classes must implement
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public abstract void open();

  @SuppressWarnings({"checkstyle:EmptyLineSeparator", "checkstyle:MissingJavadocMethod"})
  public abstract void close();

  @SuppressWarnings({"checkstyle:EmptyLineSeparator", "checkstyle:MissingJavadocMethod"})
  public abstract void lock();

  @SuppressWarnings({"checkstyle:EmptyLineSeparator", "checkstyle:MissingJavadocMethod"})
  public abstract void unlock();

  @SuppressWarnings({"checkstyle:EmptyLineSeparator", "checkstyle:MissingJavadocMethod"})
  public abstract void unlockShortly();
}
