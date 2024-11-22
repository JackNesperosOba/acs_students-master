package basenostates;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Partition area that inherits the abstract class Area
// including a spaces, group of spaces or partitions

@SuppressWarnings("checkstyle:MissingJavadocType")
public class Partition extends Area {
  private Partition partition;
  private ArrayList<Area> areas = new ArrayList<>();
  private static final Logger logger = LoggerFactory.getLogger("fita2.visitor.Area.Partition");

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Partition(String id, Partition partition) {
    super(id);
    this.partition = partition;
    logger.debug("Partition created with id: {} inside partition: {}", id, partition);
  }

  @Override
  public void acceptVisitor(Visitor vis) {
    logger.debug("Visitor accepting for partition with id: {}", id);
    vis.visitPartition(this);
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public ArrayList<Area> getAreas() {
    logger.debug("Fetching areas for partition with id: {}", id);
    return areas;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void addArea(Area area) {
    logger.info("Area added to partition with id: {}", id);
    this.areas.add(area);
  }
}
