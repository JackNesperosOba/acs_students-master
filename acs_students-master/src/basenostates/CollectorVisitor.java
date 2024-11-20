package basenostates;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//Implements the Visitor interface to collect all spaces and doors in a specified area.

@SuppressWarnings("checkstyle:MissingJavadocType")
public class CollectorVisitor implements Visitor {
  private ArrayList<Space> spaces = new ArrayList<>();
  private ArrayList<Door> doors = new ArrayList<>();
  private String areaId;
  private static final Logger logger = LoggerFactory
      .getLogger("fita2.visitor.CollectorVisitor");

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public CollectorVisitor(String areaId) {
    logger.debug("CollectorVisitor created for area with id: {}", areaId);
    this.areaId = areaId;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public ArrayList<Space> getSpaces() {
    logger.debug("Fetching spaces for area with id: {}", areaId);
    return spaces;
  }

  @SuppressWarnings({"checkstyle:EmptyLineSeparator", "checkstyle:MissingJavadocMethod"})
  public ArrayList<Door> getDoors() {
    logger.debug("Fetching doors for area with id: {}", areaId);
    return doors;
  }


  @Override
  public void visitPartition(Partition partition) {
    for (Area area : partition.getAreas()) {
      area.acceptVisitor(this);
      logger.debug("Visiting partition with id: {}", partition);
    }

  }

  @Override
  public void visitSpace(Space space) {
    spaces.add(space);
    doors.addAll(space.getDoors());
    logger.info("Collected space with id: {} and its doors", space);
  }


}
