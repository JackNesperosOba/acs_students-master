package basenostates;

import basenostates.requests.RequestReader;
import doorstates.Actions;
import doorstates.DoorStates;
import doorstates.Unlocked;
import org.json.JSONObject;

//Class door to process what action needs to do
@SuppressWarnings("checkstyle:MissingJavadocType")
public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorStates state = new Unlocked(this);
  private Space fromSpace;  // A door can do an action from this space to another
  private Space toSpace;  // A door can do an action from a space to this space

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Door(String id, Space fromSpace, Space toSpace) {
    this.id = id;
    this.fromSpace = fromSpace;
    this.toSpace = toSpace;
    closed = true;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        this.state.open();
        break;
      case Actions.CLOSE:
        this.state.close();
        break;
      case Actions.LOCK:
        // TODO
        // fall through
        this.state.lock();
        break;
      case Actions.UNLOCK:
        // TODO
        // fall through
        this.state.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        // TODO
        //  System.out.println("Action " + action + " not implemented yet");
        this.state.unlockShortly();
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    return state.getName();
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }

  public void setState(DoorStates doorStates) {
    this.state = doorStates;
  }

  public void setClosed(boolean close) {
    this.closed = close;
  } //Set the door as closed

  public Space getFromSpace() {
    return fromSpace;
  }

  public Space getToSpace() {
    return toSpace;
  }
}
