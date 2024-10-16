package baseNoStates;

import org.json.JSONObject;

import java.util.ArrayList;

public abstract class Area {
  protected String id;

  public Area(String id) {
    this.id = id;
  }

  public abstract Area findAreaById(String id);
  public abstract ArrayList<Space> getSpaces();
  public abstract ArrayList<Door> getDoorsGivingAccess();

  @Override
  public String toString() {
    return "Area(" + ", id=" + id + '\'' + ")";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    return json;
  }
}
