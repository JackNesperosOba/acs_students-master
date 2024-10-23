package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

//Class schedule to know if a certain user can do an action inside the range of time defined
public class Schedule {
  private LocalDate firstDay;
  private LocalDate lastDay;
  private ArrayList<DayOfWeek> daysOfTheWeek;
  private LocalTime fromTime;
  private LocalTime toTime;

  public Schedule(LocalDate firstDay, LocalDate lastDay, ArrayList<DayOfWeek> daysOfTheWeek, LocalTime fromTime, LocalTime toTime) {
    this.firstDay = firstDay;
    this.lastDay = lastDay;
    this.daysOfTheWeek = daysOfTheWeek;
    this.fromTime = fromTime;
    this.toTime = toTime;
  }

  public boolean canSendRequest(LocalDateTime now) {
    LocalDate today = now.toLocalDate();  //current date
    DayOfWeek dayOfWeek = now.getDayOfWeek(); // current day of the week
    LocalTime currentTime = now.toLocalTime();  // current time

    // Checks if it's the day is between the first and last day
    if (today.isBefore(firstDay) || today.isAfter(lastDay)) {
      return false;
    }
    // If it's in the day of the week allowed
    if (!daysOfTheWeek.contains(dayOfWeek)) {
      return false;
    }
    // Current time is inside the range allowed
    if (currentTime.isBefore(fromTime) || currentTime.isAfter(toTime)) {
      return false;
    }
    //  If all conditions above are true we can send the request
    return true;
  }

}
