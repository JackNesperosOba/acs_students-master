package doorStates;

import java.time.LocalDateTime;
import java.util.*;

//Unique clock for every door that can be in unlocked_shortly state to trace the time defined to change the state to propped or locked
public class Clock extends Observable {
  private LocalDateTime date;
  private Timer timer;
  private int period; // seconds

  public Clock(int period) {
    this.period = period;
    timer = new Timer();
  }
  public void start() {
    TimerTask repeatedTask = new TimerTask() {
      public void run() { // instance of anonymous class
        date = LocalDateTime.now();
        System.out.println("run() executed at " + date);
        setChanged();
        notifyObservers(date);
      }
    };
    timer.scheduleAtFixedRate(repeatedTask, 0, 1000 * period);
  }
  public void stop() { timer.cancel(); }
  public int getPeriod() { return period; }
  public LocalDateTime getDate() { return date; }
}