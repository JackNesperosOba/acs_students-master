package basenostates;

import java.util.ArrayList;
import org.json.JSONObject;

//Abstract class Area used to define common behaviors that must implement the areas
// that a user can be in, using the Composite pattern with Partition and Space classes
@SuppressWarnings("checkstyle:MissingJavadocType")
public abstract class Area {
  protected String id;

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Area(String id) {
    this.id = id;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public abstract void acceptVisitor(Visitor vis);

  @Override
  public String toString() {
    return "Area(" + ", id=" + id + '\'' + ")";
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    return json;
  }
}
