package basenostates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Implements the Visitor interface to check if a specified space exists in the area tree.

@SuppressWarnings("checkstyle:MissingJavadocType")
public class CanBeInSpaceVisitor implements Visitor {
  private Space spaceToFind;
  private boolean found = false;
  private static final Logger logger = LoggerFactory
      .getLogger("fita2.visitor.CanBeInSpaceVisitor");

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public CanBeInSpaceVisitor(Space spaceToFind) {
    logger.debug("CanBeInSpaceVisitor created to find space with id: {}", spaceToFind);
    this.spaceToFind = spaceToFind;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public boolean isFound() {
    logger.debug("Checking if space with id: {} was found: {}", spaceToFind, found);
    return found;
  }


  //Visits a Partition object and recursively searches for the target space in its subareas.
  @Override
  public void visitPartition(Partition partition) {
    logger.debug("Visiting partition with id: {}", partition);
    for (Area area : partition.getAreas()) {
      area.acceptVisitor(this);
      if (found) {
        break;
      }
    }
  }

  @Override
  public void visitSpace(Space space) {
    if (space.equals(spaceToFind)) {
      found = true;
      logger.info("Space with id: {} found", spaceToFind);
    }
  }
}
