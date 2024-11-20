package basenostates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Class schedule to know if a certain user can do an action inside the range of time defined
@SuppressWarnings({"checkstyle:MissingJavadocType", "checkstyle:LineLength"})
public class Schedule {
  private LocalDate firstDay;
  private LocalDate lastDay;
  private ArrayList<DayOfWeek> daysOfTheWeek;
  private LocalTime fromTime;
  private LocalTime toTime;
  private static final Logger logger = LoggerFactory.getLogger("fita1.basenostates.Schedule");

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public Schedule(LocalDate firstDay, LocalDate lastDay, ArrayList<DayOfWeek>
      daysOfTheWeek, LocalTime fromTime, LocalTime toTime) {
    this.firstDay = firstDay;
    this.lastDay = lastDay;
    this.daysOfTheWeek = daysOfTheWeek;
    this.fromTime = fromTime;
    this.toTime = toTime;
    logger.info("Schedule created with start date: {}, end date: {}, days of the week: {}, time range: {} - {}",
        firstDay, lastDay, daysOfTheWeek, fromTime, toTime);
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public boolean canSendRequest(LocalDateTime now) {
    LocalDate today = now.toLocalDate();  //current date
    DayOfWeek dayOfWeek = now.getDayOfWeek(); // current day of the week
    LocalTime currentTime = now.toLocalTime();  // current time

    // Checks if it's the day is between the first and last day
    if (today.isBefore(firstDay) || today.isAfter(lastDay)) {
      logger.warn("Request denied: Date {} is outside the allowed range ({} to {}).", today, firstDay, lastDay);
      return false;
    }
    // If it's in the day of the week allowed
    if (!daysOfTheWeek.contains(dayOfWeek)) {
      logger.warn("Request denied: Day {} is not within the allowed days of the week: {}", dayOfWeek, daysOfTheWeek);
      return false;
    }
    // Current time is inside the range allowed
    if (currentTime.isBefore(fromTime) || currentTime.isAfter(toTime)) {
      logger.warn("Request denied: Time {} is outside the allowed range ({} to {}).", currentTime, fromTime, toTime);
      return false;
    }
    //  If all conditions above are true we can send the request
    logger.info("Request allowed on {} at {}", today, currentTime);
    return true;
  }

}
