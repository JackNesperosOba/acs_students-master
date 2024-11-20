package basenostates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Implements the Visitor interface to search for an area (Partition or Space) by its ID.

@SuppressWarnings("checkstyle:MissingJavadocType")
public class FindAreaVisitor implements Visitor {
  private String areaId;
  private Area foundArea = null;
  private static final Logger logger = LoggerFactory.getLogger("fita2.visitor.FindAreaVisitor");


  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public FindAreaVisitor(String areaId) {
    logger.debug("FindAreaVisitor created to find area with id: {}", areaId);
    this.areaId = areaId;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Area getFoundArea() {
    if (foundArea == null) {
      logger.warn("Area with id '{}' not found.", areaId);
    } else {
      logger.debug("Returning found area with id: {}", areaId);
    }
    return foundArea;
  }


  @Override
  public void visitPartition(Partition partition) {
    if (partition.id.equals(areaId)) {
      foundArea = partition;
      logger.info("Found partition with id: {}", areaId);
      return;
    }

    for (Area area : partition.getAreas()) {
      area.acceptVisitor(this);
      if (foundArea != null) {
        return;
      }
    }
    // If no matching area is found in this partition
    if (foundArea == null) {
      logger.warn("No matching area found in partition '{}' for id '{}'.", partition.id, areaId);
    }
  }

  @Override
  public void visitSpace(Space space) {
    if (space.id.equals(areaId)) {
      foundArea = space;
      logger.info("Found space with id: {}", areaId);
    } else {
      logger.debug("Space with id '{}' does not match area id '{}'.", space.id, areaId);
    }
  }
}
