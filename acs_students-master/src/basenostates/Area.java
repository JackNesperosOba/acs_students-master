package basenostates;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Abstract class Area used to define common behaviors that must implement the areas
// that a user can be in, using the Composite and Visitor patterns
// with Partition and Space classes and Visitor interface

@SuppressWarnings("checkstyle:MissingJavadocType")
public abstract class Area {
  protected String id;
  private static final Logger logger = LoggerFactory.getLogger("fita2.visitor.Area");

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Area(String id) {
    this.id = id;
    logger.debug("Area created with id: {}", id);
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public abstract void acceptVisitor(Visitor vis);

  @Override
  public String toString() {
    logger.debug("toString() called on Area with id: {}", id);
    return "Area(" + ", id=" + id + '\'' + ")";
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public JSONObject toJson() {
    logger.debug("Converted Area with id: {} to JSON", id);
    JSONObject json = new JSONObject();
    json.put("id", id);
    return json;
  }
}
