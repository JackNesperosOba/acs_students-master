package baseNoStates;

import baseNoStates.requests.RequestReader;
import doorStates.DoorStates;
import doorStates.Locked;
import doorStates.Unlocked;
import org.json.JSONObject;


public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorStates state = new Unlocked(this);

  public Door(String id) {
    this.id = id;
    closed = true;
  }

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
        this .state.close();
        break;
      case Actions.LOCK:
        // TODO
        // fall through
        this.state = new Locked(this);
        state.lock();
        break;
      case Actions.UNLOCK:
        // TODO
        // fall through
        this.state = new Unlocked(this);
        break;
      case Actions.UNLOCK_SHORTLY:
        // TODO
        System.out.println("Action " + action + " not implemented yet");
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

  public void setClosed(boolean close) { this.closed = close;}
}
