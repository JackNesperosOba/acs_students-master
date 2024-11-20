package basenostates;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Space area that inherits the abstract class Area creating a specific space

@SuppressWarnings("checkstyle:MissingJavadocType")
public class Space extends Area {
  private ArrayList<Door> doors = new ArrayList<>();
  private Partition group;
  private static final Logger logger = LoggerFactory.getLogger("fita2.visitor.Area.Space");

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Space(String id, Partition group) {
    super(id);
    this.group = group;
    logger.debug("Space created with id: {} in group: {}", id, group);
  }

  @Override
  public void acceptVisitor(Visitor vis) {
    logger.debug("Visitor accepting for space with id: {}", id);
    vis.visitSpace(this);
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public ArrayList<Door> getDoors() {
    logger.debug("Fetching doors for space with id: {}", id);
    return doors;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void addDoor(Door door) {
    logger.info("Door added to space with id: {}", id);
    doors.add(door);
  }
}
